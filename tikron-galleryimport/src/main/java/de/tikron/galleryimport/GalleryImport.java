/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.galleryimport;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tikron.persistence.dao.gallery.GalleryDaoFactory;
import de.tikron.persistence.model.gallery.Category;
import de.tikron.persistence.model.gallery.Picture;

/**
 * Importiert die Metadaten einer mit Photoshop erstellten Web-Fotogallerie in die Freakworm-Gallerie.
 * 
 * @author Titus Kruse
 * @since 26.09.2008
 */
public class GalleryImport {

	private static Logger logger = LoggerFactory.getLogger(GalleryImport.class);
	
	private static final String VERSION = GalleryImport.class.getPackage().getImplementationVersion();

	private static int picturesAdded = 0;
	private static int picturesUpdated = 0;
	private static int picturesRemoved = 0;

	private static final String PERSISTENCE_UNIT_NAME = "tikron-persistence";

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction transaction;

	/**
	 * Programmeinstiegspunkt
	 * 
	 * @param args Programmparameter.
	 */
	public static void main(String[] args) throws Exception {
		logger.info("Gallery-Import Version {}", VERSION);
		if (args.length >= 5) {
			try {
				// Parameter übersetzen
				// String indexFileName = args[0];
				String directoryPath = args[0];
				String categoryName = args[1];
				String dbHost = args[2];
				String dbSchema = args[3];
				String dbUser = args[4];
				String dbPassword = args[5];
				// Verbindungsparameter Datenbank festlegen
				Map<String, String> entityManagerProperties = new HashMap<String, String>();
				String dbConnectionUrl = "jdbc:mysql://" + dbHost + "/" + dbSchema;
				entityManagerProperties.put("hibernate.connection.url", dbConnectionUrl);
				entityManagerProperties.put("hibernate.connection.username", dbUser);
				entityManagerProperties.put("hibernate.connection.password", dbPassword);
				// Force Hibernate to ignore cache annotations in model classes
				entityManagerProperties.put("hibernate.cache.use_second_level_cache", dbPassword);
				entityManagerProperties.put("hibernate.cache.use_query_cache", dbPassword);
				// JPA-EntityManager holen
				logger.info(String.format("Connecting to %s, database %s with user %s.", dbHost, dbSchema, dbUser, dbPassword));
				entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, entityManagerProperties);
				entityManager = entityManagerFactory.createEntityManager();
				// Transaktion öffnen und Kategorie holen
				beginTransaction();
				logger.info(String.format("Fetching category properties for %s.", categoryName));
				Category category = getCategory(categoryName);
				if (category != null) {
					// log.info(String.format("Parsing HTML-Document \"%s.\"", indexFileName);
					// Map<String, BufferedPicture> pictureBuffer = parseIndexPage(indexFileName);
					logger.info(String.format("Scanning directory \"%s\".", directoryPath));
					Map<String, BufferedPicture> pictureBuffer = scanDirectory(directoryPath);
					logger.info(String.format("Removing old pictures from category %d.", category.getId()));
					removePictures(category, pictureBuffer);
					logger.info(String.format("Updating category %d with new pictures.", category.getId()));
					addOrUpdatePictures(category, pictureBuffer);
					// Bilder werden nun direkt persistiert, nicht mehr automatisch über die Collection
					// GalleryDaoFactory.getCategoryDao(entityManager).update(category);
					commit();
					logger.info(String.format("%d pictures removed, %d added and %d updated for category \"%s\".", picturesRemoved,
							picturesAdded, picturesUpdated, categoryName));
				} else {
					rollback();
					logger.error(String.format("Category \"%s\" does not exist.", categoryName));
				}
			} catch (NumberFormatException e) {
				rollback();
				logger.error("Invalid parameter.");
			}
			// Indicates a well-formedness error
			// catch (SAXException e) {
			// rollback();
			// log.info(String.format("%s is not well-formed.", args[0]);
			// log.error(e.getMessage());
			// } catch (Exception e) {
			catch (Exception e) {
				rollback();
				throw e;
			} finally {
				if (entityManager != null)
					entityManager.close();
				if (entityManagerFactory != null)
					entityManagerFactory.close();
			}
		} else {
			logger.info("Usage: java GalleryImport <URL to pictures directory> <category name> <database host> <schema> <user> <password>");
			return;
		}
	}

	/**
	 * Scans a directory in the file system for files and returs a map of pictures.
	 * 
	 * @param directoryPath The directory path name.
	 * @return The Map of image names and associated buffered pictures.
	 */
	private static Map<String, BufferedPicture> scanDirectory(String directoryPath) {
		Map<String, BufferedPicture> pictureBuffer = new TreeMap<String, BufferedPicture>();
		File dir = new File(directoryPath);
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isFile()) {
					String fileName = file.getName();
					String imageName = getName(fileName);
					BufferedPicture picture = new BufferedPicture(imageName);
					picture.setImageName(fileName);
					pictureBuffer.put(imageName, picture);
				}
			}
		}
		return pictureBuffer;
	}

	/**
	 * Fügt neue Bilder hinzu oder aktualisiert diese, falls bereits vorhanden.
	 * 
	 * @param category Die Kategorie mit den vorhandenen Bildern.
	 * @param pictureBuffer Der Puffer mit den neuen Bildern.
	 */
	private static void addOrUpdatePictures(Category category, Map<String, BufferedPicture> pictureBuffer) {
		for (Map.Entry<String, BufferedPicture> pictureBufferEntry : pictureBuffer.entrySet()) {
			String name = pictureBufferEntry.getKey();
			// Neues Bild holen
			BufferedPicture bufferedPicture = pictureBufferEntry.getValue();
			// Versuchen, altes Bild zu holen
			Picture picture = getPicture(category, name);
			// Vorhanden, aber mit abweichenden Eigenschaften
			if (picture != null) {
				if (!bufferedPicture.equals(new BufferedPicture(picture))) {
					// Altes Bild mit den Eigenschaften des neuen überschreiben
					bufferedPicture.overridePicture(picture);
					picture = updatePicture(picture);
					logger.info(String.format("Picture with name %s and title \"%s\" updated in category %d.", picture.getName(),
							picture.getTitle(), category.getId()));
					++picturesUpdated;
				}
			} else {
				// Neues Bild speichern
				picture = new Picture();
				picture.setCategory(category);
				bufferedPicture.copyToPicture(picture);
				picture = insertPicture(picture);
				category.getPictures().add(picture);
				logger.info(String.format("Picture with name %s and title \"%s\" added to category %d.", picture.getName(),
						picture.getTitle(), category.getId()));
				++picturesAdded;
			}
		}
	}

	/**
	 * Entfernt nicht mehr vorhandene Bilder aus der Kategorie.
	 * 
	 * @param category Die Kategorie mit den vorhandenen Bildern.
	 * @param pictureBuffer Der Puffer mit den neuen Bildern.
	 */
	private static void removePictures(Category category, Map<String, BufferedPicture> pictureBuffer) {
		Set<Picture> pictures = category.getPictures();
		for (Iterator<Picture> picturesIterator = pictures.iterator(); picturesIterator.hasNext();) {
			Picture picture = (Picture) picturesIterator.next();
			if (!pictureBuffer.containsKey(picture.getName())) {
				deletePicture(picture);
				picturesIterator.remove();
				logger.info(String.format("Picture with name %s and title \"%s\" removed from category %d.", picture.getName(),
						picture.getTitle(), category.getId()));
				++picturesRemoved;
			}
		}
	}

	/**
	 * Liefert eine Kategorie (mit allen Bildern).
	 * 
	 * @param name Der eindeutige Name der Kategorie.
	 * @return Die Kategorie oder null, falls sie nicht gefunden wurde.
	 */
	private static Category getCategory(String name) {
		return GalleryDaoFactory.getCategoryDao(entityManager).findByName(name);
	}

	/**
	 * Sucht in der übergebenen Kategorie ein Bild mit dem angegebenen Namen.
	 * 
	 * @param category Die Kategorie, in der das Bild gesucht wird.
	 * @param name Der Name des Bildes.
	 * @return Das Bild oder null, falls es nicht gefunden wurde.
	 */
	private static Picture getPicture(Category category, String name) {
		for (Picture oldPicture : category.getPictures()) {
			if (oldPicture.getName().equals(name)) {
				return oldPicture;
			}
		}
		return null;
	}

	/**
	 * Speichert ein Bild
	 * 
	 * @param picture Das Bild.
	 * @return Das gespeicherte Bild.
	 */
	private static Picture insertPicture(Picture picture) {
		return GalleryDaoFactory.getPictureDao(entityManager).insert(picture);
	}

	/**
	 * Aktualisiert ein Bild
	 * 
	 * @param picture Das Bild.
	 * @return Das aktualsierte Bild.
	 */
	private static Picture updatePicture(Picture picture) {
		return GalleryDaoFactory.getPictureDao(entityManager).update(picture);
	}

	/**
	 * Löscht ein Bild
	 * 
	 * @param picture Das zu löschende Bild.
	 */
	private static void deletePicture(Picture picture) {
		GalleryDaoFactory.getPictureDao(entityManager).delete(picture);
	}

	/**
	 * Öffnet eine neue Transaktion.
	 */
	private static void beginTransaction() {
		transaction = entityManager.getTransaction();
		transaction.begin();
	}

	/**
	 * Liefert die aktuelle Transaktion
	 * 
	 * @return Die Transaktion.
	 */
	private static EntityTransaction getTransaction() {
		return transaction;
	}

	/**
	 * Persistiert und schließt die aktuelle Transaktion
	 */
	private static void commit() {
		getTransaction().commit();
	}

	/**
	 * Verwirft die aktuelle Transaktion.
	 */
	private static void rollback() {
		try {
			EntityTransaction tx = getTransaction();
			if (tx != null && tx.isActive())
				tx.rollback();
		} catch (RuntimeException e) {
			logger.error("Error rolling back transaction");
		}
	}

	/**
	 * Extrahiert aus einem Pfadnamen den Dateinamen.
	 * 
	 * @param path Der Pfadname.
	 * @return Der Dateiname.
	 */
	private static String getFileName(String path) {
		return new File(path).getName();
	}

	/**
	 * Extrahier aus einem Pfadnamen des Dateiname ohne evtl. vorhandenes Suffix.
	 * 
	 * @param path Der Pfadname.
	 * @return Der Dateiname.
	 */
	private static String getName(String path) {
		String fileName = getFileName(path);
		int suffixPos = fileName.lastIndexOf('.');
		if (suffixPos > 0) {
			return fileName.substring(0, suffixPos);
		}
		return fileName;
	}

	/**
	 * Ignoriert die DTD der XML-Datei.
	 * 
	 * @since 28.09.2008
	 * @author Titus Kruse
	 */
	/*
	 * private static class NoOpEntityResolver implements EntityResolver { public InputSource resolveEntity(String
	 * publicId, String systemId) { return new InputSource(new StringReader("")); } }
	 */

}

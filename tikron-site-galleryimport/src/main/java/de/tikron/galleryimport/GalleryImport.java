/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.galleryimport;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.cyberneko.html.parsers.DOMParser;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;
import org.xml.sax.SAXException;

import de.tikron.persistence.dao.gallery.GalleryDaoFactory;
import de.tikron.persistence.model.gallery.Category;
import de.tikron.persistence.model.gallery.Picture;

/**
 * Importiert die Metadaten einer mit Photoshop erstellten Web-Fotogallerie in die Freakworm-Gallerie.
 * 
 * @date 26.09.2008
 * @author Titus Kruse
 */
public class GalleryImport {

	private static Logger log = Logger.getLogger(GalleryImport.class);

	private static int picturesAdded = 0;
	private static int picturesUpdated = 0;
	private static int picturesRemoved = 0;

	private static final String PERSISTENCE_UNIT_NAME = "tikron-data";

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction transaction;

	/**
	 * Programmeinstiegspunkt
	 * 
	 * @param args Programmparameter.
	 */
	public static void main(String[] args) throws Exception {
		log.info("Gallery-Import Version 1.2");
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
				log.info(String.format("Connecting to %s, database %s with user %s.", dbHost, dbSchema, dbUser, dbPassword));
				entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, entityManagerProperties);
				entityManager = entityManagerFactory.createEntityManager();
				// Transaktion öffnen und Kategorie holen
				beginTransaction();
				log.info(String.format("Fetching category properties for %s.", categoryName));
				Category category = getCategory(categoryName);
				if (category != null) {
					// log.info(String.format("Parsing HTML-Document \"%s.\"", indexFileName);
					// Map<String, BufferedPicture> pictureBuffer = parseIndexPage(indexFileName);
					log.info(String.format("Scanning directory \"%s\".", directoryPath));
					Map<String, BufferedPicture> pictureBuffer = scanDirectory(directoryPath);
					log.info(String.format("Removing old pictures from category %d.", category.getId()));
					removePictures(category, pictureBuffer);
					log.info(String.format("Updating category %d with new pictures.", category.getId()));
					addOrUpdatePictures(category, pictureBuffer);
					// Bilder werden nun direkt persistiert, nicht mehr automatisch über die Collection
					// GalleryDaoFactory.getCategoryDao(entityManager).update(category);
					commit();
					log.info(String.format("%d pictures removed, %d added and %d updated for category \"%s\".", picturesRemoved,
							picturesAdded, picturesUpdated, categoryName));
				} else {
					rollback();
					log.error(String.format("Category \"%s\" does not exist.", categoryName));
				}
			} catch (NumberFormatException e) {
				rollback();
				log.error("Invalid parameter.");
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
			log.info("Usage: java GalleryImport <URL to pictures directory> <category name> <database host> <schema> <user> <password>");
			return;
		}
	}

	/**
	 * Interpretiert die Index-Page der Web-Fotogallerie.
	 * 
	 * @param indexFileName Pfadname des HTML-Index-Dokuments.
	 * @return Map aus Bildern und deren eindeutigen Namen.
	 * @throws IOException Fehler beim Lesen der Datei.
	 * @throws SAXException Fehler beim Interpretieren der Datei.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private static Map<String, BufferedPicture> parseIndexPage(String indexFileName) throws IOException, SAXException {
		Map<String, BufferedPicture> pictureBuffer = new TreeMap<String, BufferedPicture>();
		DOMParser parser = new DOMParser();
		parser.parse(indexFileName);
		DOMBuilder builder = new DOMBuilder();
		Document doc = builder.build(parser.getDocument());
		Element root = doc.getRootElement();
		Element body = root.getChild("BODY");
		if (body != null) {
			Element table = body.getChild("TABLE");
			if (table != null) {
				List<Element> rows = table.getChildren("TR");
				for (Element row : rows) {
					List<Element> columns = row.getChildren("TD");
					for (Element column : columns) {
						Element a = column.getChild("A");
						if (a != null) {
							String href = a.getAttributeValue("href");
							if (href != null) {
								String name = getName(href);
								BufferedPicture picture = new BufferedPicture(name);
								String subPageFileName = getPathName(indexFileName) + File.separator
										+ href.replace("/", File.separator);
								parseSubPage(subPageFileName, picture);
								Element img = a.getChild("IMG");
								if (img != null) {
									// picture.setThumbnailName(getFileName(img.getAttributeValue("src")));
									// picture.setThumbnailHeight(new Integer(img.getAttributeValue("height")));
									// picture.setThumbnailWidth(new Integer(img.getAttributeValue("width")));
								}
								pictureBuffer.put(name, picture);
							}
						}
					}
				}
			}
		}
		return pictureBuffer;
	}

	/**
	 * Interpretiert eine SubPage der Web-Photogallerie.
	 * 
	 * @param subPageFileName Pfadname des HTML-Dokuments.
	 * @param picture Bild, in das die extrahierten Daten gespeichert werden.
	 * @throws IOException Fehler beim Lesen der Datei.
	 * @throws SAXException Fehler beim Interpretieren der Datei.
	 */
	private static void parseSubPage(String subPageFileName, BufferedPicture picture) throws IOException, SAXException {
		DOMParser parser = new DOMParser();
		parser.parse(subPageFileName);
		DOMBuilder builder = new DOMBuilder();
		Document doc = builder.build(parser.getDocument());
		Element root = doc.getRootElement();
		Element body = root.getChild("BODY");
		if (body != null) {
			Element img = body.getChild("IMG");
			if (img != null) {
				picture.setImageName(getFileName(img.getAttributeValue("src")));
				// picture.setImageHeight(new Integer(img.getAttributeValue("height")));
				// picture.setImageWidth(new Integer(img.getAttributeValue("width")));
				picture.setTitle(img.getAttributeValue("alt"));
			}
			Element p = body.getChild("P");
			if (p != null) {
				picture.setDescription(StringUtils.stripToNull(p.getText()));
			}
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
					log.info(String.format("Picture with name %s and title \"%s\" updated in category %d.", picture.getName(),
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
				log.info(String.format("Picture with name %s and title \"%s\" added to category %d.", picture.getName(),
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
				log.info(String.format("Picture with name %s and title \"%s\" removed from category %d.", picture.getName(),
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
			log.error("Error rolling back transaction");
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
	 * Extrahiert aus einem Pfadnamen den Verzeichnisnamen.
	 * 
	 * @param path Der Pfadname.
	 * @return Der Verzeichnisname.
	 */
	private static String getPathName(String path) {
		return new File(path).getParent();
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
	 * @date 28.09.2008
	 * @author Titus Kruse
	 */
	/*
	 * private static class NoOpEntityResolver implements EntityResolver { public InputSource resolveEntity(String
	 * publicId, String systemId) { return new InputSource(new StringReader("")); } }
	 */

}

/**
 * Copyright (c) 2009 by Titus Kruse.
 */
package de.tikron.webapp.service.gallery;

import java.net.URI;
import java.util.List;
import java.util.Map;

import de.tikron.persistence.model.gallery.Catalog;
import de.tikron.persistence.model.gallery.Category;
import de.tikron.persistence.model.gallery.Picture;
import de.tikron.persistence.model.user.CategoryComment;
import de.tikron.persistence.model.user.PictureComment;
import de.tikron.webapp.util.Pager;

/**
 * Schnittstelle zum Service-Objekt der Bilder-Gallerie.
 * 
 * @date 08.03.2009
 * @author Titus Kruse
 */
public interface GalleryService {

	/**
	 * Lädt einen Katalog.
	 * 
	 * @param catalogId Die ID des Katalogs.
	 * @return Der Katalog oder null, wenn er nicht gefunden wurde.
	 */
	public Catalog getCatalog(Long catalogId);

	/**
	 * Lädt einen Katalog.
	 * 
	 * @param name Der Name des Katalogs.
	 * @return Der Katalog oder null, wenn er nicht gefunden wurde.
	 */
	public Catalog getCatalog(String name);
	
	public Catalog getCatalogWithCategories(Long catalogId);

	/**
	 * Lädt die Kataloge.
	 * 
	 * @param visibleOnly Falls true, enthält die Liste nur sichtbare Kataloge.
	 * @return Eine Liste der Kataloge.
	 */
	public List<Catalog> getCatalogs(boolean visibleOnly);
	
	/**
	 * Liefert alle Kataloge mit deren Kategorien (für die Main Navi).
	 * 
	 * @param visibleOnly Gibt an, ob die Liste nur sichtbare Kataloge und Kategorien enthält. 
	 * @return Eine Liste der Kataloge.
	 */
	public List<Catalog> getCatalogsWithCategories(boolean visibleOnly);

	/**
	 * Lädt eine Kategorie.
	 * 
	 * @param categoryId Die ID der Kategorie.
	 * @return Die Kategorie oder null, wenn sie nicht gefunden wurde.
	 */
	public Category getCategory(Long categoryId);

	/**
	 * Lädt eine Kategorie.
	 * 
	 * @param name Der Name der Kategorie.
	 * @return Die Kategorie oder null, wenn sie nicht gefunden wurde.
	 */
	public Category getCategory(String name);

	/**
	 * Lädt alle Kategorien.
	 * 
	 * @param visibleOnly Gibt an, ob die Liste nur sichtbare Kategorien enthält. 
	 * @return Eine Liste der Kategorien.
	 */
	public List<Category> getCategories(boolean visibleOnly);

	/**
	 * Lädt die Kategorien eines Katalogs.
	 * 
	 * @param catalog Der Katalog, dessen Kategorien geladen werden.
	 * @param visibleOnly Gibt an, ob die Liste nur sichtbare Kategorien enthält. 
	 * @return Eine Liste der Kategorien.
	 */
	public List<Category> getCategories(Catalog catalog, boolean visibleOnly);

	/**
	 * Lädt ein Bild.
	 * 
	 * @param pictureId Die ID des Bildes.
	 * @return Das Bild.
	 */
	public Picture getPicture(Long pictureId);

	/**
	 * Lädt ein Bild.
	 * 
	 * @param name Der Name des Bildes.
	 * @return Das Bild oder null, wenn es nicht gefunden wurde.
	 */
	public Picture getPicture(String name);

	/**
	 * Lädt ein zufällig ausgewähltes Bild.
	 * 
	 * @return Das ausgewählte Bild.
	 */
	public Picture getRandomPicture();

	/**
	 * Lädt die Bilder einer Kategorie.
	 * 
	 * @param category Die Kategorie, deren Bilder geladen werden.
	 * @return Eine Liste der Bilder.
	 */
	public List<Picture> getPictures(Category category);

	/**
	 * Liefert einen Pager zu den Bildern übergebenen Kategorie.
	 * 
	 * @param category Die Kategorie.
	 * @return Der Pager.
	 */
	public Pager<Picture> getPicturePager(Category category);

	/**
	 * Lädt die Kommentare einer Kategorie.
	 * 
	 * @param category Die Kategorie, deren Kommentare geladen werden.
	 * @return Die Liste der Kommentare.
	 */
	public List<CategoryComment> getComments(Category category);

	/**
	 * Lädt die Kommentare eines Bildes.
	 * 
	 * @param picture Das Bild, dessen Kommentare geladen werden.
	 * @return Die Liste der Kommentare.
	 */
	public List<PictureComment> getComments(Picture picture);

	/**
	 * Liefert den URI zum angegebenen Bild und Template.
	 * 
	 * @param Das Bild (Entity).
	 * @param Das Template.
	 * @return Den URI zum Bild.
	 */
	public URI getImageUri(Picture picture, String template);

	/**
	 * Liefert eine Map aller Templates und dazugehörige URIs für das angegebene Bild.
	 * 
	 * @param Das Bild (Entity).
	 * @return Eine Map aus Templates und URIs.
	 */
	public Map<String, URI> getImageUris(Picture picture);

}

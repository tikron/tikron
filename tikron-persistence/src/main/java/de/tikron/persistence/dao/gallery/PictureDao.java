package de.tikron.persistence.dao.gallery;

import java.util.List;

import de.tikron.jpa.dao.GenericDao;
import de.tikron.persistence.model.gallery.Category;
import de.tikron.persistence.model.gallery.Picture;

public interface PictureDao extends GenericDao<Picture, Long> {

	/**
	 * Liefert das Bild mit dem angegebenen Namen.
	 * 
	 * @param name Der eindeutige Name des Bildes.
	 * @return Das Bild oder null, falls es nicht gefunden wurde.
	 */
	public Picture findByName(String name);

	/**
	 * Liste aller Bilder einer Kategorie sortiert nach Name holen.
	 * 
	 * @param category Die Kategorie.
	 * @return Die Liste der Bilder.
	 */
	public List<Picture> findByCategoryOrderByName(Category category);

	/**
	 * Liefert das nach Name erste nächste Bild innerhalb der übergebenen Kategorie.
	 * 
	 * @param category Die Kategorie, in der gesucht wird.
	 * @return Das ermittelte Bild oder null, falls kein Bild gefunden wurde.
	 */
	public Picture findFirst(Category category);

	/**
	 * Liefert das zum übergebenen Bild nach Name vorherige Bild. Dabei wird nur innerhalb der übergebenen Kategorie
	 * gesucht.
	 * 
	 * @param category Die Kategorie, in der gesucht wird.
	 * @param picture Das Bild, auf dessen Basis das vorherige Bild ermittelt wird.
	 * @return Das ermittelte Bild oder null, falls kein Bild gefunden wurde.
	 */
	public Picture findPrevious(Category category, Picture picture);

	/**
	 * Liefert das zum übergebenen Bild nach Name nächste Bild. Dabei wird nur innerhalb der übergebenen Kategorie
	 * gesucht.
	 * 
	 * @param category Die Kategorie, in der gesucht wird.
	 * @param picture Das Bild, auf dessen Basis das nächste Bild ermittelt wird.
	 * @return Das ermittelte Bild oder null, falls kein Bild gefunden wurde.
	 */
	public Picture findNext(Category category, Picture picture);

	/**
	 * Liefert das nach Name letzte nächste Bild innerhalb der übergebenen Kategorie.
	 * 
	 * @param category Die Kategorie, in der gesucht wird.
	 * @return Das ermittelte Bild oder null, falls kein Bild gefunden wurde.
	 */
	public Picture findLast(Category category);

	/**
	 * Liefert ein zufällig ausgewähltes Bild zur Anzeige im Teaser.
	 * 
	 * @return Das ausgewählte Bild.
	 */
	public Picture findForTeaser();

}

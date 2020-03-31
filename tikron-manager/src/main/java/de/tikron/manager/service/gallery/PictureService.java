/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.service.gallery;

import java.util.List;

import de.tikron.manager.service.common.CRUDService;
import de.tikron.persistence.model.gallery.Category;
import de.tikron.persistence.model.gallery.Picture;

/**
 * Service für Bilder.
 * 
 * @date 12.02.2012
 * @author Titus Kruse
 */
public interface PictureService extends CRUDService<Picture, Long> {

	/**
	 * Liste aller Bilder einer Kategorie holen.
	 * 
	 * @param category Der Katalog.
	 * @return Die Liste der Kategorien.
	 */
	public List<Picture> getPictures(Category category);

	/**
	 * Liefert das zum übergebene Nild vorherige Bild.
	 * 
	 * @param picture Das Referenzbild.
	 * @return Das vorherige Bild oder null, falls es keines gubt.
	 */
	public Picture getPrevious(Picture picture);

	/**
	 * Liefert das zum übergebene Nild nächste Bild.
	 * 
	 * @param picture Das Referenzbild.
	 * @return Das nächste Bild oder null, falls es keines gubt.
	 */
	public Picture getNext(Picture picture);

}

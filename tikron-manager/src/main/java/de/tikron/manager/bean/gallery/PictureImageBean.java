/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.bean.gallery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.tikron.manager.service.common.Constants;
import de.tikron.manager.service.gallery.CategoryService;
import de.tikron.persistence.model.gallery.Category;
import de.tikron.persistence.model.gallery.Picture;

/**
 * Helper-Bean, die Pfadnamen zu Gallery-Bilder bereitstellt.
 *
 * @since 18.02.2012
 * @author Titus Kruse
 */
@ManagedBean
@ApplicationScoped
public class PictureImageBean {

	@ManagedProperty(value = "#{categoryService}")
	private CategoryService categoryService;

	/**
	 * Liefert den Namen des Verzeichnisses, in dem Bilder gespeichert werden.
	 * 
	 * @return Der Verzeichnisname.
	 */
	public String getGalleryImagePath() {
		return Constants.GALLERY_IMAGE_PATH;
	}

	/**
	 * Liefert den Verzeichnispfad zur Kategorie.
	 * 
	 * @param category Die Kategorie.
	 * @return Der Verzeichnispfad.
	 */
	public String getPictureImagePath(Category category) {
		return getGalleryImagePath() + category.getName() + "/";
	}

	/**
	 * Liefert eine Map der Verzeichnispfade zu allen Kategorien.
	 * 
	 * @return Ein Map.
	 */
	public Map<Long, String> getPictureImagePaths() {
		Map<Long, String> pictureImagePaths = new HashMap<Long, String>();
		List<Category> categories = categoryService.getAll();
		for (Category category : categories) {
			pictureImagePaths.put(category.getId(), getPictureImagePath(category));
		}
		return pictureImagePaths;
	}

	/**
	 * Liefert den Verzeichnispfad des Bildes zum übergebenen Bild.
	 * 
	 * @param picture Das Bild.
	 * @return Der Verzeichnisname.
	 */
	public String getPictureFileName(Picture picture) {
		return getPictureImagePath(picture.getCategory()) + picture.getImageName();
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

}

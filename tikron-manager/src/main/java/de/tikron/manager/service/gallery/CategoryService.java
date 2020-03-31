/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.service.gallery;

import java.util.List;

import de.tikron.manager.service.common.CRUDService;
import de.tikron.persistence.model.gallery.Catalog;
import de.tikron.persistence.model.gallery.Category;

/**
 * Service für Kategorien.
 * 
 * @date 12.02.2012
 * @author Titus Kruse
 */
public interface CategoryService extends CRUDService<Category, Long> {

	/**
	 * Liste aller Kategorien eines Katalogs holen.
	 * 
	 * @param catalog Der Katalog.
	 * @return Die Liste der Kategorien.
	 */
	public List<Category> getCategories(Catalog catalog);

}

package de.tikron.persistence.dao.gallery;

import java.util.List;

import de.tikron.persistence.model.gallery.Catalog;
import de.tikron.persistence.model.gallery.Category;
import de.tikru.commons.jpa.dao.GenericDao;

public interface CategoryDao extends GenericDao<Category, Long> {

	/**
	 * Liefert eine Kategorie mit dem angegebenen Namen.
	 * 
	 * @param name Der eindeutige Name der Kategorie.
	 * @return Die Kategorie oder null, falls sie nicht gefunden wurde.
	 */
	public Category findByName(String name);

	/**
	 * Liste aller sichtbaren Kategorien holen.
	 * 
	 * @param visibleOnly Gibt an, ob die Liste nur sichtbare Kategorien enthält.
	 * @return Die Liste der Kategorien.
	 */
	public List<Category> findByVisibility(boolean visibleOnly);

	/**
	 * Liste aller Kategorien eines Katalogsholen.
	 * 
	 * @param catalog Der Katalog.
	 * @return Die Liste der Kategorien.
	 */
	public List<Category> findByCatalog(Catalog catalog);

	/**
	 * Liste aller Kategorien eines Katalogs sortiert nach Name holen.
	 * 
	 * @param catalog Der Katalog.
	 * @return Die Liste der Kategorien.
	 */
	public List<Category> findByCatalogOrderByName(Catalog catalog);

	/**
	 * Liste aller sichtbaren Kategorien eines Katalogs holen.
	 * 
	 * @param catalog Der Katalog.
	 * @param visibleOnly Gibt an, ob die Liste nur sichtbare Kategorien enthält.
	 * @return Die Liste der Kategorien.
	 */
	public List<Category> findByCatalogAndVisibility(Catalog catalog, boolean visibleOnly);

	/**
	 * Liste aller sichtbaren Kategorien eines Katalogs sortiert nach Name holen.
	 * 
	 * @param catalog Der Katalog.
	 * @param visibleOnly Gibt an, ob die Liste nur sichtbare Kategorien enthält.
	 * @return Die Liste der Kategorien.
	 */
	public List<Category> findByCatalogAndVisibilityOrderByName(Catalog catalog, boolean visibleOnly);

}

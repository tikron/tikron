package de.tikron.persistence.dao.gallery;

import java.util.List;

import de.tikron.persistence.model.gallery.Catalog;
import de.tikru.commons.jpa.dao.GenericDao;

public interface CatalogDao extends GenericDao<Catalog, Long> {
	
	/**
	 * Liefert einen Katalog mit den zugehörigen Kategorien.
	 * 
	 * @param id Der Primärschlüssel des Katalogs.
	 * @return Der Katalog.
	 */
	public Catalog findByIdFetchCategories(Long id);

	/**
	 * Liefert einen Katalog mit dem angegebenen Namen.
	 * 
	 * @param name Der eindeutige Name des Katalogs.
	 * 
	 * @return Der Katalog oder null, falls er nicht gefunden wurde.
	 */
	public Catalog findByName(String name);
	
	/**
	 * Liefert alle Kataloge sortiert nach Rang.
	 * 
	 * @return Eine Liste der Kataloge.
	 */
	public List<Catalog> findAllOrderByName();
	
	/**
	 * Liefert eine Liste von Katalogen mit den zugehörigen Kategorien.
	 * 
	 * @return Die Liste der Kataloge.
	 */
	public List<Catalog> findAllFetchCategories();
	
	/**
	 * Liefert alle Kataloge.
	 * 
	 * @param visibleOnly Gibt an, ob die Liste nur sichtbare Kataloge enthält.
	 * @return Eine Liste der Kataloge.
	 */
	public List<Catalog> findByVisibility(boolean visibleOnly);
	
	/**
	 * Liefert alle sichtbaren Kataloge sortiert nach Rang.
	 * 
	 * @param visibleOnly Gibt an, ob die Liste nur sichtbare Kataloge enthält.
	 * @return Eine Liste der Kataloge.
	 */
	public List<Catalog> findByVisibilityOrderByName(boolean visibleOnly);
	
	/**
	 * Liefert alle Kataloge mit den zugehörigen Kategorien.
	 * 
	 * @param visibleOnly Gibt an, ob die Liste nur sichtbare Kataloge enthält.
	 * @return Eine Liste der Kataloge.
	 */
	public List<Catalog> findByVisibilityFetchCategories(boolean visibleOnly);

}

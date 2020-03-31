/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.manager.service.common;

import java.io.Serializable;

import de.tikru.commons.jpa.domain.Entity;

/**
 * Deklariert grundsätzliche Methoden, die zum Erstellen, Lesen, Ändern und Löschen von Entitäten verwendet werden.
 * 
 * @date 07.06.2008
 * @author Titus Kruse
 */
public interface CRUDService<T extends Entity<ID>, ID extends Serializable> extends ReadingService<T, ID> {

	/**
	 * Eine Entität speichern.
	 * 
	 * @param entity Die Entität.
	 */
	public T insert(T entity);

	/**
	 * Eine Entität aktualisieren.
	 * 
	 * @param entity Die Entität.
	 */
	public T update(T entity);

	/**
	 * Eine Entität persistieren.
	 * 
	 * @param entity Die Entität.
	 */
	public T save(T entity);

	/**
	 * Eine Entität löschen.
	 * 
	 * @param entity Die Entität.
	 */
	public void delete(T entity);

}

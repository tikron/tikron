/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.manager.service.common;

import java.io.Serializable;
import java.util.List;

import de.tikru.commons.jpa.domain.Entity;

/**
 * Deklariert grundsätzliche Methoden, die von nur lesenden Services implementieren müssen.
 * 
 * @date 01.04.2015
 * @author Titus Kruse
 */
public interface ReadingService<T extends Entity<ID>, ID extends Serializable> {

	/**
	 * Eine Entität auf Basis des Primärschlüssel holen.
	 * 
	 * @param id Der Primärschlüssel.
	 * @return Die Entität.
	 */
	public T get(ID id);

	/**
	 * Eine Liste aller Entitäten holen.
	 * 
	 * @return Die Liste der Entitäten.
	 */
	public List<T> getAll();

}

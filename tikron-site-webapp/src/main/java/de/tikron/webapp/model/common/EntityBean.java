/**
 * Copyright (c) 2014 by Titus Kruse.
 */
package de.tikron.webapp.model.common;

import java.io.Serializable;

import de.tikron.jpa.domain.Entity;

/**
 * An entity bean. The entity bean is a wrapper around domain model entity providing additional information on it.
 * 
 * @param <E> Type of entity.
 *
 * @date 31.03.2014
 * @author Titus Kruse
 */
public interface EntityBean<E extends Entity<?>> extends Serializable {

	public E getEntity();

}

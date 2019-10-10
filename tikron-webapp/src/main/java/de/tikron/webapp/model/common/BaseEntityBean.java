/**
 * Copyright (c) 2014 by Titus Kruse.
 */
package de.tikron.webapp.model.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import de.tikron.webapp.facade.common.EntityBeanHelper;
import de.tikru.commons.jpa.domain.Entity;

/**
 * Abstract entity bean holding the entity and providing helper methods.
 * 
 * @param <E> Type of entity for this entity bean.
 *
 * @date 31.03.2014
 * @author Titus
 */
public abstract class BaseEntityBean<E extends Entity<?>> implements EntityBean<E> {

	private static final long serialVersionUID = 7059486057515049582L;

	protected ApplicationContext applicationContext;

	protected EntityBeanHelper entityBeanHelper;

	protected E entity;

	protected BaseEntityBean(E entity) {
		this.entity = entity;
	}

	public E getEntity() {
		return entity;
	}

	/**
	 * Returns true, if a given entity bean is equals to this entity bean. The comparison will be delegated to the entity.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		return obj instanceof BaseEntityBean && ((EntityBean<E>) obj).getEntity().equals(getEntity());
	}

	/**
	 * Returns the hash code. The generating of the hash code will be delegated to the entity.
	 */
	@Override
	public int hashCode() {
		return entity.hashCode();
	}

	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Autowired
	public void setEntityBeanHelper(EntityBeanHelper entityBeanHelper) {
		this.entityBeanHelper = entityBeanHelper;
	}

}

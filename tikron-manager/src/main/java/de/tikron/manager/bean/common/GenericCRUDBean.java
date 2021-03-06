/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.bean.common;

import java.io.Serializable;

import javax.faces.event.ComponentSystemEvent;

import de.tikron.manager.service.common.CRUDService;
import de.tikru.commons.faces.util.Message;
import de.tikru.commons.jpa.domain.Entity;

/**
 * Generic managed bean to handle CRUD operations on the entity T with primary key ID.
 *
 * @author Titus Kruse
 * @since 17.03.2015
 */
public abstract class GenericCRUDBean<T extends Entity<ID>, ID extends Serializable> extends AbstractDetailBean<T> {
	
	private static final long serialVersionUID = -8646208128838304950L;

	private T entity;
	
	private CRUDService<T, ID> service;

	/**
	 * Initialize view.
	 */
	public void preRenderView(ComponentSystemEvent e) {
		if (entity == null) {
			entity = initEntity();
		}
	}
	
	/**
	 * Callback method to receive a new instaciated entity.
	 * 
	 * @return New entity of type T.
	 */
	protected abstract T initEntity();

	/**
	 * Action method to save (persist) the current enitity.
	 * 
	 * @return Navigation-Case.
	 */
	public String save() {
		service.save(entity);
		Message.sendMessage(null, "de.tikron.manager.INFO_SUCCESSFUL_SAVE", null);
		return getSuccessWithRedirect();
	}

	/**
	 * Action method to delete (remove) the current entity.
	 * 
	 * @return Navigation-Case.
	 */
	public String delete() {
		service.delete(entity);
		Message.sendMessage(null, "de.tikron.manager.INFO_SUCCESSFUL_DELETE", null);
		return getSuccessWithRedirect();
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public void setService(CRUDService<T, ID> service) {
		this.service = service;
	}

}

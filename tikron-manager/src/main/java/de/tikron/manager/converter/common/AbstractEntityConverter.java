/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.manager.converter.common;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import de.tikron.manager.service.common.ReadingService;
import de.tikru.commons.jpa.domain.Entity;

/**
 * Generic faces converter to convert to/from entity from/to primary key.
 * 
 * @param T The type of enity to convert to/from.
 * @param ID The type of primary key to convert to/from.
 *
 * @since 29.05.2015
 * @author Titus Kruse
 * @author BalusC
 * 
 * @see http://balusc.blogspot.com/2011/09/communication-in-jsf-20.html
 */
public abstract class AbstractEntityConverter<T extends Entity<ID>, ID extends Serializable> implements Converter {
	
	private ReadingService<T, ID> service;

	private Class<T> clazz;

	protected AbstractEntityConverter(ReadingService<T, ID> service, Class<T> clazz) {
		this.service = service;
		this.clazz = clazz;
	}

	protected AbstractEntityConverter(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	/**
	 * Converts a string representation of the primary key value to the key type ID. Classes extending this class must
	 * implement this method.
	 * 
	 * @param value The key as string.
	 * @return The key as ID.
	 */
	protected abstract ID toKey(String value);

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		ID key;
		try {
			key = toKey(value);
		} catch (IllegalArgumentException e) {
			throw new ConverterException(new FacesMessage(MessageFormat.format("Invalid ID format for entity {0}: {1}", clazz, value)));
		}
		T entity = service.get(key);
		if (entity == null) {
			throw new ConverterException(new FacesMessage(MessageFormat.format("Entity of type {0} with ID {1} not found.", clazz, value)));
		}
		return entity;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && !clazz.isInstance(value)) {
			throw new IllegalArgumentException(MessageFormat.format("Object {0} not of type {1}.", value, clazz));
		}
		T entity = clazz.cast(value);
		return entity == null ? null : String.valueOf(entity.getId());
	}

	/**
	 * Sets the ReadingService to use for reading an entity from the persistence layer.
	 * 
	 * @param service The ReadingService.
	 */
	protected void setService(ReadingService<T, ID> service) {
		this.service = service;
	}

}

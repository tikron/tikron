/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.manager.converter.common;

import de.tikron.jpa.domain.Entity;
import de.tikron.manager.service.common.ReadingService;

/**
 * Generic faces convert to convert to/from entity with primary key of type String.
 *
 * @date 29.05.2015
 * @author Titus Kruse
 */
public abstract class StringKeyEntityConverter<T extends Entity<String>> extends AbstractEntityConverter<T, String> {

	protected StringKeyEntityConverter(ReadingService<T, String> service, Class<T> clazz) {
		super(service, clazz);
	}

	protected StringKeyEntityConverter(Class<T> clazz) {
		super(clazz);
	}

	@Override
	protected String toKey(String value) {
		return value;
	}

}

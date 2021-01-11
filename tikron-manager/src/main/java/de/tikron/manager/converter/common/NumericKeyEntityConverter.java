/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.manager.converter.common;

import de.tikron.manager.service.common.ReadingService;
import de.tikru.commons.jpa.domain.Entity;

/**
 * Generic faces convert to convert to/from entity with numeric primary key.
 *
 * @author Titus Kruse
 * @since 29.05.2015
 */
public abstract class NumericKeyEntityConverter<T extends Entity<Long>> extends AbstractEntityConverter<T, Long> {

	protected NumericKeyEntityConverter(Class<T> clazz) {
		super(clazz);
	}

	protected NumericKeyEntityConverter(ReadingService<T, Long> service, Class<T> clazz) {
		super(service, clazz);
	}

	@Override
	protected Long toKey(String value) {
		return Long.valueOf(value);
	}

}

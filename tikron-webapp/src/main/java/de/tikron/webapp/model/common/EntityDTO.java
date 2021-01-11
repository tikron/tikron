/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.model.common;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Base class for all data transfer objects with unique identifier.
 * 
 * @param ID Type of identifier.
 *
 * @since 03.01.2015
 * @author Titus Kruse
 */
public abstract class EntityDTO<ID> {

	protected final ID id;

	public ID getId() {
		return id;
	}

	protected EntityDTO(ID id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof EntityDTO<?> && (id != null) ? id.equals(((EntityDTO<?>) other).getId())
				: (other == this);
	}

	@Override
	public int hashCode() {
		return id != null ? this.getClass().hashCode() + id.hashCode() : super.hashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).toString();
	}

}

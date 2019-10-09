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
 * @date 03.01.2015
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals()
	 */
	@Override
	public boolean equals(Object other) {
		return other instanceof EntityDTO<?> && (id != null) ? id.equals(((EntityDTO<?>) other).getId())
				: (other == this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return id != null ? this.getClass().hashCode() + id.hashCode() : super.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).toString();
	}

}

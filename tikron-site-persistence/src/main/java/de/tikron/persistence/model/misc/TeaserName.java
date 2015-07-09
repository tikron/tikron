/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.persistence.model.misc;

/**
 * Per convesion defined special teaser names.
 *
 * @date 01.06.2015
 * @author Titus Kruse
 */
public enum TeaserName {
	
	FINDING("FINDING");
	
	private String name;

	private TeaserName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

}

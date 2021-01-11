/**
 * Copyright (c) 2014 by Titus Kruse.
 */
package de.tikron.persistence.model.gallery;

/**
 * Per convention defined catalog names.
 *
 * @author Titus
 * @since 05.04.2014
 */
public enum CatalogName {

	TRAVELS("travels"), EVENTS("events"), CASKET("casket");

	private String name;

	private CatalogName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}

/**
 * Copyright (c) 2014 by Titus Kruse.
 */
package de.tikron.persistence.model.gallery;

/**
 * How to show pictures in the frontend.
 *
 * @author Titus Kruse
 * @since 24.11.2014
 */
public enum DisplayType {

	/**
	 * Full ppage
	 */
	PAGE("displayType.page"),

	/**
	 * Overlay like jquery light box.
	 */
	OVERLAY("displayType.overlay");

	private String label;

	private DisplayType(String label) {
		this.label = label;
	}

	/**
	 * Returns a label for this display type.
	 * 
	 * @return The label as message property key.
	 */
	public String getLabel() {
		return label;
	}
}

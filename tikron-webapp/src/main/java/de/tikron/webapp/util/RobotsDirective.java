/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.util;

/**
 * Defines values for HTML meta directive "robots"
 *
 * @date 08.01.2015
 * @author Titus Kruse
 */
public enum RobotsDirective {

	INDEX("index,follow"), INDEX_NOFOLLOW("index,nofollow"), NOINDEX("noindex,nofollow");

	private String value;

	private RobotsDirective(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}

}

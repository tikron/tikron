/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.manager.service.common;

/**
 * Declares a Service to be refreshed. Typically used for a cashe.
 *
 * @since 01.04.2015
 * @author Titus Kruse
 */
public interface Refreshable {
	
	public void refresh();

}

/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.controller.common;

/**
 * Interface declaring basic Ajax response.
 *
 * @date 17.05.2015
 * @author Titus Kruse
 */
public interface AjaxResponse {
	
	public static final String SUCCESS = "SUCCESS";
	
	public static final String ERROR = "ERROR";

	public String getStatus();

}

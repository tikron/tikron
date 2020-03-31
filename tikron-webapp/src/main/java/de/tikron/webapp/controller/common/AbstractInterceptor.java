/**
 * Copyright (c) 2017 by Titus Kruse.
 */
package de.tikron.webapp.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Base class for all my interceptors
 *
 * @date 26.11.2017
 * @author Titus Kruse
 */
public abstract class AbstractInterceptor extends HandlerInterceptorAdapter {

	/**
	 * Checks whether the request is an Ajax request.
	 *  
	 * @return true, if Ajax.
	 */
	protected boolean isXmlHttpRequest(HttpServletRequest request) {
		String requestedWithHeader = request.getHeader("X-Requested-With");
		return "XMLHttpRequest".equals(requestedWithHeader);
	}

}

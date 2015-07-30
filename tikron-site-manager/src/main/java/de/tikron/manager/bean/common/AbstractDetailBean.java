/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.bean.common;

import org.springframework.web.util.UriComponentsBuilder;

import de.tikron.common.URLCoder;

/**
 * Managed bean common for all entity detail beans.
 *
 * @date 12.02.2012
 * @author Titus Kruse
 */
public abstract class AbstractDetailBean<T> extends BaseBean {

	private String successView;
	
	/**
	 * Default action method for cancelling the current user action.
	 *  
	 * @return The faces outcome navigating to the given success view. 
	 */
	public String cancel() {
		return getSuccessWithRedirect();
	}

	/**
	 * Returns the URI to the given success view with Faces redirect.
	 * 
	 * @return The URI.
	 */
	public String getSuccessWithRedirect() {
		return UriComponentsBuilder.newInstance().path(getSuccessView())
			.queryParam("faces-redirect", "true")
			.build().encode().toString();
	}

	public String getSuccessView() {
		return successView != null ? successView : "/pages/common/home.xhtml";
	}

	public void setSuccessView(String successView) {
		this.successView = URLCoder.decode(successView);
	}

}

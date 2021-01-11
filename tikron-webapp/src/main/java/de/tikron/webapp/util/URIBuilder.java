/**
 * Copyright (c) 2014 by Titus Kruse.
 */
package de.tikron.webapp.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.web.util.UriComponentsBuilder;

/**
 * Helper class to build URIs.
 *
 * @since 08.04.2014
 * @author Titus Kruse
 */
public class URIBuilder {
	
	private static URIBuilder INSTANCE = new URIBuilder();
	
	private URIBuilder() {
	}
	
	public static URIBuilder getInstance() {
		return INSTANCE;
	}

	/**
	 * Builds a relative URI for given view name.
	 * 
	 * @param viewName The Spring view name.
	 * @return The URI.
	 */
	public String fromViewName(String viewName) {
		return fromViewName(viewName, Collections.<NameValuePair> emptyList());
	}

	/**
	 * Builds a relative URI for given view name and one request parameter.
	 * 
	 * @param viewName The Spring view name.
	 * @param paramName The request parameter name.
	 * @param paramValue The request parameter value.
	 * @return The URI.
	 */
	public String fromViewName(String viewName, String paramName, Object paramValue) {
		List<NameValuePair> parameters = new ArrayList<>();
		parameters.add(new NameValuePair(paramName, paramValue));
		return fromViewName(viewName, parameters);
	}

	/**
	 * Builds a relative URI for given view name and request parameters.
	 * 
	 * @param viewName The Spring view name.
	 * @param parameters A list of request parameters.
	 * @return The URI.
	 */
	public String fromViewName(String viewName, List<URIBuilder.NameValuePair> parameters) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("/" + viewName + ".html");
		for (URIBuilder.NameValuePair parameter : parameters) {
			uriBuilder = uriBuilder.queryParam(parameter.getName(), parameter.getValue());
		}
		return uriBuilder.build().encode().toUriString();
	}

	public static class NameValuePair {

		private String name;

		private Object value;

		public NameValuePair(String name, Object value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public Object getValue() {
			return value;
		}

		@Override
		public String toString() {
			return this.name + "=" + this.value.toString();
		}

	}
}

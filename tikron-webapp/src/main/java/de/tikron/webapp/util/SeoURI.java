/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.util;

import java.util.ArrayList;
import java.util.List;

import de.tikron.webapp.util.URIBuilder.NameValuePair;

/**
 * URI with view name and request parameters. Parameter values of type String will be converter in a search engine optimized form.
 *
 * @author Titus Kruse
 * @since 28.01.2015
 */
public class SeoURI {
	
	private final String viewName;
	
	private final List<NameValuePair> parameters;
	
	private String uri;

	public SeoURI(String viewName) {
		this(viewName, new ArrayList<NameValuePair>());
	}
	
	public SeoURI(String viewName, String paramName, Object paramValue) {
		this(viewName);
		addParameter(paramName, paramValue);
	}

	public SeoURI(String viewName, List<NameValuePair> parameters) {
		this.viewName = viewName;
		this.parameters = parameters;
	}
	
	public void addParameter(String name, Object value) {
		parameters.add(new NameValuePair(name, value));
	}

	public String getViewName() {
		return viewName;
	}

	public List<NameValuePair> getParameters() {
		return parameters;
	}

	public String getUri() {
		if (uri == null) {
			List<NameValuePair> paramsAdjusted = new ArrayList<NameValuePair>(parameters.size());
			for (NameValuePair parameter : parameters) {
				Object value = parameter.getValue();
				if (value instanceof String) {
					value = SeoUtils.getInstance().adjustRequestParameterValue((String) value);
				}
				paramsAdjusted.add(new NameValuePair(parameter.getName(), value));
			}
			uri = URIBuilder.getInstance().fromViewName(viewName, paramsAdjusted);
		}
		return uri;
	}

	@Override
	public String toString() {
		return getUri();
	}

}

/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.webapp.util.tag;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * Basisklasse für HTML-Tags. Stellt gemeinsame Attribute zur Verfügung.
 * 
 * @since 25.12.2008
 * @author Titus Kruse
 */
public abstract class BaseTag extends TagSupport {

	private static final long serialVersionUID = 463455574521820078L;
	
	private String styleClass;

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/**
	 * Umgibt den übergebenen Text mit einem SPAN-Tag, falls Attribut id oder styleClass gesetzt sind.
	 * 
	 * @param value Der HTML-Text.
	 * @return Der resultierende HTML-Text.
	 */
	protected String renderSpanTag(String value) {
		if (getId() != null || getStyleClass() != null) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("<span");
			if (getId() != null)
				buffer.append(" id=\"" + getId() + "\"");
			if (getStyleClass() != null)
				buffer.append(" class=\"" + getStyleClass() + "\"");
			buffer.append(">");
			buffer.append(value);
			buffer.append("</span>");
			return buffer.toString();
		} else {
			return value;
		}
	}

}

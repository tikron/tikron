/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.webapp.util.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

/**
 * HTML-Tag zum Formatieren von Textbereichen.
 * 
 * @date 21.12.2008
 * @author Titus Kruse
 */
public class TextArea extends BaseTag implements Tag {

	private String value;

	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			String htmlText = getValue().replace("\n", "<br/>");
			out.print(renderSpanTag(htmlText));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

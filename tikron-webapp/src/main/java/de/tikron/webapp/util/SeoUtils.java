/**
 * Copyright (c) 2014 by Titus Kruse.
 */
package de.tikron.webapp.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

import de.tikru.commons.util.FormattedTextCompiler;

/**
 * Utility class providing some SEO methods.
 *
 * @author Titus Kruse
 * @since 16.03.2014
 */
public class SeoUtils {

	private static final String[] FROM_STRINGS = new String[] { " ", "ä", "ö", "ü", "ß", "?", "&", "=", "," };
	private static final String[] TO_STRINGS = new String[] { "-", "ae", "oe", "ue", "ss", "", "", "", "" };
	
	private static SeoUtils INSTANCE = new SeoUtils();

	private SeoUtils() {
	}
	
	public static SeoUtils getInstance() {
		return INSTANCE;
	}

	public String adjustRequestParameterValue(final String value) {
		String outStr = value.toLowerCase();
		outStr = outStr.replaceAll(" - ", "-");
		outStr = StringUtils.replaceEach(outStr, FROM_STRINGS, TO_STRINGS);
		return outStr;
	}

	public String formatMetaDescription(String text) {
		// Remove HTML and crop to maximum length.
		return WordUtils.abbreviate(removeTextCommands(text), 120, -1, "...");
	}

	/*
	private static String removeHtml(String text) {
		// see http://stackoverflow.com/questions/240546/removing-html-from-a-java-string
		return text == null ? null : text.replaceAll("\\<.*?>", "");
	}
	*/
	
	private static String removeTextCommands(String text) {
		return FormattedTextCompiler.getInstance().compile(text, FormattedTextCompiler.DISCARD_ALL);
	}

}

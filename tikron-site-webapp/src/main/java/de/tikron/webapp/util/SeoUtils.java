/**
 * Copyright (c) 2014 by Titus Kruse.
 */
package de.tikron.webapp.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

/**
 * Utility class providing some SEO methods.
 *
 * @date 16.03.2014
 * @author Titus Kruse
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
		// Remove HTML (http://stackoverflow.com/questions/240546/removing-html-from-a-java-string) and crop to maximum
		// length.
		return WordUtils.abbreviate(removeHtml(text), 120, -1, "...");
	}

	private static String removeHtml(String text) {
		return text == null ? null : text.replaceAll("\\<.*?>", "");
	}

}

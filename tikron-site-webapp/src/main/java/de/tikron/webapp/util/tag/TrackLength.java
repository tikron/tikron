/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.webapp.util.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

/**
 * HTML-Tag zur Ausgabe der Spieldauer von Titeln.
 *
 * @date 25.12.2008
 * @author Titus Kruse
 */
public class TrackLength extends BaseTag implements Tag {

	/**
	 * Zeitfaktoren (Sekunden, Minuten, Stunden, Dummy)
	 */
	private static final int TIME_FACTOR[] = { 60, 60, 24, 99 };

	/**
	 * Trennzeichen für Bestandteile der Zeit
	 */
	private static final String TIME_SEPARATOR = ":";

	private Integer value;

	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			String convertedTrackLength = convert(getValue());
			out.print(renderSpanTag(convertedTrackLength));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	private String convert(Integer value) {
		StringBuffer result = new StringBuffer();
		int seconds = value.intValue();
		int p = 0;
		boolean moreElements;
		do {
			// Sekunde, Minute usw. ermitteln
			int mod = seconds % TIME_FACTOR[p];
			// Prüfen, ob nach dem aktuellen Element noch weitere folgen
			moreElements = (p == 0 || seconds / TIME_FACTOR[p] > 0);
			// Sofern ein Element folgt, aktuellen Wert mit führender Null
			// einfügen
			String format = (moreElements ? "%02d" : "%d");
			String part = String.format(format, mod);
			// Falls bereits Teile vorangegangen sind, Zeittrennzeichen
			// einfügen
			if (p > 0)
				result.insert(0, TIME_SEPARATOR);
			// Teilzeichenfolge einfügen
			result.insert(0, part);
			// Sekunden durch Zeitfaktor dividieren
			seconds /= TIME_FACTOR[p];
			// Nächster Zeitfaktor
			++p;
			// Solange innerhalb Faktorentabelle und Sekunden vorhanden
			// sind. Mindestens jedoch Sekunden und Minuten.
		} while (p < TIME_FACTOR.length && moreElements);
		return result.toString();
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}

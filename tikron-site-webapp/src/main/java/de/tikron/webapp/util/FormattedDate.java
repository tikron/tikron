/**
 * Copyright (c) 2017 by Titus Kruse.
 */
package de.tikron.webapp.util;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

/**
 * A class representing a formatted date according to the given localization context.
 *
 * @date 01.07.2017
 * @author Titus Kruse
 */
public class FormattedDate extends AbstractFormattedDateTime {
	
	public FormattedDate(TemporalAccessor date, LocalizationContext context) {
		super(date, context);
	}

	public FormattedDate(String text, LocalizationContext context) {
		super(text, context);
	}

	@Override
	protected DateTimeFormatter getFormatter(FormatStyle style) {
		return context.getDateFormatter(style);
	}
}

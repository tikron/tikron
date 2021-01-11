/**
 * Copyright (c) 2017 by Titus Kruse.
 */
package de.tikron.webapp.util;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

/**
 * A class representing a formatted time according to the given localization context.
 *
 * @since 01.07.2017
 * @author Titus Kruse
 */
public class FormattedTime extends AbstractFormattedDateTime {
	
	public FormattedTime(TemporalAccessor date, LocalizationContext context) {
		super(date, context);
	}

	public FormattedTime(String text, LocalizationContext context) {
		super(text, context);
	}

	@Override
	protected DateTimeFormatter getFormatter(FormatStyle style) {
		return context.getTimeFormatter(style);
	}
}

/**
 * Copyright (c) 2017 by Titus Kruse.
 */
package de.tikron.webapp.util;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

/**
 * Base class for Dates and Times representing a human readable format. 
 *
 * @since 01.07.2017
 * @author Titus Kruse
 */
public abstract class AbstractFormattedDateTime {

	protected final TemporalAccessor temporal;
	
	protected final LocalizationContext context;

	protected AbstractFormattedDateTime(TemporalAccessor temporal, LocalizationContext context) {
		this.temporal = temporal;
		this.context = context;
	}
	
	protected AbstractFormattedDateTime(String text, LocalizationContext context) {
		this.context = context;
		this.temporal = getFormatter(FormatStyle.MEDIUM).parse(text);
	}
	
	protected abstract DateTimeFormatter getFormatter(FormatStyle style);

	public TemporalAccessor getTemporalAccessor() {
		return temporal;
	}

	public LocalizationContext getContext() {
		return context;
	}
	
	public FormattedDate parse(CharSequence text, FormatStyle style, LocalizationContext context) {
		return new FormattedDate(getFormatter(style).parse(text), context);
	}

	@Override
	public String toString() {
		return getFormatter(FormatStyle.MEDIUM).format(temporal);
	}

}

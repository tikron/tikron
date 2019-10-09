/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.util;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Declares some methods providing formatters used to convert from/to model objects and localozed external representation.
 *
 * @date 13.02.2015
 * @author Titus Kruse
 */
public interface LocalizationConverter {

	/**
	 * Returns a date formatter with medium style according to the context locale.
	 * 
	 * @return The date formatter.
	 */
	public DateTimeFormatter getDateFormatter();

	/**
	 * Returns a date formatter with given style according to the context locale.
	 * 
	 * @param The FormatStyle of the formatter.
	 * @return The date formatter.
	 */
	public DateTimeFormatter getDateFormatter(FormatStyle style);

	/**
	 * Returns a time formatter with medium style according to the context locale.
	 * 
	 * @return The time formatter.
	 */
	public DateTimeFormatter getTimeFormatter();

	/**
	 * Returns a time formatter with medium style according to the context locale.
	 * 
	 * @param The FormatStyle of the formatter.
	 * @return The time formatter.
	 */
	public DateTimeFormatter getTimeFormatter(FormatStyle style);
	
	
	/**
	 * Converts a date and time in server time zone into the presentation layers local time zone.
	 *   
	 * @param localDateTime local time.
	 * @return remote time.
	 */
	public ZonedDateTime convertToZonedDateTime(LocalDateTime localDateTime);

}

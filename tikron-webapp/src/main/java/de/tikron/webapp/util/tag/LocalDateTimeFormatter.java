/**
 * Copyright (c) 2017 by Titus Kruse.
 */
package de.tikron.webapp.util.tag;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.commons.lang3.time.DurationFormatUtils;

/**
 * Date and Time formatters for my custom JSTL functions.
 * 
 * @see https://stackoverflow.com/questions/35606551/jstl-localdatetime-format
 *
 * @since 29.07.2017
 * @author Titus Kruse
 * @author BalusC
 */
public final class LocalDateTimeFormatter {

	private LocalDateTimeFormatter() {
	};

	public static String formatDateTime(LocalDateTime localDateTime, String pattern, Locale locale) {
		return localDateTime.format(DateTimeFormatter.ofPattern(pattern, locale));
	}

	public static String formatDateTime(LocalDateTime localDateTime, String pattern) {
		return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
	}

	public static String formatDate(LocalDate localDate, String pattern, Locale locale) {
		return localDate.format(DateTimeFormatter.ofPattern(pattern, locale));
	}

	public static String formatDate(LocalDate localDate, String pattern) {
		return localDate.format(DateTimeFormatter.ofPattern(pattern));
	}

	public static String formatTime(LocalTime localTime, String pattern, Locale locale) {
		return localTime.format(DateTimeFormatter.ofPattern(pattern, locale));
	}

	public static String formatTime(LocalTime localTime, String pattern) {
		return localTime.format(DateTimeFormatter.ofPattern(pattern));
	}
	
	public static String formatDuration(Duration duration, String pattern) {
		return DurationFormatUtils.formatDuration(duration.toMillis(), pattern);
	}
}

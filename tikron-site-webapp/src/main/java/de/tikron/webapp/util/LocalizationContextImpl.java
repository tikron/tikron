/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

/**
 * Default implementation of {@link de.tikron.webapp.util.LocalizationContext}
 * 
 * @date 13.02.2015
 * @author Titus Kruse
 */
public class LocalizationContextImpl implements LocalizationContext {
	
	@SuppressWarnings("unused")
	private HttpServletRequest request;
	
	private MessageSource messageSource;
	
	private Map<FormatStyle, DateTimeFormatter> dateFormatters = new HashMap<FormatStyle, DateTimeFormatter>();
	
	private Map<FormatStyle, DateTimeFormatter> timeFormatters = new HashMap<FormatStyle, DateTimeFormatter>();

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public String getMessage(MessageSourceResolvable arg0) throws NoSuchMessageException {
		return messageSource.getMessage(arg0, getLocale());
	}

	@Override
	public String getMessage(String arg0, Object[] arg1) throws NoSuchMessageException {
		return messageSource.getMessage(arg0, arg1, getLocale());
	}

	@Override
	public String getMessage(String arg0, Object[] arg1, String arg2) {
		return messageSource.getMessage(arg0, arg1, arg2, getLocale());
	}

	@Override
	public Locale getLocale() {
//		return this.request.getLocale();
		// Use always Germany because no english translation as fallback supported yet.  
		return Locale.GERMANY;
	}

	@Override
	public ZoneId getTimeZone() {
		// Use always German time zone unless it cannot be selected by user.
		return ZoneId.of("Europe/Berlin");
	}
	
	@Override
	public Instant getSystemTime() {
		return Instant.now();
	}

	@Override
	public LocalDateTime getLocalSystemTime() {
		return LocalDateTime.ofInstant(getSystemTime(), getTimeZone());
	}

	@Override
	public DateTimeFormatter getDateFormatter() {
		return getDateFormatter(FormatStyle.MEDIUM);
	}

	@Override
	public DateTimeFormatter getDateFormatter(FormatStyle style) {
		synchronized (dateFormatters) {
			if (!dateFormatters.containsKey(style)) {
				dateFormatters.put(style, DateTimeFormatter.ofLocalizedDate(style).withLocale(getLocale()));
			}
		}
		return dateFormatters.get(style);
	}

	@Override
	public FormattedDate getFormattedCurrentDate() {
		return new FormattedDate(getLocalSystemTime().toLocalDate(), this); 
	}

	@Override
	public DateTimeFormatter getTimeFormatter() {
		return getTimeFormatter(FormatStyle.MEDIUM);
	}

	@Override
	public DateTimeFormatter getTimeFormatter(FormatStyle style) {
		synchronized (timeFormatters) {
			if (!timeFormatters.containsKey(style)) {
				timeFormatters.put(style, DateTimeFormatter.ofLocalizedTime(style).withLocale(getLocale()));
			}
		}
		return timeFormatters.get(style);
	}

	@Override
	public FormattedTime getFormattedCurrentTime() {
		return new FormattedTime(getLocalSystemTime().toLocalTime(), this); 
	}
	
	/**
	 * A class representing a formatted date according to the given localization context.
	 *
	 * @date 09.02.2015
	 * @author Titus Kruse
	 */
	public class FormattedDate {
		
		private final LocalDate date;
		
		private final LocalizationContext context;

		public FormattedDate(LocalDate date, LocalizationContext context) {
			this.date = date;
			this.context = context;
		}

		@Override
		public String toString() {
			return context.getDateFormatter().format(date);
		}
	}
	
	/**
	 * A class representing a formatted time according to the given localization context.
	 *
	 * @date 09.02.2015
	 * @author Titus Kruse
	 */
	public class FormattedTime {
		
		private final LocalTime time;
		
		private final LocalizationContext context;

		public FormattedTime(LocalTime time, LocalizationContext context) {
			this.time = time;
			this.context = context;
		}

		@Override
		public String toString() {
			return context.getTimeFormatter().format(time);
		}
	}
	
	public class DateConverter {
		
		private final DateTimeFormatter formatter;
		
		public DateConverter(DateTimeFormatter formatter) {
			this.formatter = formatter;
		}

		public String toString(TemporalAccessor temporal) {
			return formatter.format(temporal);
		}
		
		public TemporalAccessor fromString(String text) {
			return formatter.parse(text);
		}
	}

}

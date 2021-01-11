/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.servlet.LocaleResolver;

/**
 * Default implementation of {@link de.tikron.webapp.util.LocalizationContext}
 * 
 * @since 13.02.2015
 * @author Titus Kruse
 */
public class LocalizationContextImpl implements LocalizationContext {
	
	@Autowired
	private HttpServletRequest request;
	
	private MessageSource messageSource;
	
	private LocaleResolver localeResolver;

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
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
		return localeResolver.resolveLocale(request);
		// Use always Germany because no english translation as fallback supported yet.  
		// return Locale.GERMANY;
	}

	@Override
	public ZoneId getTimeZone() {
		// Use always German time zone unless it cannot be selected by the user.
		return ZoneId.of("Europe/Berlin");
		//return ZoneId.of("Australia/Broken_Hill");
	} 
	
	@Override
	public Instant getSystemTime() {
		return Instant.now();
	}

	@Override
	public LocalDateTime getLocalSystemTime() {
		return LocalDateTime.ofInstant(getSystemTime(), ZoneId.systemDefault());
	}

	@Override
	public FormattedDate getFormattedDate(TemporalAccessor temporalAccessor) {
		return new FormattedDate(temporalAccessor, this); 
	}

	@Override
	public FormattedTime getFormattedTime(TemporalAccessor temporalAccessor) {
		return new FormattedTime(temporalAccessor, this); 
	}

	@Override
	public FormattedDate getFormattedCurrentDate() {
		return getFormattedDate(getLocalSystemTime().toLocalDate()); 
	}

	@Override
	public FormattedTime getFormattedCurrentTime() {
		return getFormattedTime(getLocalSystemTime().toLocalTime()); 
	}

	@Override
	public ZonedDateTime convertToZonedDateTime(LocalDateTime localDateTime) {
		return ZonedDateTime.of(localDateTime, ZoneId.systemDefault()).withZoneSameInstant(getTimeZone());
	}

	@Override
	public DateTimeFormatter getDateFormatter() {
		return getDateFormatter(FormatStyle.MEDIUM);
	}

	@Override
	public DateTimeFormatter getDateFormatter(FormatStyle style) {
		return DateTimeFormatter.ofLocalizedDate(style).withLocale(getLocale()).withZone(getTimeZone());
	}

	@Override
	public DateTimeFormatter getTimeFormatter() {
		return getTimeFormatter(FormatStyle.MEDIUM);
	}

	@Override
	public DateTimeFormatter getTimeFormatter(FormatStyle style) {
		return DateTimeFormatter.ofLocalizedTime(style).withLocale(getLocale()).withZone(getTimeZone());
	}
}

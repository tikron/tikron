package de.tikron.webapp.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

/**
 * Context providing localized messages and formatted dates and times based on locale and time zone properties.
 * 
 * Note: This type should be replaced by Spring Web Flow {@link org.springframework.web.servlet.support.RequestContext} and {@link org.springframework.core.convert.ConversionService} later.
 *
 * @date 09.02.2015
 * @author Titus Kruse
 * 
 * @see http://java.dzone.com/articles/deeper-look-java-8-date-and
 */
public interface LocalizationContext extends LocalizationConverter {

	public String getMessage(MessageSourceResolvable arg0) throws NoSuchMessageException;

	public String getMessage(String arg0, Object[] arg1) throws NoSuchMessageException;

	public String getMessage(String arg0, Object[] arg1, String arg2);

	/**
	 * Returns the locale based on HTTP servlet request and locale resolver.
	 *  
	 * @return The locale.
	 */
	public Locale getLocale();

	/**
	 * Returns the time zone to use for dates and times in the presentation layer.
	 * 
	 * @return The zone ID.
	 */
	public ZoneId getTimeZone();

	public Instant getSystemTime();

	public LocalDateTime getLocalSystemTime();
	
	public FormattedDate getFormattedDate(TemporalAccessor temporalAccessor);
	
	public FormattedTime getFormattedTime(TemporalAccessor temporalAccessor);

	/**
	 * Returns the current system date formatted according to context properties.  
	 * 
	 * @return The formatted date.
	 */
	public FormattedDate getFormattedCurrentDate();

	/**
	 * Returns the current system time formatted according to context properties.  
	 * 
	 * @return The formatted time.
	 */
	public FormattedTime getFormattedCurrentTime();

}
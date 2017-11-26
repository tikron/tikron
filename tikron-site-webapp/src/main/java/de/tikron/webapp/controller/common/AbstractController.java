/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.controller.common;

import java.net.URL;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

//import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import de.tikron.webapp.facade.common.EntityBeanHelper;
import de.tikron.webapp.service.common.FileArchiveService;
import de.tikron.webapp.service.common.ImageService;
import de.tikron.webapp.util.FormattedDate;
import de.tikron.webapp.util.FormattedTime;
import de.tikron.webapp.util.LocalizationContext;

/**
 * Base class for all controllers providing helper methods.
 * 
 * @date 26.12.2009
 * @author Titus Kruse
 */
public abstract class AbstractController {

	protected WebApplicationContext webApplicationContext;

	protected EntityBeanHelper entityBeanHelper;
	
	protected LocalizationContext localizationContext;

	protected ImageService imageService;

	protected FileArchiveService fileArchiveService;
	
//	protected Mapper beanMapper;

	private static final DateTimeFormatter YEAR_FORMATTER = DateTimeFormatter.ofPattern("yyyy");

	/**
	 * Returns a message text for the given message key and message arguments.
	 * 
	 * @param key The message key.
	 * @param args The replacement arguments.
	 * @return The message text.
	 */
	protected String getMessage(String key, Object[] args) throws NoSuchMessageException {
		return getLocalizationContext().getMessage(key, args);
	}

	/**
	 * Returns a message text for the given message key.
	 * 
	 * @param key The message key.
	 * @return The message text.
	 */
	protected String getMessage(String key) throws NoSuchMessageException {
		return getMessage(key, null);
	}

	protected String getActionMessageKey() {
		return getActionPath().substring(1).replace("/", ".");
	}

	protected String getActionMessageKey(String qualifier) {
		return getActionMessageKey() + "." + qualifier;
	}

	// protected String getActionText(String key) {
	// return getText()
	// }

	/**
	 * Liefert den HTTP-Servlet-Request.
	 * 
	 * @return Der HTTP-Servlet-Request.
	 */
	@ModelAttribute("httpServletRequest")
	public HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * Liefert die URL zur Web-Application.
	 * 
	 * @return Die URL zur Web-Application.
	 */
	@ModelAttribute("servletContextPath")
	public String getServletContextPath() {
		return getHttpServletRequest().getScheme() + "://" + getHttpServletRequest().getServerName()
				+ getHttpServletRequest().getContextPath();
	}

	/**
	 * Liefert die komplette Anfrage-URI inklusive Parameter
	 * 
	 * @return Die Anfrage-URI.
	 */
	@ModelAttribute("fullRequestURI")
	public String getFullRequestURI() {
		HttpServletRequest httpServletRequest = getHttpServletRequest();
		String queryString = httpServletRequest.getQueryString();
		return httpServletRequest.getRequestURL().toString() + (queryString != null ? "?" + queryString : "");
	}

	/**
	 * Checks whether the request is an Ajax request.
	 *  
	 * @return true, if Ajax.
	 */
	@ModelAttribute("xmlHttpRequest")
	public boolean isXmlHttpRequest() {
		String requestedWithHeader = getHttpServletRequest().getHeader("X-Requested-With");
		return "XMLHttpRequest".equals(requestedWithHeader);
	}
	
	/**
	 * Checks whether the request was submitted by an Internet Explorer version lower then 9.
	 *   
	 * @return True, if MS IE 6, 7 or 8.
	 */
	@ModelAttribute("browserLowerIE9")
	public boolean isLowerIE9() {
		String userAgent = getHttpServletRequest().getHeader("User-Agent");
		return userAgent != null && (userAgent.contains("MSIE 8.0") || userAgent.contains("MSIE 7.0") || userAgent.contains("MSIE 6.0"));
	}

	@ModelAttribute("actionPath")
	public String getActionPath() {
		String requestURI = getHttpServletRequest().getRequestURI();
		int pos = getHttpServletRequest().getContextPath().length();
		String actionPath = requestURI.substring(pos);
		pos = actionPath.lastIndexOf(".");
		if (pos > -1) {
			actionPath = actionPath.substring(0, pos);
		}
		return actionPath;
	}

	/**
	 * Provides the URL to the image server.
	 * 
	 * @return The URL of the image server.
	 */
	@ModelAttribute("imageServerUrl")
	public URL getImageServerUrl() {
		return imageService.getImageServerUrl(getHttpServletRequest().isSecure());
	}

	/**
	 * Provides the URL to the file archive.
	 * 
	 * @return The URL of the file archive
	 */
	@ModelAttribute("fileArchiveUrl")
	public URL getFileArchiveUrl() {
		return fileArchiveService.getArchiveUrl(getHttpServletRequest().isSecure());
	}

	protected LocalizationContext getLocalizationContext() {
//		return LocalizationContext bean = (LocalizationContext) webApplicationContext.getBean("localizationContext");
		return localizationContext;
	}

	/**
	 * Returns the locale to use in the front end.
	 *  
	 * @return The locale.
	 */
	@ModelAttribute("locale")
	public Locale getLocale() {
		return getLocalizationContext().getLocale();
	}
	
	/**
	 * Returns the time zone to use for dates and times in the front end.
	 * 
	 * @return The zone ID.
	 */
	@ModelAttribute("timeZone")
	public ZoneId getTimeZone() {
		return getLocalizationContext().getTimeZone();
	}

	/**
	 * Returns the current date. The date will be formatted with FormatStyle.MEDIUM and according to the current locale.  
	 * 
	 * @return The formatted date.
	 */
	@ModelAttribute("currentDate")
	public FormattedDate getFormattedCurrentDate() {
		return getLocalizationContext().getFormattedCurrentDate(); 
	}

	/**
	 * Returns the current time. The time will be formatted with FormatStyle.MEDIUM and according to the current locale.  
	 * 
	 * @return The formatted time.
	 */
	@ModelAttribute("currentTime")
	public FormattedTime getFormattedCurrentTime() {
		return getLocalizationContext().getFormattedCurrentTime(); 
	}
	
	@ModelAttribute("copyrightYear")
	public String getCopyrightYear() {
		return YEAR_FORMATTER.format(getLocalizationContext().getLocalSystemTime()); 
	}

	@Autowired
	public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
		this.webApplicationContext = webApplicationContext;
	}

	@Autowired
	public void setEntityBeanHelper(EntityBeanHelper entityBeanHelper) {
		this.entityBeanHelper = entityBeanHelper;
	}

	@Autowired
	public void setLocalizationContext(LocalizationContext localizationContext) {
		this.localizationContext = localizationContext;
	}

	@Autowired
	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	@Autowired
	public void setFileArchiveService(FileArchiveService fileArchiveService) {
		this.fileArchiveService = fileArchiveService;
	}

//	@Autowired
//	public void setBeanMapper(Mapper beanMapper) {
//		this.beanMapper = beanMapper;
//	}

}

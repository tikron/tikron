/**
 * Copyright (c) 2017 by Titus Kruse.
 */
package de.tikron.webapp.controller.common;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * Interceptor using a Spring LocaleResolver and validating, if the locale/language is supported. 
 *
 * @date 03.12.2017
 * @author Titus Kruse
 */
@Component
public class LocaleInterceptor extends AbstractInterceptor implements HandlerInterceptor {

	private static Logger logger = LogManager.getLogger();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		if (localeResolver == null) {
			throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
		}
		Locale locale = localeResolver.resolveLocale(request);
		if (!locale.getLanguage().equals(Locale.GERMAN.getLanguage())) {
			logger.info("Unsupported language {}.", locale);
			response.sendRedirect(request.getContextPath() + "/displayUnsupportedLanguage.html?lang=us");
	    return false;
		}
		return true;
	}

}

/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import de.tikron.webapp.controller.common.ResourceNotFoundException;

/**
 * Exception handler with additional logging and preparing the error page content. 
 *
 * @date 18.01.2015
 * @author Titus Kruse
 */
public class ExceptionHandler extends SimpleMappingExceptionResolver implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;
	
	public ExceptionHandler() {
		// Enable logging by providing the name of the logger to use
		setWarnLogCategory(ExceptionHandler.class.getName());
	}

	@Override
	public String buildLogMessage(Exception e, HttpServletRequest req) {
		return "MVC exception: " + e.getLocalizedMessage() + "\n\tRequest URL: " + req.getRequestURL() + (req.getQueryString() == null ? "" : "?" + req.getQueryString());
	}

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {
		// Call super method to get the ModelAndView
		ModelAndView modelAndView = super.doResolveException(request, response, handler, exception);
		//
		if (exception instanceof ResourceNotFoundException) {
			response.setStatus(404);
		}
		// Add some page meta data
		// TODO Redundant with preparation in AbstractPageController and AbstractController 
		modelAndView.addObject("pageTitle", applicationContext.getMessage("title", null, null));
		modelAndView.addObject("currentDate", new Date());
		return modelAndView;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}

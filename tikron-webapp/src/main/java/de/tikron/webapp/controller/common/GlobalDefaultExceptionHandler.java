/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.controller.common;

import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import de.tikron.webapp.util.LocalizationContext;

/**
 * Exception handler in modern annotation style. 
 *
 * @date 19.01.2015
 * @author Titus Kruse
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
	
	private LocalizationContext localizationContext;

	private static final DateTimeFormatter YEAR_FORMATTER = DateTimeFormatter.ofPattern("yyyy");
	
	/**
	 * Special handler for exceptions caused by a missing resource. This handler results into a view, showing the user an
	 * appropriate message and a search engine bot a HTTP 404.
	 * 
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView handleResourceNotFound(HttpServletRequest request, Exception e) {
		logger.warn(buildLogMessage(request, e));
		return prepareErrorView(ViewConstants.DISPLAY_APPLICATION_ERROR, e, request);
	}
	
	/**
	 * General Exeption handler for runtime exceptions thrown by this application or bad request caused by wrong or missing paramters for example.
	 * 
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ApplicationException.class, ServletRequestBindingException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ModelAndView handleApplicationException(HttpServletRequest request, Exception e) {
		logger.warn(buildLogMessage(request, e));
		return prepareErrorView(ViewConstants.DISPLAY_APPLICATION_ERROR, e, request);
	}
	
	/**
	 * Default exception handler for all other checked and unchecked eceptions.
	 *  
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView defaultHandler(HttpServletRequest request, Exception e) {
		logger.error(buildLogMessage(request, e), e);
		return prepareErrorView(ViewConstants.DISPLAY_SYSTEM_ERROR, request);
	}

	/**
	 * Builds a message for logging output including the request URL for debugging purpose.
	 * 
	 * @param request
	 * @param e
	 *  
	 * @return
	 */
	private String buildLogMessage(HttpServletRequest request, Exception e) {
		StringBuilder sb = new StringBuilder();
		if (e instanceof ApplicationException) {
			sb.append("Application exception: ");
			sb.append(e.getLocalizedMessage());
		} else {
			sb.append("System exception: ");
			sb.append(e.getLocalizedMessage());
		}
		sb.append("\n\tRequest URL: ");
		sb.append(request.getRequestURL());
		if (request.getQueryString() != null) {
			sb.append("?");
			sb.append(request.getQueryString());
		}
		return sb.toString();
	}
	
	/**
	 * Prepares the ModelAndView for all types of error views in this application.
	 *  
	 * @param viewName
	 * @param request
	 * @return
	 */
	private ModelAndView prepareErrorView(String viewName, HttpServletRequest request) {
		return prepareErrorView(viewName, null, request);
	}

	/**
	 * Prepares the ModelAndView for all types of error views caused by an exception.
	 *  
	 * @param viewName
	 * @param e 
	 * @param request
	 * @return
	 */
	private ModelAndView prepareErrorView(String viewName, Exception e, HttpServletRequest request) {
		// TODO Redundant with preparation in AbstractPageController and AbstractController
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pageTitle", localizationContext.getMessage("title", null));
		modelAndView.addObject("copyrightYear", YEAR_FORMATTER.format(localizationContext.getLocalSystemTime()));
		if (e != null && e instanceof ApplicationException) {
			ApplicationException ae = (ApplicationException) e;
			modelAndView.addObject("messageKey", ae.getKey());
			modelAndView.addObject("messageParam", ae.getParam());
		}
		modelAndView.setViewName(viewName);
		return modelAndView;
	}

	@Autowired
	public void setLocalizationContext(LocalizationContext localizationContext) {
		this.localizationContext = localizationContext;
	}

}

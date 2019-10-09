/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.controller.common;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * Abstract base controller for all servlets containing an input form processed by Ajax request.
 *
 * @date 10.05.2015
 * @author Titus Kruse
 */
public class AbstractAjaxFormController extends AbstractController {

	/**
	 * Register property editors.
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Convert empty to null
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

}

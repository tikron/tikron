/**
 * Copyright (c) 2014 by Titus Kruse.
 */
package de.tikron.webapp.controller.common;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * Abstract base controller for all servlets containing an input form.
 *
 * @author Titus Kruse
 * @since 12.12.2014
 */
public class AbstractFormController extends AbstractPageController {

	/**
	 * Register property editors.
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Convert empty to null
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

}

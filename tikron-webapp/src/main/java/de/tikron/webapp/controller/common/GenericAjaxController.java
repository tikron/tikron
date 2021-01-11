/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Common controller for all simple ajax responses.
 *
 * @author Titus Kruse
 * @since 20.11.2014
 */
@Controller
@RequestMapping("/*display*Ajax.html")
public class GenericAjaxController extends AbstractController {

	@RequestMapping(method = RequestMethod.GET)
	public void doGet() {
	}

}

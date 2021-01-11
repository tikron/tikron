/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Common controller for all pages, displaying simple content.
 *
 * @author Titus Kruse
 * @since 27.12.2012
 */
@Controller
@RequestMapping("/*display*.html")
public class GenericPageController extends AbstractPageController {

	@RequestMapping(method = RequestMethod.GET)
	public void doGet() {
	}

}

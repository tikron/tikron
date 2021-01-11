/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.tikron.webapp.controller.common.AbstractPageController;
import de.tikron.webapp.util.RobotsDirective;

/**
 * Zeigt das Impressum an.
 * 
 * @author Titus Kruse
 * @since 28.12.2012
 */
@Controller
@RequestMapping("/displayImprint.html")
public class DisplayImprintController extends AbstractPageController {

	@RequestMapping(method = RequestMethod.GET)
	public void doGet() {
	}

	@Override
	public String getRobotsDirective() {
		return RobotsDirective.NOINDEX.toString();
	}

}

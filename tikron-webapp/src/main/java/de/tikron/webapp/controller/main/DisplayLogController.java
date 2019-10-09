/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.tikron.webapp.controller.common.AbstractPageController;
import de.tikron.webapp.controller.common.ViewConstants;
import de.tikron.webapp.util.RobotsDirective;

/**
 * Zeigt die Historie an.
 * 
 * @date 28.12.2012
 * @author Titus Kruse
 */
@Controller
@RequestMapping("/displayLog.html")
public class DisplayLogController extends AbstractPageController {

	@RequestMapping(method = RequestMethod.GET)
	public String doGet() {
		if (isXmlHttpRequest()) {
			return ViewConstants.DISPLAY_LOG_AJAX;
		} else {
			return ViewConstants.DISPLAY_LOG;
		}
	}

	@Override
	public String getRobotsDirective() {
		return RobotsDirective.NOINDEX.toString();
	}

}

/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.tikron.webapp.controller.common.AbstractPageController;

/**
 * Zeigt die Historie als Ajax-Response an. Der Controller erweitert AbstractPageController, damit der Page Title für
 * das Piwik Tracking zur Verfügung steht.
 * 
 * @date 14.12.2014
 * @author Titus Kruse
 */
@Controller
@RequestMapping("/displayLogAjax.html")
public class DisplayLogAjaxController extends AbstractPageController {

	@RequestMapping(method = RequestMethod.GET)
	public void doGet() {
	}

	@Override
	protected String getSubTitle() {
		return getMessage("displayLog.subTitle");
	}

}

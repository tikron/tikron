/**
 * Copyright (c) 2019 by Titus Kruse.
 */
package de.tikron.webapp.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.tikron.webapp.controller.common.AbstractPageController;
import de.tikron.webapp.util.RobotsDirective;

/**
 * Zeigt Informationen zum Datenschutz an.
 * 
 * @since 28.02.2019
 * @author Titus Kruse
 */
@Controller
@RequestMapping("/displayPrivacyPolicy.html")
public class DisplayPrivacyPolicyController extends AbstractPageController {

	@RequestMapping(method = RequestMethod.GET)
	public void doGet() {
	}

	@Override
	public String getRobotsDirective() {
		return RobotsDirective.NOINDEX.toString();
	}

}

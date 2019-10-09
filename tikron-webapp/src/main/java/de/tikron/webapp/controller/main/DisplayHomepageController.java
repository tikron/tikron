/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.controller.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.tikron.persistence.model.misc.Teaser;
import de.tikron.webapp.controller.common.AbstractPageController;
import de.tikron.webapp.model.misc.TeaserEntityBean;
import de.tikron.webapp.service.misc.TeaserService;

/**
 * Startseite.
 *
 * @date 27.12.2012
 * @author Titus Kruse
 */
@Controller
@RequestMapping("/displayHomepage.html")
public class DisplayHomepageController extends AbstractPageController {
	
	private TeaserService teaserService;

	@RequestMapping(method = RequestMethod.GET)
	public void doGet(ModelMap model) {
		// Add teasers to model
		List<Teaser> teasers = teaserService.getTeasers();
		List<TeaserEntityBean> teaserBeans = entityBeanHelper.toList(TeaserEntityBean.NAME, teasers);
		model.addAttribute("teasers", teaserBeans);
	}

	@Autowired
	public void setTeaserService(TeaserService teaserService) {
		this.teaserService = teaserService;
	}

}

/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.controller.misc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.tikron.persistence.model.misc.WebRecommendation;
import de.tikron.webapp.controller.common.AbstractPageController;
import de.tikron.webapp.model.misc.WebRecommendationEntityBean;
import de.tikron.webapp.service.misc.WebRecommendationService;
import de.tikron.webapp.util.RobotsDirective;

/**
 * Shows a list of web recommendations.
 *
 * @since 30.12.2012
 * @author Titus Kruse
 */
@Controller
@RequestMapping("/webRecommendations/displayEntries.html")
public class DisplayWebRecommendationsController extends AbstractPageController {
	
	private WebRecommendationService webRecommendationService;

	@RequestMapping(method = RequestMethod.GET)
	public void doGet(ModelMap model) {
		List<WebRecommendation> webRecommendations = webRecommendationService.getWebRecommendations();
		List<WebRecommendationEntityBean> webRecommendationBeans = entityBeanHelper.toList(WebRecommendationEntityBean.NAME, webRecommendations);
		model.addAttribute("webRecommendations", webRecommendationBeans);
	}

	@Override
	public String getRobotsDirective() {
		return RobotsDirective.NOINDEX.toString();
	}

	@Autowired
	public void setWebRecommendationService(WebRecommendationService webRecommendationService) {
		this.webRecommendationService = webRecommendationService;
	}

}

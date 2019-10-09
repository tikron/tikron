/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.bean.misc;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import de.tikron.manager.bean.common.GenericCRUDBean;
import de.tikron.manager.service.misc.WebRecommendationService;
import de.tikron.persistence.model.misc.WebRecommendation;

/**
 * Backing bean for CRUD operations on a single web recommendation.
 * 
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class WebRecommendationDetailBean extends GenericCRUDBean<WebRecommendation, Long> {

	@ManagedProperty(value = "#{webRecommendationService}")
	private WebRecommendationService webRecommendationService;

	@Override
	protected WebRecommendation initEntity() {
		return new WebRecommendation();
	}

	public void setWebRecommendationService(WebRecommendationService webRecommendationService) {
		super.setService(webRecommendationService);
	}
}

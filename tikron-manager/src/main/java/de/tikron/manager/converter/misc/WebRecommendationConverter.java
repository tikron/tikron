/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.converter.misc;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.tikron.manager.converter.common.NumericKeyEntityConverter;
import de.tikron.manager.service.misc.WebRecommendationService;
import de.tikron.persistence.model.misc.WebRecommendation;

/**
 * Converter used as an interface between primary key and entity.
 * 
 * @date 12.02.2012
 * @author Titus Kruse
 */
@ManagedBean
@ApplicationScoped
// Service won't be set in FacesConverter
// @FacesConverter(forClass=WebRecommendation.class)
public class WebRecommendationConverter extends NumericKeyEntityConverter<WebRecommendation> {

	@ManagedProperty(value = "#{webRecommendationService}")
	private WebRecommendationService webRecommendationService;

	public WebRecommendationConverter() {
		super(WebRecommendation.class);
	}

	public void setWebRecommendationService(WebRecommendationService webRecommendationService) {
		super.setService(webRecommendationService);
	}

}

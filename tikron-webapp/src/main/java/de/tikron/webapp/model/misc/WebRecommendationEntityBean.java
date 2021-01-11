/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.model.misc;

import de.tikron.persistence.model.misc.WebRecommendation;
import de.tikron.webapp.model.common.BaseEntityBean;

/**
 * An entity bean for a web recommendation.
 *
 * @author Titus Kruse
 * @since 21.03.2015
 */
public class WebRecommendationEntityBean extends BaseEntityBean<WebRecommendation> {
	
	private static final long serialVersionUID = -2913522153142844967L;
	
	public static final String NAME = "webRecommendationEntityBean";
	
	// Currently no additional properties required.

	public WebRecommendationEntityBean(WebRecommendation entity) {
		super(entity);
	}
	
	// Delegate methods

	public Long getId() {
		return entity.getId();
	}

	public String getTitle() {
		return entity.getTitle();
	}

	public String getDescription() {
		return entity.getDescription();
	}

	public String getImageName() {
		return entity.getImageName();
	}

	public String getUrl() {
		return entity.getUrl();
	}

}

/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.service.misc;

import java.util.List;

import de.tikron.persistence.model.misc.WebRecommendation;

/**
 * Service for web recommendations. 
 *
 * @date 21.03.2015
 * @author Titus Kruse
 */
public interface WebRecommendationService {
	
	public WebRecommendation getWebRecommendation(Long webRecommendationId);
	
	public List<WebRecommendation> getWebRecommendations();

}

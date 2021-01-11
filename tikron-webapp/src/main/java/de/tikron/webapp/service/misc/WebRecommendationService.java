/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.service.misc;

import java.util.List;

import de.tikron.persistence.model.misc.WebRecommendation;

/**
 * Service for web recommendations. 
 *
 * @author Titus Kruse
 * @since 21.03.2015
 */
public interface WebRecommendationService {
	
	public WebRecommendation getWebRecommendation(Long webRecommendationId);
	
	public List<WebRecommendation> getWebRecommendations();

}

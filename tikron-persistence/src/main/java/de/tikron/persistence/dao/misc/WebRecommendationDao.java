/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.persistence.dao.misc;

import java.util.List;

import de.tikron.persistence.model.misc.WebRecommendation;
import de.tikru.commons.jpa.dao.GenericDao;

/**
 * Data Acccess Object for web recommendations.
 *
 * @author Titus Kruse
 * @since 21.03.2015
 */
public interface WebRecommendationDao extends GenericDao<WebRecommendation, Long> {
	
	/**
	 * Fetches a list of all web recommendations ordered by sequence number.
	 * 
	 * @return A list of web recommendations.
	 */
	public List<WebRecommendation> findAllOrderBySequence();

}

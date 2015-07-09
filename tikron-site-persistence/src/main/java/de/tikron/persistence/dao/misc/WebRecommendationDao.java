/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.persistence.dao.misc;

import java.util.List;

import de.tikron.jpa.dao.GenericDao;
import de.tikron.persistence.model.misc.WebRecommendation;

/**
 * Data Acccess Object for web recommendations.
 *
 * @date 21.03.2015
 * @author Titus Kruse
 */
public interface WebRecommendationDao extends GenericDao<WebRecommendation, Long> {
	
	/**
	 * Fetches a list of all web recommendations ordered by sequence number.
	 * 
	 * @return A list of web recommendations.
	 */
	public List<WebRecommendation> findAllOrderBySequence();

}

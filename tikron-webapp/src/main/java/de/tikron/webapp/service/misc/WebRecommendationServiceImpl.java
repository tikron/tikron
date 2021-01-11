/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.service.misc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.tikron.persistence.dao.misc.WebRecommendationDao;
import de.tikron.persistence.model.misc.WebRecommendation;

/**
 * Spring implementation of WebRecommendationService.
 *
 * @since 21.03.2015
 * @author Titus Kruse
 */
@Service("webRecommendationService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class WebRecommendationServiceImpl implements WebRecommendationService {
	
	private WebRecommendationDao webRecommendationDao;

	@Override
	public WebRecommendation getWebRecommendation(Long webRecommendationId) {
		return webRecommendationDao.findById(webRecommendationId);
	}

	@Override
	public List<WebRecommendation> getWebRecommendations() {
		return webRecommendationDao.findAllOrderBySequence();
	}

	@Autowired
	public void setWebRecommendationDao(WebRecommendationDao webRecommendationDao) {
		this.webRecommendationDao = webRecommendationDao;
	}

}

/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.manager.service.misc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.tikron.manager.service.common.CRUDServiceImpl;
import de.tikron.persistence.dao.misc.WebRecommendationDao;
import de.tikron.persistence.model.misc.WebRecommendation;

/**
 * Default implementation of WebRecommendationService.
 *
 * @author Titus Kruse
 * @since 21.03.2015
 */
@Service("webRecommendationService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class WebRecommendationServiceImpl extends CRUDServiceImpl<WebRecommendation, Long> implements WebRecommendationService {

	protected WebRecommendationDao getDao() {
		return (WebRecommendationDao) super.getDao();
	}

	@Autowired
	public void setDao(WebRecommendationDao dao) {
		super.setDao(dao);
	}

}

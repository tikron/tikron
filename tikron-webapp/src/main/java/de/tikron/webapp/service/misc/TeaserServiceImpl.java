/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.service.misc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.tikron.persistence.dao.misc.TeaserDao;
import de.tikron.persistence.model.misc.Teaser;

/**
 * Spring implementation of {@link TeaserService}.
 *
 * @since 01.06.2015
 * @author Titus Kruse
 */
@Service("teaserService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TeaserServiceImpl implements TeaserService {
	
	private TeaserDao teaserDao;

	@Override
	public List<Teaser> getTeasers() {
		return teaserDao.findVisibleOrderBySquence();
	}

	@Autowired
	public void setTeaserDao(TeaserDao teaserDao) {
		this.teaserDao = teaserDao;
	}

}

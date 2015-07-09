/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.manager.service.misc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.tikron.manager.service.common.CRUDServiceImpl;
import de.tikron.persistence.dao.misc.TeaserDao;
import de.tikron.persistence.model.misc.Teaser;

/**
 * Default implementation of TeaserService.
 *
 * @date 29.05.2015
 * @author Titus Kruse
 */
@Service("teaserService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TeaserServiceImpl extends CRUDServiceImpl<Teaser, Long> implements TeaserService {

	@Override
	public List<Teaser> getAll() {
		return getDao().findAllOrderByName();
	}

	@Override
	protected TeaserDao getDao() {
		return (TeaserDao) super.getDao();
	}

	@Autowired
	public void setDao(TeaserDao dao) {
		super.setDao(dao);
	}

}

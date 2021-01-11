/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.manager.service.misc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.tikron.manager.service.common.CRUDServiceImpl;
import de.tikron.persistence.dao.misc.ClipDao;
import de.tikron.persistence.model.misc.Clip;

/**
 * Default implementation of ClipService.
 *
 * @author Titus Kruse
 * @since 16.03.2015
 */
@Service("clipService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ClipServiceImpl extends CRUDServiceImpl<Clip, Long> implements ClipService {

	protected ClipDao getDao() {
		return (ClipDao) super.getDao();
	}

	@Autowired
	public void setDao(ClipDao dao) {
		super.setDao(dao);
	}

}

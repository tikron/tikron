/**
 * Copyright (c) 2009 by Titus Kruse.
 */
package de.tikron.manager.service.gallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.tikron.manager.service.common.CRUDServiceImpl;
import de.tikron.persistence.dao.gallery.CatalogDao;
import de.tikron.persistence.model.gallery.Catalog;

/**
 * Default-Implementation des CatalogService.
 *
 * @author Titus Kruse
 * @since 12.02.2012
 */
@Service("catalogService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CatalogServiceImpl extends CRUDServiceImpl<Catalog, Long> implements CatalogService {

	protected CatalogDao getCatalogDao() {
		return (CatalogDao) super.getDao();
	}

	@Autowired
	public void setCatalogDao(CatalogDao dao) {
		super.setDao(dao);
	}
}

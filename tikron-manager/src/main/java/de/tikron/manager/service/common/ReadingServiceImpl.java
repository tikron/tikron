/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.manager.service.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import de.tikru.commons.jpa.dao.GenericDao;
import de.tikru.commons.jpa.domain.Entity;

/**
 * Abstrakte Basisklasse für Default-Implementationen aller nur lesenden Services.
 *
 * @since 01.04.2015
 * @author Titus Kruse
 */
public class ReadingServiceImpl<T extends Entity<ID>, ID extends Serializable> implements ReadingService<T, ID> {

	protected GenericDao<T, ID> dao;

	protected GenericDao<T, ID> getDao() {
		return dao;
	}

	protected void setDao(GenericDao<T, ID> dao) {
		this.dao = dao;
	}

	@PreAuthorize("hasRole('USE')")
	@Override
	public T get(ID id) {
		return dao.findById(id);
	}

	@PreAuthorize("hasRole('USE')")
	@Override
	public List<T> getAll() {
		return dao.findAll();
	}

}

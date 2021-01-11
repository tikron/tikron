/**
 * Copyright (c) 2009 by Titus Kruse.
 */
package de.tikron.manager.service.common;

import java.io.Serializable;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.tikru.commons.jpa.domain.Entity;

/**
 * Abstrakte Basisklasse für Default-Implementationen aller Services.
 *
 * @author Titus Kruse
 * @since 24.03.2009
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public abstract class CRUDServiceImpl<T extends Entity<ID>, ID extends Serializable> extends ReadingServiceImpl<T, ID> implements CRUDService<T, ID> {

	@Transactional(propagation = Propagation.REQUIRED)
	@PreAuthorize("hasRole('MANAGE')")
	@Override
	public T insert(T entity) {
		return dao.insert(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@PreAuthorize("hasRole('MANAGE')")
	@Override
	public T update(T entity) {
		return dao.update(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@PreAuthorize("hasRole('MANAGE')")
	@Override
	public T save(T entity) {
		return dao.save(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@PreAuthorize("hasRole('MANAGE')")
	@Override
	public void delete(T entity) {
		dao.delete(entity);
	}

}

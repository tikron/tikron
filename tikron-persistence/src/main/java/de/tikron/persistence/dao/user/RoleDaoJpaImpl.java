/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.persistence.dao.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.jpa.QueryHints;

import de.tikron.persistence.model.user.Role;
import de.tikron.persistence.model.user.RoleId;
import de.tikru.commons.jpa.dao.GenericJpaDao;

/**
 * JPA implementation of {@link RoleDao}
 *
 * @author Titus Kruse
 * @since 01.04.2015
 */
public class RoleDaoJpaImpl extends GenericJpaDao<Role, RoleId> implements RoleDao {

	public RoleDaoJpaImpl() {
		super(Role.class);
	}

	public RoleDaoJpaImpl(EntityManager entityManager) {
		super(Role.class, entityManager);
	}

	@Override
	public Role findById(RoleId id) {
		Map<String, Object> hints = new HashMap<String, Object>();
		hints.put(QueryHints.HINT_CACHEABLE, Boolean.TRUE);
		return entityManager.find(Role.class, id, hints);
	}

	@Override
	public List<Role> findAll() {
		TypedQuery<Role> query = entityManager.createNamedQuery(Role.NQ_FIND_ALL, Role.class);
		query.setHint(QueryHints.HINT_CACHEABLE, Boolean.TRUE);
		return query.getResultList();
	}

}

/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.persistence.dao.user;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.graph.GraphSemantic;

import de.tikron.persistence.model.user.User;
import de.tikru.commons.jpa.dao.GenericJpaDao;

/**
 * JPA implemenation of {@link UserDao}.
 * 
 * @author Titus Kruse
 */
public class UserDaoJpaImpl extends GenericJpaDao<User, Long> implements UserDao {

	public UserDaoJpaImpl() {
		super(User.class);
	}

	public UserDaoJpaImpl(EntityManager entityManager) {
		super(User.class, entityManager);
	}

	@Override
	public User findByIdFetchRoles(Long id) {
		final EntityGraph<?> entityGraph = entityManager.getEntityGraph(User.NEG_ROLES);
		Map<String, Object> hints = new HashMap<String, Object>();
		hints.put(GraphSemantic.FETCH.getJpaHintName(), entityGraph);
		return entityManager.find(User.class, id, hints);
	}

	@Override
	public User findByName(String name) {
		TypedQuery<User> query = entityManager.createNamedQuery(User.NQ_FIND_BY_NAME, User.class);
		query.setParameter("name", name);
		return singleResultOrNull(query);
	}

	@Override
	public User findByNameFetchRoles(String name) {
		TypedQuery<User> query = entityManager.createNamedQuery(User.NQ_FIND_BY_NAME, User.class);
		query.setParameter("name", name);
		query.setHint(GraphSemantic.FETCH.getJpaHintName(), entityManager.getEntityGraph(User.NEG_ROLES));
		return singleResultOrNull(query);
	}
	
	@Override
	public User findOrCreate(String name) {
		User user = findByName(name);
		if (user == null) {
			user = new User(name);
		}
		return user;
	}

	@Override
	public User findOrInsert(String name) {
		User user = findByName(name);
		if (user == null) {
			user = insert(new User(name));
		}
		return user;
	}

}

/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.persistence.dao.gallery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.jpa.QueryHints;

import de.tikron.persistence.model.gallery.Catalog;
import de.tikru.commons.jpa.dao.GenericJpaDao;

/**
 * Data-Access-Object für Kataloge.
 * 
 * @since 28.09.2008
 * @author Titus Kruse
 */
public class CatalogDaoJpaImpl extends GenericJpaDao<Catalog, Long> implements CatalogDao {

	/**
	 * Data Access Object erstellen
	 */
	public CatalogDaoJpaImpl() {
		super(Catalog.class);
	}

	/**
	 * Data Access Object mit EntityManager erstellen
	 * 
	 * @param entityManager Setzt den EntityManager für dieses DAO.
	 */
	public CatalogDaoJpaImpl(EntityManager entityManager) {
		super(Catalog.class, entityManager);
	}

	@Override
	public Catalog findByIdFetchCategories(Long id) {
		final EntityGraph<?> entityGraph = entityManager.getEntityGraph(Catalog.NEG_CATEGORIES);
		Map<String, Object> hints = new HashMap<String, Object>();
		hints.put(GraphSemantic.FETCH.getJpaHintName(), entityGraph); // Fetch eagerly attributes specified in entity graph and in entity 
		// hints.put(QueryHints.HINT_LOADGRAPH, entityGraph); Fetch eagerly ONLY attributes specified in entity graph
		hints.put(QueryHints.HINT_CACHEABLE, Boolean.TRUE);
		Catalog catalog = entityManager.find(Catalog.class, id, hints);
		// Fetch collection to prevent LazyInizitializationExcpeption with query cache and join query
		if (catalog != null) {
			Hibernate.initialize(catalog.getCategories());
		}
		/*
		TypedQuery<Catalog> query = entityManager.createNamedQuery(Catalog.NQ_FIND_BY_ID_FETCH_CATEGORIES, Catalog.class);
		query.setParameter("id", id);
		query.setHint(QueryHints.HINT_CACHEABLE, Boolean.TRUE);
		Catalog catalog = singleResultOrNull(query);
		*/
		return catalog;
	}

	@Override
	public Catalog findByName(String name) {
		TypedQuery<Catalog> query = entityManager.createNamedQuery(Catalog.NQ_FIND_BY_NAME, Catalog.class);
		query.setParameter("name", name);
		Catalog catalog = singleResultOrNull(query);
		return catalog;
	}

	@Override
	public List<Catalog> findAllOrderByName() {
		TypedQuery<Catalog> query = entityManager.createNamedQuery(Catalog.NQ_FIND_ALL_ORDERBY_NAME, Catalog.class);
		return query.getResultList();
	}

	@Override
	public List<Catalog> findAllFetchCategories() {
		TypedQuery<Catalog> query = entityManager.createNamedQuery(Catalog.NQ_FIND_ALL, Catalog.class);
		query.setHint(GraphSemantic.FETCH.getJpaHintName(), entityManager.getEntityGraph(Catalog.NEG_CATEGORIES));
		return query.getResultList();
	}

	@Override
	public List<Catalog> findByVisibility(boolean visibleOnly) {
		TypedQuery<Catalog> query = entityManager.createNamedQuery(Catalog.NQ_FIND_BY_VISIBILITY, Catalog.class);
		query.setParameter("visibleOnly", visibleOnly);
		// Enable query caching for webapp top navigation
		// JPA hints ingnored by Hibernate 2nd level cache?
		// query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.USE);
		query.setHint(QueryHints.HINT_CACHEABLE, Boolean.TRUE);
		return query.getResultList();
	}

	@Override
	public List<Catalog> findByVisibilityOrderByName(boolean visibleOnly) {
		TypedQuery<Catalog> query = entityManager.createNamedQuery(Catalog.NQ_FIND_BY_VISIBILITY_ORDERBY_NAME, Catalog.class);
		query.setParameter("visibleOnly", visibleOnly);
		query.setHint(QueryHints.HINT_CACHEABLE, Boolean.TRUE);
		return query.getResultList();
	}

	@Override
	public List<Catalog> findByVisibilityFetchCategories(boolean visibleOnly) {
		TypedQuery<Catalog> query = entityManager.createNamedQuery(Catalog.NQ_FIND_BY_VISIBILITY, Catalog.class);
		query.setParameter("visibleOnly", visibleOnly);
		query.setHint(GraphSemantic.FETCH.getJpaHintName(), entityManager.getEntityGraph(Catalog.NEG_CATEGORIES));
		query.setHint(QueryHints.HINT_CACHEABLE, Boolean.TRUE);
		List<Catalog> resultList = query.getResultList();
		// Fetch collections to prevent LazyInizitializationExcpeption with query cache and join query
		for (Catalog catalog : resultList) {
			Hibernate.initialize(catalog.getCategories());
		}
		return resultList;
	}
}

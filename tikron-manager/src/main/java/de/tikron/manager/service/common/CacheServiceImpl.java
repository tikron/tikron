/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.manager.service.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import de.tikru.commons.jpa.domain.Entity;

/**
 * Implementation of {@link ReadingService} caching all read information. The cache will never be flushed and has
 * unlimmited capacity. So this Service should only be used for data never changed while applications runs.
 *
 * @author Titus Kruse
 * @since 01.04.2015
 */
public class CacheServiceImpl<T extends Entity<ID>, ID extends Serializable> extends ReadingServiceImpl<T, ID> implements Refreshable {
	
	private Map<ID, T> entityMap = new HashMap<ID, T>();
	
	@PostConstruct
	public void init() {
		// Load data unsynchronized to prevent from user requests waiting on database access (performance opt.)
		List<T> entities = super.getAll();
		Map<ID, T> newEntityMap = new HashMap<ID, T>(entities.size());
		for (T entity : entities) {
			newEntityMap.put(entity.getId(), entity);
		}
		synchronized (entityMap) {
			entityMap = newEntityMap;
		}
	}
	
	@Override
	public void refresh() {
		init();
	}

	@Override
	public T get(ID id) {
		synchronized (entityMap) {
			return entityMap.get(id);
		}
	}

	@Override
	public List<T> getAll() {
		synchronized (entityMap) {
			// Unfortunally we have to convert to List.
			return new ArrayList<T>(entityMap.values());
		}
	}

}

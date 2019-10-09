/**
 * Copyright (c) 2014 by Titus Kruse.
 */
package de.tikron.webapp.facade.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import de.tikron.jpa.domain.Entity;
import de.tikron.webapp.model.common.EntityBean;

/**
 * Helper class providing usefull methods for entity beans.
 *
 * @date 31.03.2014
 * @author Titus
 */
@Service
public class EntityBeanHelper {

	private ApplicationContext applicationContext;
	
	/**
	 * Returns a single entity bean baked from the given entity.
	 * 
	 * @param beanName The name of entity bean to bake.
	 * @param entity The entity.
	 * 
	 * @return The entity bean.
	 */
	@SuppressWarnings("unchecked")
	public <B extends EntityBean<E>, E extends Entity<ID>, ID extends Serializable> B getBean(String beanName, E entity) {
		return (B) applicationContext.getBean(beanName, entity);
	}

	/**
	 * Returns a list of entity beans baked from the given collection of entities.
	 * 
	 * @param beanName The name of entity beans to bake.
	 * @param entities The list of entities.
	 * 
	 * @return The list of enitity beans.
	 */
	public <B extends EntityBean<E>, E extends Entity<ID>, ID extends Serializable> List<B> toList(String beanName, Collection<E> entities) {
		return (List<B>) toCollection(beanName, new ArrayList<B>(entities.size()), entities);
	}

	/**
	 * Returns a set of entity beans baked from the given collection of entities.
	 * 
	 * @param beanName The name of entity beans to bake.
	 * @param entities The set of entities.
	 * 
	 * @return The set of enitity beans.
	 */
	public <B extends EntityBean<E>, E extends Entity<ID>, ID extends Serializable> Set<B> toSet(String beanName, Collection<E> entities) {
		return (Set<B>) toCollection(beanName, new HashSet<B>(entities.size()), entities);
	}

	/**
	 * Returns a map of entity beans baked from the given collection of entities. The key of each map entry is the unique
	 * identifier of the associated entity bean.
	 * 
	 * @param beanName The name of entity beans to bake.
	 * @param entities The set of entities.
	 * 
	 * @return The set of enitity beans.
	 */
	@SuppressWarnings("unchecked")
	public <B extends EntityBean<E>, E extends Entity<ID>, ID extends Serializable> Map<ID, B> toMap(String beanName, Collection<E> entities) {
		Map<ID, B> beans = new HashMap<ID, B>();
		for (Entity<ID> entity : entities) {
			beans.put(entity.getId(), (B) applicationContext.getBean(beanName, entity));
		}
		return beans;
	}

	/**
	 * Returns a collection of entity beans baked from the given collection of entities.
	 * 
	 * @param beanName The name of entity beans to bake.
	 * @param beans A collection to add new entity beans.
	 * @param entities The collection of entities.
	 * 
	 * @return The collection of enitity beans.
	 */
	@SuppressWarnings("unchecked")
	public <B extends EntityBean<E>, E extends Entity<ID>, ID extends Serializable> Collection<B> toCollection(String beanName,
			Collection<B> beans, Collection<E> entities) {
		for (Entity<ID> entity : entities) {
			beans.add((B) applicationContext.getBean(beanName, entity));
		}
		return beans;
	}

	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}

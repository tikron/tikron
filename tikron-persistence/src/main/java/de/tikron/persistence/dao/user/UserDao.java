package de.tikron.persistence.dao.user;

import de.tikron.persistence.model.user.User;
import de.tikru.commons.jpa.dao.GenericDao;

public interface UserDao extends GenericDao<User, Long> {
	
	/**
	 * Tries to find a user by primary key and fetch related roles.
	 * 
	 * @param id The primary key.
	 * @return The user.
	 */
	public User findByIdFetchRoles(Long id);

	/**
	 * Tries to find the user matching the given name. 
	 * 
	 * @param name The name of the user to find.
	 * @return The user or null, if not exists.
	 */
	public User findByName(String name);
	
	/**
	 * Tries to find the user matching the given name and fetch related roles.
	 * 
	 * @param name The name of the user to find.
	 * @return The user or null, if not exists.
	 */
	public User findByNameFetchRoles(String name);
	
	/**
	 * Tries to find the user matching the given name or creates a new one.
	 * 
	 * @param name The name of the user to find.
	 * @return The existing user or a new one created with the given properties.
	 */
	public User findOrCreate(String name);
	
	/**
	 * Tries to find the user matching the given name or creates and inserts a new one.
	 * 
	 * @param name The name of the user to find.
	 * @return The existing user or a new one created with the given properties.
	 */
	public User findOrInsert(String name);
}

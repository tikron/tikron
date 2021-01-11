/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.service.user;

import de.tikron.manager.service.common.CRUDService;
import de.tikron.persistence.model.user.User;

/**
 * Service für Benutzer.
 * 
 * @since 05.02.2012
 * @author Titus Kruse
 */
public interface UserService extends CRUDService<User, Long> {
	
	/**
	 * Holt einen Benutzer inklusive dessen Rollen.
	 * 
	 * @param id Die ID des Benutzers.
	 * @return Der Benutzer oder null, falls er nicht gefunden wurde.
	 */
	public User getWithRoles(Long id);
	
	/**
	 * Holt einen Benutzer über den eindeutigen Namen.
	 * 
	 * @param name Der Name des Benutzers.
	 * @return Der Benutzer oder null, falls kein Benutzer mit dem angegeben Namen existiert.
	 */
	public User get(String name); 

}

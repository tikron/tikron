/**
 * Copyright (c) 2010 by Titus Kruse.
 */
package de.tikron.manager.service.common;

import org.springframework.security.core.userdetails.User;

/**
 * Service providing access to the Spring security layer.
 * 
 * @since 09.12.2010
 * @author Titus Kruse
 */
public interface SecurityService {

	/**
	 * Returns the domain User object for the currently logged in user, or null if no User is logged in.
	 * 
	 * @return User object for the currently logged in user, or null if no User is logged in.
	 */
	public User getCurrentUser();

	/**
	 * Checks whether the currently logged on user has the given authority role.
	 * 
	 * @param authorityRole The authority role.
	 * @return true, if a user is logged on and has the given authority role.
	 */
	public boolean hasAuthorityRole(String authorityRole);

}

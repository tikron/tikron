/**
 * Copyright (c) 2010 by Titus Kruse.
 */
package de.tikron.manager.service.common;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 * Implementation of the security service to access the Spring security layer.
 *
 * @author Titus Kruse
 * @since 09.12.2010
 */
@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

	@Override
	public User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// Return user principle, if logged on.
		if (principal instanceof User) {
			return (User) principal;
		}

		// principal object is either null or represents anonymous user -
		// neither of which our domain User object can represent - so return null
		return null;
	}

	@Override
	public boolean hasAuthorityRole(String authorityRole) {
		User currentUser = getCurrentUser();
		if (currentUser != null) {
			for (GrantedAuthority authority : currentUser.getAuthorities()) {
				if (authorityRole.equals(authority.getAuthority()))
					return true;
			}
		}
		return false;
	}

}

/**
 * Copyright (c) 2011 by Titus Kruse.
 */
package de.tikron.manager.bean.common;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.security.core.userdetails.User;

import de.tikron.manager.service.common.SecurityService;
import de.tikron.persistence.model.user.RoleId;

/**
 * Security bean providing information about the possibe currently logged in user.
 *
 * @author Titus Kruse
 * @since 20.11.2011
 */
@ManagedBean
@RequestScoped
public class SecurityBean {

	@ManagedProperty(value = "#{securityService}")
	private SecurityService securityService;

	/**
	 * Liefert den Namen des aktuell angemeldeten Benutzers.
	 * 
	 * @return The Benutzername oder null, wenn kein Benutzer angemeldet ist.
	 */
	public String getUsername() {
		User currentUser = securityService.getCurrentUser();
		if (currentUser != null) {
			return currentUser.getUsername();
		}
		return null;
	}

	/**
	 * Gibt an, ob ein Benutzer angemeldet ist.
	 * 
	 * @return true, falls ein Benutzer angemeldet ist.
	 */
	public boolean isLoggedIn() {
		return securityService.getCurrentUser() != null;
	}

	/**
	 * Gibt zurück, ob es der aktuell angemeldeten Benutzer die Berechtigung zum Ändern von Daten hat.
	 * 
	 * @return true, falls der Benutzer die Berechtigung zum Ändern hat.
	 */
	public boolean isAuthorityUpdate() {
		return securityService.hasAuthorityRole(RoleId.ROLE_MANAGE.toString());
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

}

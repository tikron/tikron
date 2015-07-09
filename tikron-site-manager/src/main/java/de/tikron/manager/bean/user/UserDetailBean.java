/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.bean.user;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import de.tikron.manager.bean.common.AbstractDetailBean;
import de.tikron.manager.converter.user.PasswordConverter;
import de.tikron.manager.service.user.UserService;
import de.tikron.persistence.model.user.User;

/**
 * Backing Bean für einzelnen Kommentar.
 * 
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class UserDetailBean extends AbstractDetailBean<User> {

	private User user;

	private String oldPassword;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	/**
	 * Bean initialisieren.
	 */
	public void preRenderView(ComponentSystemEvent e) {
		if (user == null) {
			user = new User();
		}
		if (oldPassword == null) {
			oldPassword = user.getPassword();
		}
	}

	/**
	 * Action-Method "Speichern"
	 * 
	 * @return Navigation-Case.
	 */
	public String save() {
		// Revert to old password, if display value hasn't been changed.
		if (user.getPassword() != null && user.getPassword().equals(PasswordConverter.SUBSTITUTION)) {
			user.setPassword(oldPassword);
		}
		// Save user
		userService.save(user);
		return "/pages/common/confirmation.xhtml";
	}

	/**
	 * Action-Method "Löschen"
	 * 
	 * @return Navigation-Case.
	 */
	public String delete() {
		userService.delete(user);
		return "/pages/common/confirmation.xhtml";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}

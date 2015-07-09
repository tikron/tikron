/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.bean.user;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import de.tikron.faces.util.Message;
import de.tikron.manager.bean.common.AbstractSelectableListBean;
import de.tikron.manager.service.user.UserService;
import de.tikron.persistence.model.user.User;

/**
 * Backing Bean für Liste der Benutzer.
 * 
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class UserListBean extends AbstractSelectableListBean<User, Long> implements Serializable {

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	public void preRenderView(ComponentSystemEvent e) {
		if (getList() == null) {
			setList(userService.getAll());
		}
	}

	/**
	 * Anzeige aktualisieren
	 * 
	 * @return Faces-Navigation.
	 */
	public String refresh() {
		setList(null);
		selectNone();
		return null;
	}

	/**
	 * Eintrag editieren.
	 * 
	 * @return Faces-Navigation.
	 */
	public String edit() {
		List<User> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		return "/pages/user/editUser.xhtml?userId=" + selectedItems.get(0).getId() + "&faces-redirect=true";
	}

	/**
	 * Einträge löschen.
	 * 
	 * @return Faces-Navigation.
	 */
	public String delete() {
		List<User> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		for (User user : selectedItems) {
			userService.delete(user);
		}
		return "/pages/common/confirmation.xhtml";
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}

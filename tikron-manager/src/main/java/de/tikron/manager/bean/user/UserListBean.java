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

import org.springframework.web.util.UriComponentsBuilder;

import de.tikron.manager.bean.common.AbstractSelectableListBean;
import de.tikron.manager.service.user.UserService;
import de.tikron.persistence.model.user.User;
import de.tikru.commons.faces.util.Message;

/**
 * Backing Bean für Liste der Benutzer.
 * 
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class UserListBean extends AbstractSelectableListBean<User, Long> implements Serializable {

	private static final long serialVersionUID = 8613909928841928339L;
	
	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	public void preRenderView(ComponentSystemEvent e) {
		if (getList() == null) {
			setList(userService.getAll());
		}
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
		return UriComponentsBuilder.newInstance().path("/pages/user/editUser.xhtml")
				.queryParam("userId",  selectedItems.get(0).getId())
				.queryParam("successView", getNavigationUri())
				.queryParam("faces-redirect", "true")
				.build().encode().toString();
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
		return refresh();
	}
	
	/**
	 * Returns the navigation URI for the current view.
	 * 
	 * @return Die Faces-URI.
	 */
	public String getNavigationUri() {
		return UriComponentsBuilder.newInstance().path("/pages/user/manageUsers.xhtml").build().toString();
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}

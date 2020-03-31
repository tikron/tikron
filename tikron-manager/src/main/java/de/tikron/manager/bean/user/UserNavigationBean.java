/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.bean.user;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.tikron.manager.bean.common.NavigationBean;
import de.tikron.manager.navigation.ChildrenProvider;
import de.tikron.manager.navigation.LoadableNode;
import de.tikron.manager.navigation.Node;
import de.tikron.manager.navigation.NodeDescription;
import de.tikron.manager.navigation.SimpleNode;
import de.tikron.manager.service.common.Constants;
import de.tikron.manager.service.user.UserService;
import de.tikron.persistence.model.user.User;
import de.tikru.commons.faces.util.Message;

/**
 * Managed bean providing the navigation tree for users and associated children.
 *
 * @date 05.02.2012
 * @author Titus Kruse
 */
@ManagedBean
@SessionScoped
public class UserNavigationBean implements NavigationBean {

	private static final String MESSAGE_NODE_USERS = Message.getMessage("navigationNodeUsers");

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	@Override
	public Node getNode() {
		return getUsersNode();
	}

	/**
	 * @return
	 */
	private Node getUsersNode() {
		NodeDescription description = new NodeDescription(MESSAGE_NODE_USERS, Constants.ICON_FOLDER_CLOSED);
		ChildrenProvider childrenProvider = new ChildrenProvider() {
			public List<Node> getChildren() {
				List<Node> nodes = new ArrayList<Node>();
				List<User> users = userService.getAll();
				for (User user : users) {
					nodes.add(getUserNode(user));
				}
				return nodes;
			};
		};
		return new LoadableNode("users", description, "/pages/user/manageUsers.xhtml", childrenProvider);
	}

	/**
	 * @return
	 */
	private Node getUserNode(final User user) {
		String identifier = "user_" + user.getId().toString();
		NodeDescription description = new NodeDescription(user.getDisplayName(), Constants.ICON_DOCUMENT);
		String outcome = "/pages/user/editUser.xhtml?userId=" + user.getId();
		Node node = new SimpleNode(identifier, description, outcome);
		return node;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}

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
import de.tikron.manager.service.user.CommentService;
import de.tikron.persistence.model.user.Comment;
import de.tikru.commons.faces.util.Message;

/**
 * Managed bean providing the navigation tree for comments and associated children.
 *
 * @date 04.02.2012
 * @author Titus Kruse
 */
@ManagedBean
@SessionScoped
public class CommentNavigationBean implements NavigationBean {

	private static final String MESSAGE_NODE_COMMENTS = Message.getMessage("navigationNodeComments");

	@ManagedProperty(value = "#{commentService}")
	private CommentService commentService;
	
	@Override
	public Node getNode() {
		return getCommentsNode();
	}

	/**
	 * @return
	 */
	private Node getCommentsNode() {
		NodeDescription description = new NodeDescription(MESSAGE_NODE_COMMENTS, Constants.ICON_FOLDER_CLOSED);
		ChildrenProvider childrenProvider = new ChildrenProvider() {
			public List<Node> getChildren() {
				List<Node> nodes = new ArrayList<Node>();
				List<Comment> comments = commentService.getAll();
				for (Comment comment : comments) {
					nodes.add(getCommentNode(comment));
				}
				return nodes;
			};
		};
		return new LoadableNode("comments", description, "/pages/user/manageComments.xhtml", childrenProvider);
	}

	/**
	 * @return
	 */
	private Node getCommentNode(final Comment comment) {
		String identifier = "comment_" + comment.getId().toString();
		NodeDescription description = new NodeDescription(comment.getUser().getDisplayName(), Constants.ICON_DOCUMENT);
		String outcome = "/pages/user/editComment.xhtml?commentId=" + comment.getId();
		Node node = new SimpleNode(identifier, description, outcome);
		return node;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

}

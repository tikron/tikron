/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.manager.bean.user;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.web.util.UriComponentsBuilder;

import de.tikron.faces.util.Message;
import de.tikron.manager.bean.common.AbstractSelectableListBean;
import de.tikron.manager.service.user.CommentService;
import de.tikron.persistence.model.user.Comment;

/**
 * Backing Bean für Liste der Kommentare.
 * 
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class CommentListBean extends AbstractSelectableListBean<Comment, Long> implements Serializable {

	@ManagedProperty(value = "#{commentService}")
	private CommentService commentService;

	public void preRenderView(ComponentSystemEvent e) {
		if (getList() == null) {
			setList(commentService.getAll());
		}
	}

	/**
	 * Eintrag editieren.
	 * 
	 * @return Faces-Navigation.
	 */
	public String edit() {
		List<Comment> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		return UriComponentsBuilder.newInstance().path("/pages/user/editComment.xhtml")
				.queryParam("commentId",  selectedItems.get(0).getId())
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
		List<Comment> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		for (Comment comment : selectedItems) {
			commentService.delete(comment);
		}
		return refresh();
	}
	
	/**
	 * Returns the navigation URI for the current view.
	 * 
	 * @return Die Faces-URI.
	 */
	public String getNavigationUri() {
		return UriComponentsBuilder.newInstance().path("/pages/user/manageComments.xhtml").build().toString();
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
}

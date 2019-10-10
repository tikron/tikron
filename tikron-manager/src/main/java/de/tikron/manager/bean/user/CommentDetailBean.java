/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.manager.bean.user;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import de.tikron.manager.bean.common.AbstractDetailBean;
import de.tikron.manager.service.common.SecurityService;
import de.tikron.manager.service.user.CommentService;
import de.tikron.manager.service.user.UserService;
import de.tikron.persistence.model.gallery.Category;
import de.tikron.persistence.model.gallery.Picture;
import de.tikron.persistence.model.misc.Clip;
import de.tikron.persistence.model.user.CategoryComment;
import de.tikron.persistence.model.user.ClipComment;
import de.tikron.persistence.model.user.Comment;
import de.tikron.persistence.model.user.GuestbookComment;
import de.tikron.persistence.model.user.PictureComment;
import de.tikron.persistence.model.user.User;
import de.tikru.commons.faces.util.Message;

/**
 * Backing Bean für einzelnen Kommentar.
 * 
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class CommentDetailBean extends AbstractDetailBean<Comment> {

	private Comment comment;

	private Clip clip;

	private Category category;

	private Picture picture;

	@ManagedProperty(value = "#{commentService}")
	private CommentService commentService;

	@ManagedProperty(value = "#{securityService}")
	private SecurityService securityService;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	/**
	 * Bean initialisieren.
	 */
	public void preRenderView(ComponentSystemEvent e) {
		if (comment == null) {
			// Neuen Kommentar dem aktuell angemeldeten Benutzer zuordnen.
			User currentUser = userService.get(securityService.getCurrentUser().getUsername());
			if (getClip() != null) {
				comment = new ClipComment(getClip(), currentUser);
			} else if (getCategory() != null) {
				comment = new CategoryComment(getCategory(), currentUser);
			} else if (getPicture() != null) {
				comment = new PictureComment(getPicture(), currentUser);
			} else {
				comment = new GuestbookComment(currentUser);
			}
		}
	}

	/**
	 * Action-Method "Speichern"
	 * 
	 * @return Navigation-Case.
	 */
	public String save() {
		commentService.save(comment);
		Message.sendMessage(null, "de.tikron.manager.INFO_SUCCESSFUL_SAVE", null);
		return getSuccessWithRedirect();
	}

	/**
	 * Action-Method "Löschen"
	 * 
	 * @return Navigation-Case.
	 */
	public String delete() {
		commentService.delete(comment);
		Message.sendMessage(null, "de.tikron.manager.INFO_SUCCESSFUL_DELETE", null);
		return getSuccessWithRedirect();
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Clip getClip() {
		return clip;
	}

	public void setClip(Clip clip) {
		this.clip = clip;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}

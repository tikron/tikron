/**
 * Copyright (c) 2011 by Titus Kruse.
 */
package de.tikron.manager.converter.user;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.tikron.manager.converter.common.NumericKeyEntityConverter;
import de.tikron.manager.service.user.CommentService;
import de.tikron.persistence.model.user.Comment;

/**
 * Converter used as an interface between primary key and entity.
 * 
 * @since 27.11.2011
 * @author Titus Kruse
 */
@ManagedBean
@ApplicationScoped
public class CommentConverter extends NumericKeyEntityConverter<Comment> {

	@ManagedProperty(value = "#{commentService}")
	private CommentService commentService;

	public CommentConverter() {
		super(Comment.class);
	}

	public void setCommentService(CommentService commentService) {
		super.setService(commentService);
	}

}

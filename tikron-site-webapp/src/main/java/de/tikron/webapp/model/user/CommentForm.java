/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.model.user;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import de.tikron.jpa.validation.AllowedDomain;
import de.tikron.jpa.validation.NotSpam;
import de.tikron.jpa.validation.ValidationConstants;
import de.tikron.persistence.model.user.CommentTypeId;

/**
 * Base class for Comment forms, holding common properties.
 * 
 * Note: Bean validation annotations are redundant to domain model beans
 * {@link de.tikron.persistence.model.user.Comment} and {@link de.tikron.persistence.model.user.User}. In Spring Ajax
 * controller, we have to use this DTO as request property. To enable bean validation with @Valid annotation,
 * constraints had to be pulled up into this DTO. The only currently known alternatives are @Valid on service layer or
 * programmatically validation of domain model bean in controller/service.
 *
 * @date 03.01.2015
 * @author Titus Kruse
 */
public class CommentForm implements Serializable {

	public static final String NAME = "commentForm";
	
	private CommentTypeId relatedEntityType;
	
	private Long relatedEntityId;

	@NotNull(message = "{user.name.NotNull}")
	@Size(max = 255, message = "{user.name.Size}")
	private String author;
	
	@NotNull(message = "{comment.text.NotNull}")
	@Size(max = 1000, message = "{comment.text.Size}")
	@NotSpam(value = {"://", "<a"}, message = "{comment.text.NotSpam}")
	private String text;
	
	@Size(max = 255, message = "{user.email.Size}")
	@Pattern(regexp = ValidationConstants.EMAIL_MASK, message = "{user.email.Pattern}")
	private String email;
	
	@Size(max = 255, message = "{user.url.Size}")
	@Pattern.List({
		@Pattern(regexp = ValidationConstants.URL_MASK, message = "{user.url.Pattern}"),
//		@Pattern(regexp = ValidationConstants.URL_MASK_DE, message = "{user.url.NotSpam}")
	})
	@AllowedDomain(value = {".de", ".at", ".ch"})
	private String url;

	public CommentForm() {
	}

	public CommentForm(CommentTypeId relatedEntityType, Long relatedEntityId) {
		this.relatedEntityType = relatedEntityType;
		this.relatedEntityId = relatedEntityId;
	}

	public CommentForm(CommentTypeId relatedEntityType, Long relatedEntityId, String author, String text, String email,
			String url) {
		this.relatedEntityType = relatedEntityType;
		this.relatedEntityId = relatedEntityId;
		this.author = author;
		this.text = text;
		this.email = email;
		this.url = url;
	}

	public CommentTypeId getRelatedEntityType() {
		return relatedEntityType;
	}

	public void setRelatedEntityType(CommentTypeId relatedEntityType) {
		this.relatedEntityType = relatedEntityType;
	}

	public Long getRelatedEntityId() {
		return relatedEntityId;
	}

	public void setRelatedEntityId(Long relatedEntityId) {
		this.relatedEntityId = relatedEntityId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.persistence.model.user;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import de.tikru.commons.jpa.domain.GeneratedKeyEntity;
import de.tikru.commons.jpa.domain.ShowableEntity;
import de.tikru.commons.jpa.validation.NotSpam;

/**
 * Ein Kommentar.
 * 
 * @author Titus Kruse
 */
@Entity
@Inheritance
@DiscriminatorColumn(name = "commenttype_id", columnDefinition = ("char(64)"))
@NamedQueries({
		@NamedQuery(name = Comment.NQ_FIND_ALL, query = "SELECT o FROM Comment o"),
		@NamedQuery(name = Comment.NQ_FIND_BY_COMMENTTYPE, query = "SELECT o FROM Comment o WHERE o.commentType = :commentType"),
		@NamedQuery(name = Comment.NQ_FIND_BY_COMMENTTYPE_AND_VISIBILITY, query = "SELECT o FROM Comment o WHERE o.commentType = :commentType AND o.visible in (true, :visibleOnly)") })
@NamedEntityGraphs({
	@NamedEntityGraph(name = Comment.NEG_USER, attributeNodes={@NamedAttributeNode("user")})
})
@Table(name = "comment")
public abstract class Comment extends GeneratedKeyEntity<Long> implements ShowableEntity<Long> {

	public static final String NQ_FIND_ALL = "Comment.findAll";
	public static final String NQ_FIND_BY_COMMENTTYPE = "Comment.findByCommentType";
	public static final String NQ_FIND_BY_COMMENTTYPE_AND_VISIBILITY = "Comment.findByCommentTypeAndVisibility";

	public static final String NEG_USER = "Comment.includeUser";

	@ManyToOne
	@JoinColumn(name = "commenttype_id", insertable = false, updatable = false)
	private CommentType commentType;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@Valid
	private User user;

	@Column(columnDefinition = ("text"), nullable = false)
	@NotNull(message = "{comment.text.NotNull}")
	@Size(max = 1000, message = "{comment.text.Size}")
	@NotSpam(value = {"://", "<a"}, message = "{comment.text.NotSpam}")
	private String text;

	@Column(columnDefinition = ("smallint"))
	private Boolean visible;

	/* @see http://www.eclipse.org/eclipselink/documentation/2.5/solutions/extensible001.htm */
	@Transient
	private Map<String, Object> extensions = new HashMap<String, Object>();

	protected Comment() {
	}

	protected Comment(User user) {
		this.user = user;
	}

	protected Comment(User user, String text) {
		this.user = user;
		this.text = text;
	}

	public CommentType getCommentType() {
		return commentType;
	}

	/*
	 * public void setCommentType(CommentType commentType) { this.commentType = commentType; }
	 */

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Map<String, Object> getExtensions() {
		return extensions;
	}

	public Object setExtension(String name, Object value) {
		return extensions.put(name, value);
	}
	
	/**
	 * Returns the related Entity of this comment.
	 * 
	 * @return The entity or null, if this type off comment hasn't any associated object.
	 */
	public abstract de.tikru.commons.jpa.domain.ShowableEntity<Long> getRelatedEntity();

	@Override
	public String getDisplayName() {
		return getRelatedEntity() == null ? null : getRelatedEntity().getDisplayName();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("user", user).append("createdOn", createdOn).append("text", StringUtils.abbreviate(text, 40))
				.toString();
	}

}

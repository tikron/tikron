/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.persistence.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import de.tikron.jpa.domain.EnumeratedKeyEntity;

/**
 * Ein Kommentartyp.
 *
 * @date 17.03.2012
 * @author Titus Kruse
 */
@Entity
@NamedQueries({ @NamedQuery(name = CommentType.NQ_FIND_ALL, query = "SELECT o FROM CommentType o"),
		@NamedQuery(name = CommentType.NQ_FIND_BY_ID, query = "SELECT o FROM CommentType o WHERE o.id = :id"), })
@Table(name = "comment_type")
public class CommentType extends EnumeratedKeyEntity<CommentTypeId> {

	public static final String NQ_FIND_ALL = "CommentType.findAll";
	public static final String NQ_FIND_BY_ID = "CommentType.findById";

	public CommentType() {
	}

	public CommentType(CommentTypeId id) {
		super(id);
	}

	@Column
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("description", description).toString();
	}

}

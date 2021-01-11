/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.persistence.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import de.tikron.persistence.model.gallery.Category;

/**
 * A comment for a category.
 *
 * @since 07.04.2015
 * @author Titus Kruse
 */
@Entity
@DiscriminatorValue("CATEGORY")
@NamedQueries({
		@NamedQuery(name = CategoryComment.NQ_FIND_BY_CATEGORY, query = "SELECT o FROM CategoryComment o WHERE o.category = :category"),
		@NamedQuery(name = CategoryComment.NQ_FIND_VISIBLE_BY_CATEGORY, query = "SELECT o FROM CategoryComment o WHERE o.category = :category AND o.visible = true") })
public class CategoryComment extends Comment {

	private static final long serialVersionUID = 3627088511003821262L;
	
	public static final String NQ_FIND_BY_CATEGORY = "Comment.findByCategory";
	public static final String NQ_FIND_VISIBLE_BY_CATEGORY = "Comment.findVisibleByCategory";

	@ManyToOne(fetch = FetchType.LAZY) 	// Typically the category is not used for each comment in a list
	@JoinColumn(name = "category_id")
	private Category category;

	public CategoryComment() {
	}

	public CategoryComment(Category category) {
		this.category = category;
	}

	public CategoryComment(Category category, User user) {
		super(user);
		this.category = category;
	}

	public CategoryComment(Category category, User user, String text) {
		super(user, text);
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public de.tikru.commons.jpa.domain.ShowableEntity<Long> getRelatedEntity() {
		return getCategory();
	}

}

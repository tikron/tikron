/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.persistence.model.gallery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import de.tikron.jpa.domain.EnumeratedKeyEntity;

/**
 * A category type.
 *
 * @date 05.04.2015
 * @author Titus Kruse
 */
@Entity
@NamedQueries({ @NamedQuery(name = CategoryType.NQ_FIND_ALL, query = "SELECT o FROM CategoryType o"),
		@NamedQuery(name = CategoryType.NQ_FIND_BY_ID, query = "SELECT o FROM CategoryType o WHERE o.id = :id"), })
@Table(name = "category_type")
public class CategoryType extends EnumeratedKeyEntity<CategoryTypeId> {

	public static final String NQ_FIND_ALL = "CategoryType.findAll";
	public static final String NQ_FIND_BY_ID = "CategoryType.findById";

	public static final CategoryType GALLERY = new CategoryType(CategoryTypeId.GALLERY);
	public static final CategoryType REPORT = new CategoryType(CategoryTypeId.REPORT);

	public CategoryType() {
	}

	public CategoryType(CategoryTypeId id) {
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

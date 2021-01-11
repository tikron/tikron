/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.persistence.model.gallery;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringBuilder;

import de.tikron.persistence.model.user.PictureComment;
import de.tikru.commons.jpa.domain.GeneratedKeyEntity;
import de.tikru.commons.jpa.domain.ShowableEntity;

/**
 * Ein Bild.
 * 
 * @since 26.09.2008
 * @author Titus Kruse
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Picture.NQ_FIND_ALL, query = "SELECT o FROM Picture o"),
		@NamedQuery(name = Picture.NQ_FIND_BY_CATEGORY, query = "SELECT o FROM Picture o WHERE o.category = :category"),
		@NamedQuery(name = Picture.NQ_FIND_BY_CATEGORY_ORDERBY_NAME, query = "SELECT o FROM Picture o WHERE o.category = :category ORDER BY o.name"),
		@NamedQuery(name = Picture.NQ_FIND_BY_NAME, query = "SELECT o FROM Picture o WHERE o.name = :name") })
@Table(name = "gallery_picture", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Picture extends GeneratedKeyEntity<Long> implements ShowableEntity<Long> {

	private static final long serialVersionUID = -2763126762054544147L;
	
	public static final String NQ_FIND_ALL = "Picture.findAll";
	public static final String NQ_FIND_BY_CATEGORY = "Picture.findByCategory";
	public static final String NQ_FIND_BY_CATEGORY_ORDERBY_NAME = "Picture.findByCategoryOrderByName";
	public static final String NQ_FIND_BY_NAME = "Picture.findByName";

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@Column(nullable = false)
	private String name;
	@Column
	private String title;
	@Column(columnDefinition = ("text"))
	private String description;
	@Column(name = "image_name")
	private String imageName;
	@OneToMany(mappedBy = "picture", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PictureComment> comments = new HashSet<PictureComment>();

	/**
	 * Standardkonstruktor.
	 */
	public Picture() {
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Set<PictureComment> getComments() {
		return comments;
	}

	public void setComments(Set<PictureComment> comments) {
		this.comments = comments;
	}

	public void addComment(PictureComment comment) {
		comment.setPicture(this);
		comments.add(comment);
	}

	public void removeComment(PictureComment comment) {
		comments.remove(comment);
	}

	@Override
	public String getDisplayName() {
		return getTitle();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("name", name).append("title", title).toString();
	}

}

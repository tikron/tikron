/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.persistence.model.gallery;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.ToStringBuilder;

import de.tikron.jpa.domain.GeneratedKeyEntity;
import de.tikron.jpa.domain.ShowableEntity;

/**
 * Eine Kategorie.
 * 
 * @date 26.09.2008
 * @author Titus Kruse
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Category.NQ_FIND_ALL, query = "SELECT o FROM Category o"),
		@NamedQuery(name = Category.NQ_FIND_BY_VISIBILITY, query = "SELECT o FROM Category o WHERE o.visible IN(true, :visibleOnly)"),
		@NamedQuery(name = Category.NQ_FIND_BY_CATALOG, query = "SELECT o FROM Category o WHERE o.catalog = :catalog"),
		@NamedQuery(name = Category.NQ_FIND_BY_CATALOG_ORDERBY_NAME, query = "SELECT o FROM Category o WHERE o.catalog = :catalog ORDER BY o.name"),
		@NamedQuery(name = Category.NQ_FIND_BY_CATALOG_AND_VISIBILITY, query = "SELECT o FROM Category o WHERE o.catalog = :catalog AND o.visible IN(true, :visibleOnly)"),
		@NamedQuery(name = Category.NQ_FIND_BY_CATALOG_AND_VISIBILITY_ORDERBY_NAME, query = "SELECT o FROM Category o WHERE o.catalog = :catalog AND o.visible IN(true, :visibleOnly) ORDER BY o.name"),
		@NamedQuery(name = Category.NQ_FIND_BY_NAME, query = "SELECT o FROM Category o WHERE o.name = :name") })
@Table(name = "gallery_category", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Category extends GeneratedKeyEntity<Long> implements ShowableEntity<Long> {

	public static final String NQ_FIND_ALL = "Category.findAll";
	public static final String NQ_FIND_BY_VISIBILITY = "Category.findByVisibility";
	public static final String NQ_FIND_BY_CATALOG = "Category.findByCatalog";
	public static final String NQ_FIND_BY_CATALOG_ORDERBY_NAME = "Category.findByCatalogOrderByName";
	public static final String NQ_FIND_BY_CATALOG_AND_VISIBILITY = "Category.findByCatalogAndVisibility";
	public static final String NQ_FIND_BY_CATALOG_AND_VISIBILITY_ORDERBY_NAME = "Category.findByCatalogAndVisibilityOrderByName";
	public static final String NQ_FIND_BY_NAME = "Category.findByName";

	@ManyToOne
	@JoinColumn(name = "catalog_id", nullable = false)
	private Catalog catalog;

	@Column(nullable = false)
	private String name;
	@Column
	private String title;
	@Column(name = "short_title")
	private String shortTitle;
	@Column(name = "short_description", columnDefinition = ("text"))
	private String shortDescription;
	@Column(name = "long_description", columnDefinition = ("text"))
	private String longDescription;
	@Column(name = "image_name")
	private String imageName;
	@Column
	private Double sequence;
	@Column(columnDefinition = ("smallint"))
	private Boolean visible;
	@Column(name = "on_teaser", columnDefinition = ("smallint"))
	private Boolean onTeaser;
	@ManyToOne
	@JoinColumn(name = "categorytype_id")
	private CategoryType categoryType;
	@Column(name = "display_type")
	@Enumerated
	private DisplayType displayType;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Picture> pictures = new HashSet<Picture>();

	/**
	 * Standardkonstruktor.
	 */
	public Category() {
		this(null);
	}
	
	public Category(Catalog catalog) {
		this(null, catalog, CategoryType.GALLERY, DisplayType.PAGE);
	}

	public Category(String name, Catalog catalog, CategoryType categoryType, DisplayType displayType) {
		this.name = name;
		this.categoryType = categoryType;
		this.catalog = catalog;
		this.displayType = displayType;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
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

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Double getSequence() {
		return sequence;
	}

	public void setSequence(Double sequence) {
		this.sequence = sequence;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getOnTeaser() {
		return onTeaser;
	}

	public void setOnTeaser(Boolean onTeaser) {
		this.onTeaser = onTeaser;
	}

	public CategoryType getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(CategoryType categoryType) {
		this.categoryType = categoryType;
	}

	public DisplayType getDisplayType() {
		return displayType;
	}

	public void setDisplayType(DisplayType displayType) {
		this.displayType = displayType;
	}

	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

	public void addPicture(Picture picture) {
		picture.setCategory(this);
		pictures.add(picture);
	}

	public void removePicture(Picture picture) {
		pictures.remove(picture);
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

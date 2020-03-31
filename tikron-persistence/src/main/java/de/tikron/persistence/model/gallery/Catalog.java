/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.persistence.model.gallery;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import de.tikru.commons.jpa.domain.GeneratedKeyEntity;
import de.tikru.commons.jpa.domain.ShowableEntity;

/**
 * Ein Katalog.
 * 
 * @date 26.09.2008
 * @author Titus Kruse
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Catalog.NQ_FIND_ALL, query = "SELECT DISTINCT o FROM Catalog o"),
		@NamedQuery(name = Catalog.NQ_FIND_ALL_ORDERBY_NAME, query = "SELECT o FROM Catalog o ORDER BY o.name"),
		@NamedQuery(name = Catalog.NQ_FIND_BY_VISIBILITY, query = "SELECT DISTINCT o FROM Catalog o WHERE o.visible IN(true, :visibleOnly)"),
		@NamedQuery(name = Catalog.NQ_FIND_BY_VISIBILITY_ORDERBY_NAME, query = "SELECT o FROM Catalog o WHERE o.visible IN(true, :visibleOnly) ORDER BY o.name"),
		@NamedQuery(name = Catalog.NQ_FIND_BY_NAME, query = "SELECT o FROM Catalog o WHERE o.name = :name"),
		@NamedQuery(name = Catalog.NQ_FIND_BY_ID_FETCH_CATEGORIES, query = "SELECT DISTINCT o FROM Catalog o JOIN FETCH o.categories WHERE o.id = :id")
})
@NamedEntityGraphs({
		@NamedEntityGraph(name = Catalog.NEG_CATEGORIES, attributeNodes={@NamedAttributeNode("categories")})
})
@Table(name = "gallery_catalog", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Catalog extends GeneratedKeyEntity<Long> implements ShowableEntity<Long> {

	private static final long serialVersionUID = 7271531851968911869L;
	
	public static final String NQ_FIND_ALL = "Catalog.findAll";
	public static final String NQ_FIND_ALL_ORDERBY_NAME = "Catalog.findAllOrderByName";
	public static final String NQ_FIND_BY_VISIBILITY = "Catalog.findByVisibility";
	public static final String NQ_FIND_BY_VISIBILITY_ORDERBY_NAME = "Catalog.findByVisibilityOrderByName";
	public static final String NQ_FIND_BY_NAME = "Catalog.findByName";
	public static final String NQ_FIND_BY_ID_FETCH_CATEGORIES = "Catalog.findByIdFetchCategories";

	public static final String NEG_CATEGORIES = "Catalog.includeCategories";

	@Column(nullable = false)
	private String name;
	@Column
	private String title;
	@Column(name = "short_description", columnDefinition = ("text"))
	private String shortDescription;
	@Column(name = "long_description", columnDefinition = ("text"))
	private String longDescription;
	@Column
	private Double sequence;
	@Column(columnDefinition = ("smallint"))
	private Boolean visible;

	// http://stackoverflow.com/questions/4655392/which-java-type-do-you-use-for-jpa-collections-and-why
	@OneToMany(mappedBy = "catalog", cascade = CascadeType.ALL, orphanRemoval = true)
	// Cache collections to prevent LazyInizitializationExcpeption with query cache and join query
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Category> categories = new HashSet<Category>();

	/**
	 * Standardkonstruktor.
	 */
	public Catalog() {
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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String short_description) {
		this.shortDescription = short_description;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String long_description) {
		this.longDescription = long_description;
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

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	/**
	 * Adds the given category to this catalog.
	 * 
	 * @param category The category to add.
	 */
	public void addCategory(Category category) {
		category.setCatalog(this);
		categories.add(category);
	}

	/**
	 * Removes the given category from this catalog.
	 * 
	 * @param category The category to remove.
	 */
	public void removeCategory(Category category) {
		categories.remove(category);
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

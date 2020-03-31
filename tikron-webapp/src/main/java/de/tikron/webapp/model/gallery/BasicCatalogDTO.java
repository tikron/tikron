package de.tikron.webapp.model.gallery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.tikron.persistence.model.gallery.CategoryType;
import de.tikron.persistence.model.gallery.DisplayType;
import de.tikron.webapp.model.common.EntityDTO;

public class BasicCatalogDTO extends EntityDTO<Long> {
	
	private final String name;
	
	private final String title;
	
	private final Double sequence;
	
	private final List<BasicCategoryDTO> categories;
	
	private final String seoName;

	public BasicCatalogDTO(Long id, String name, String title, Double sequence, String seoName) {
		super(id);
		this.name = name;
		this.title = title;
		this.sequence = sequence;
		this.categories = new ArrayList<BasicCategoryDTO>();
		this.seoName = seoName;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public Double getSequence() {
		return sequence;
	}
	
	public void addCategory(Long id, String name, String title, String shortTitle,
			String shortDescription, String longDescription, String imageName, Double sequence, CategoryType categoryType, DisplayType displayType, Boolean commentable, Boolean rateable, String seoName) {
		this.categories.add(new CategoryDTO(id, this, name, title, shortTitle, shortDescription, longDescription, imageName, sequence, categoryType.toString(), displayType, commentable, rateable, seoName));
	}
	
	public void addCategory(BasicCategoryDTO category) {
		this.categories.add(category);
	}
	
	public void addCategories(Collection<BasicCategoryDTO> categories) {
		this.categories.addAll(categories);
	}

	public List<BasicCategoryDTO> getCategories() {
		return Collections.unmodifiableList(categories);
	}

	public String getSeoName() {
		return seoName;
	}

}

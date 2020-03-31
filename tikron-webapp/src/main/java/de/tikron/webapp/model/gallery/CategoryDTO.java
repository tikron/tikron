package de.tikron.webapp.model.gallery;

import de.tikron.persistence.model.gallery.DisplayType;

public class CategoryDTO extends BasicCategoryDTO {
	
	private final String shortDescription;
	
	private final String longDescription;
	
	private final String imageName;
	
	public CategoryDTO(Long id, BasicCatalogDTO catalog, String name, String title, String shortTitle,
			String shortDescription, String longDescription, String imageName, Double sequence, String categoryType, DisplayType displayType, Boolean commentable, Boolean rateable, String seoName) {
		super(id, catalog, name, title, shortTitle, sequence, categoryType, displayType, commentable, rateable, seoName);
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.imageName = imageName;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public String getImageName() {
		return imageName;
	}

}

package de.tikron.webapp.model.gallery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.tikron.persistence.model.gallery.DisplayType;
import de.tikron.webapp.model.common.EntityDTO;

public class BasicCategoryDTO extends EntityDTO<Long> {

	private final BasicCatalogDTO catalog;
	
	private final String name;
	
	private final String title;
	
	private final String shortTitle;
	
	private final Double sequence;
	
	private final String categoryType; // Using String as of JSP can't handle named enums like CategoryTypeId 
	
	private final DisplayType displayType;
	
	private final List<PictureDTO> pictures;
	
	private final String seoName;

	public BasicCategoryDTO(Long id, BasicCatalogDTO catalog, String name, String title, String shortTitle, Double sequence,
			String categoryType, DisplayType displayType, String seoName) {
		super(id);
		this.catalog = catalog;
		this.name = name;
		this.title = title;
		this.shortTitle = shortTitle;
		this.sequence = sequence;
		this.categoryType = categoryType;
		this.displayType = displayType;
		this.pictures = new ArrayList<PictureDTO>();
		this.seoName = seoName;
	}

	public BasicCatalogDTO getCatalog() {
		return catalog;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public Double getSequence() {
		return sequence;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public DisplayType getDisplayType() {
		return displayType;
	}

	public void addPicture(PictureDTO picture) {
		this.pictures.add(picture);
	}

	public void addPicture(Long id, CategoryDTO category, String name, String title, String description, String imageName, String seoName,
			ImageDTO image) {
				this.pictures.add(new PictureDTO(id, category, name, title, description, imageName, seoName, image));
			}

	public List<PictureDTO> getPictures() {
		return Collections.unmodifiableList(pictures);
	}

	public String getSeoName() {
		return seoName;
	}

}

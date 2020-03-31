package de.tikron.webapp.model.gallery;

import de.tikron.webapp.model.common.EntityDTO;

public class BasicPictureDTO extends EntityDTO<Long> {
	
	protected final BasicCategoryDTO category;
	
	protected final String name;
	
	protected final String title;
	
	protected final String imageName;
	
	protected final String seoName;
	
	protected final ImageDTO image;
	
	public BasicPictureDTO(Long id, BasicCategoryDTO category, String name, String title, String imageName, String seoName, ImageDTO image) {
		super(id);
		this.category = category;
		this.name = name;
		this.title = title;
		this.imageName = imageName;
		this.seoName = seoName;
		this.image = image;
	}

	public BasicCategoryDTO getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public String getImageName() {
		return imageName;
	}

	public String getSeoName() {
		return seoName;
	}

	public ImageDTO getImage() {
		return image;
	}

}

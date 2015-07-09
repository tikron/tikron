package de.tikron.webapp.model.gallery;

public class PictureDTO extends BasicPictureDTO {
	
	private final String description;

	public PictureDTO(Long id, BasicCategoryDTO category, String name, String title, String description, String imageName, String seoName,
			ImageDTO image) {
		super(id, category, name, title, imageName, seoName, image);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}

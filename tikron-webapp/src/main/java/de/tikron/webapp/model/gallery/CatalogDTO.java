package de.tikron.webapp.model.gallery;

public class CatalogDTO extends BasicCatalogDTO {

	private final String shortDescription;
	private final String longDescription;

	public CatalogDTO(Long id, String name, String title, String shortDescription, String longDescription, Double sequence,
			String seoName) {
		super(id, name, title, sequence, seoName);
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

}

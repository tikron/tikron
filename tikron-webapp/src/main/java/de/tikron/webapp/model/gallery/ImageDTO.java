package de.tikron.webapp.model.gallery;

import java.net.URI;
import java.util.Map;

public class ImageDTO {

	private final Map<String, URI> imageUris;

	public ImageDTO(Map<String, URI> imageUris) {
		this.imageUris = imageUris;
	}

	public Map<String, URI> getImageUris() {
		return imageUris;
	}

}

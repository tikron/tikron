/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.assembler.gallery;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tikron.persistence.model.gallery.Picture;
import de.tikron.webapp.model.gallery.ImageDTO;
import de.tikron.webapp.service.gallery.GalleryService;

/**
 * Component class mapping from/to data transfer objects of type Image. 
 *
 * @date 02.01.2015
 * @author Titus Kruse
 */
@Component
public class ImageDTOAssembler {

	private GalleryService galleryService;
	
	public ImageDTO toDTO(Picture picture) {
		Map<String, URI> imageUris = galleryService.getImageUris(picture);
		return new ImageDTO(imageUris);
	}

	@Autowired
	public void setGalleryService(GalleryService galleryService) {
		this.galleryService = galleryService;
	}

}

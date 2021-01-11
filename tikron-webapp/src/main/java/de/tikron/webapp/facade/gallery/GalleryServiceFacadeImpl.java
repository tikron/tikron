/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.facade.gallery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tikron.persistence.model.gallery.Category;
import de.tikron.persistence.model.gallery.Picture;
import de.tikron.webapp.assembler.gallery.PictureDTOAssembler;
import de.tikron.webapp.model.gallery.BasicPictureDTO;
import de.tikron.webapp.service.gallery.GalleryService;

/**
 * 
 *
 * @author Titus Kruse
 * @since 01.01.2015
 */
@Service
public class GalleryServiceFacadeImpl implements GalleryServiceFacade {
	
	private GalleryService galleryService;
	
	private PictureDTOAssembler pictureDTOAssembler;

	@Override
	public List<BasicPictureDTO> listPictures(Long categoryId) {
		Category category = galleryService.getCategory(categoryId);
		List<Picture> pictures = galleryService.getPictures(category);
		return pictureDTOAssembler.toBasicDTOList(pictures);
	}

	@Autowired
	public void setGalleryService(GalleryService galleryService) {
		this.galleryService = galleryService;
	}

	@Autowired
	public void setPictureDTOAssembler(PictureDTOAssembler pictureDTOAssembler) {
		this.pictureDTOAssembler = pictureDTOAssembler;
	}

}

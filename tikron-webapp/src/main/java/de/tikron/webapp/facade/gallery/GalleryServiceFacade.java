/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.facade.gallery;

import java.util.List;

import de.tikron.webapp.model.gallery.BasicPictureDTO;

/**
 * 
 *
 * @since 01.01.2015
 * @author Titus Kruse
 */
public interface GalleryServiceFacade {

	public List<BasicPictureDTO> listPictures(Long categoryId);
}

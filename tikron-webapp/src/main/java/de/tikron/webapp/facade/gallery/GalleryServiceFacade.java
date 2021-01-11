/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.facade.gallery;

import java.util.List;

import de.tikron.webapp.model.gallery.BasicPictureDTO;

/**
 * 
 *
 * @author Titus Kruse
 * @since 01.01.2015
 */
public interface GalleryServiceFacade {

	public List<BasicPictureDTO> listPictures(Long categoryId);
}

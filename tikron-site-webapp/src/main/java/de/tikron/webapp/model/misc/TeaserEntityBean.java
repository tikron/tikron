/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.model.misc;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;

import de.tikron.persistence.model.gallery.Picture;
import de.tikron.persistence.model.misc.Teaser;
import de.tikron.persistence.model.misc.TeaserName;
import de.tikron.webapp.assembler.gallery.PictureDTOAssembler;
import de.tikron.webapp.model.common.BaseEntityBean;
import de.tikron.webapp.model.gallery.PictureDTO;
import de.tikron.webapp.service.gallery.GalleryService;

/**
 * An entity bean for a teaser.
 *
 * @date 01.06.2015
 * @author Titus Kruse
 */
public class TeaserEntityBean extends BaseEntityBean<Teaser> {
	
	public static final String NAME = "teaserEntityBean";

	private static Logger LOGGER = Logger.getLogger(TeaserEntityBean.class);
	
	private PictureDTO picture;
	
//	private String imageUrl;

	public TeaserEntityBean(Teaser entity) {
		super(entity);
	}

	/**
	 * Returns a picture DTO to be used in front end.
	 *  
	 * @return A picture DTO or null, if no picture available.
	 */
	public PictureDTO getPicture() {
		if (picture == null) {
			if (TeaserName.FINDING.toString().equals(entity.getName())) {
				// Finding teaser
				Picture findingPicture = applicationContext.getBean(GalleryService.class).getRandomPicture();
				if (findingPicture != null) {
					picture = applicationContext.getBean(PictureDTOAssembler.class).toDTO(findingPicture);
				} else {
					LOGGER.warn("No random Picture found for finding teaser.");
				}
			} else if (entity.getPicture() != null) {
				// General teaser with picture from image archive.
				picture = applicationContext.getBean(PictureDTOAssembler.class).toDTO(entity.getPicture());
			}
		}
		return picture;
	}
	
	/*
	public String getImageUrl() {
		if (imageUrl == null) {
			if (entity.getImageName() != null) {
				imageUrl = "/images/" + entity.getImageName();
			} else if (entity.getPicture() != null) {
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
				ImageService imageService = applicationContext.getBean(ImageServiceImpl.class);
				imageUrl = imageService.getImageServerUrl(request.isSecure()).toString() + getPicture().getImage().getImageUris().get("galleryTeaser");
			}
		}
		return imageUrl;
	}
	*/
	
	// Delegate methods

	public String getName() {
		return entity.getName();
	}

	public Double getSequence() {
		return entity.getSequence();
	}

	public LocalDateTime getStartDate() {
		return entity.getStartDate();
	}

	public LocalDateTime getEndDate() {
		return entity.getEndDate();
	}

	public Boolean getVisible() {
		return entity.getVisible();
	}

	public String getTitle() {
		return entity.getTitle();
	}

	public String getImageName() {
		return entity.getImageName();
	}

	public String getImageAlternate() {
		return entity.getImageAlternate();
	}

	public String getTargetUrl() {
		return entity.getTargetUrl();
	}

	public String getCaption() {
		return entity.getCaption();
	}

	public String getSubTitle() {
		return entity.getSubTitle();
	}

}

/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.converter.gallery;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.tikron.manager.converter.common.NumericKeyEntityConverter;
import de.tikron.manager.service.gallery.PictureService;
import de.tikron.persistence.model.gallery.Picture;

/**
 * Converter used as an interface between primary key and entity.
 * 
 * @author Titus Kruse
 * @since 12.02.2012
 */
@ManagedBean
@ApplicationScoped
// Service won't be set in FacesConverter
// @FacesConverter(forClass=Picture.class)
public class PictureConverter extends NumericKeyEntityConverter<Picture> {

	@ManagedProperty(value = "#{pictureService}")
	private PictureService pictureService;

	public PictureConverter() {
		super(Picture.class);
	}

	public void setPictureService(PictureService pictureService) {
		super.setService(pictureService);
	}

}

/**
 * Copyright (c) 2011 by Titus Kruse.
 */
package de.tikron.manager.bean.common;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import de.tikron.manager.service.common.ImageService;

/**
 * Stellt Funktionen für den Image-Server iBase bereit.
 *
 * @date 22.10.2011
 * @author Titus Kruse
 */
@ManagedBean
@RequestScoped
public class ImageServerBean {

	@ManagedProperty(value = "#{imageService}")
	private ImageService imageService;

	/**
	 * Liefert den Kontextpfad zum Imageserver iBase.
	 * 
	 * @return Der Kontextpfad.
	 */
	public String getContextPath() {
		return imageService.getServerPath();
	}

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

}

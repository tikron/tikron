/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.manager.bean.gallery;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import de.tikron.manager.bean.common.BaseBean;
import de.tikron.manager.service.gallery.PictureService;
import de.tikron.persistence.model.gallery.Picture;

/**
 * Backing Bean zum Speichern des Dateinamens eines hochgeladenen Bildes.
 * 
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class PictureImageUploadConfirmBean extends BaseBean {

	private static final long serialVersionUID = 8532922321612737984L;

	private Picture picture;

	private String imageName;

	@ManagedProperty(value = "#{pictureService}")
	private PictureService pictureService;

	/**
	 * Init method called by faces event "preRenderView" after all view parameters have been set.
	 */
	public void preRenderView(ComponentSystemEvent e) {
		// Set file name of image path name as image file name
		if (imageName != null) {
			int pos = imageName.lastIndexOf("/");
			String imageFileName = (pos != -1 ? imageName.substring(pos + 1) : imageName);
			picture.setImageName(imageFileName);
		}
	}

	/**
	 * Action-Method "Speichern"
	 * 
	 * @return Navigation-Case.
	 */
	public String save() {
		pictureService.save(picture);
		return "/pages/common/confirmation.xhtml";
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}
}

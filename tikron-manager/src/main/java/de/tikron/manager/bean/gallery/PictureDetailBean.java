/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.bean.gallery;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import de.tikron.manager.bean.common.AbstractDetailBean;
import de.tikron.manager.service.gallery.PictureService;
import de.tikron.persistence.model.gallery.Category;
import de.tikron.persistence.model.gallery.Picture;
import de.tikru.commons.faces.util.FacesParameter;
import de.tikru.commons.faces.util.FacesUtil;
import de.tikru.commons.faces.util.Message;

/**
 * Backing Bean für eine einzelne Kategory.
 * 
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class PictureDetailBean extends AbstractDetailBean<Picture> {

	private static final long serialVersionUID = 427069534572697996L;

	private Picture picture;

	private Category category;

	private Picture previousPicture;

	private Picture nextPicture;
	
	@ManagedProperty(value = "#{pictureService}")
	private PictureService pictureService;

	@ManagedProperty(value = "#{pictureImageBean}")
	private PictureImageBean pictureImageBean;

	/**
	 * Bean initialisieren.
	 */
	public void preRenderView(ComponentSystemEvent e) {
		if (picture == null) {
			picture = new Picture();
			picture.setCategory(category);
		}
	}

	/**
	 * Action-Method "Speichern"
	 * 
	 * @return Navigation-Case.
	 */
	public String save() {
		pictureService.save(picture);
		Message.sendMessage(null, "de.tikron.manager.INFO_SUCCESSFUL_SAVE", null);
		return getSuccessWithRedirect();
	}

	/**
	 * Action-Method "Löschen"
	 * 
	 * @return Navigation-Case.
	 */
	public String delete() {
		pictureService.delete(picture);
		return getSuccessWithRedirect();
	}

	/**
	 * Action Method "Bild hochladen"
	 * 
	 * @return Navigation-Case.
	 */
	public String uploadImage() {
		// Entity Daten speichern, um später Name des hochgeladenen Bildes hinzufügen zu können. 
		pictureService.save(picture);
		// Build redirect URL to get back to current domain and context after succuessful upload
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, List<String>> parameters = new HashMap<String, List<String>>();
		parameters.put("pictureId", Arrays.asList(picture.getId().toString()));
		String redirectURL = context.getApplication().getViewHandler().getRedirectURL(context, "/pages/common/uploadPictureImageConfirm.html", parameters, false);
		// Add host FQDN and port to get back from image server to my one web application
		URL absoluteRedirectUrl = FacesUtil.buildURL(redirectURL);
		// Pass picture image path and redirect URL to common image upload view
		FacesParameter.setSessionMapValue("imagePath", this.pictureImageBean.getPictureImagePath(picture.getCategory()));
		FacesParameter.setSessionMapValue("redirectUrl", absoluteRedirectUrl);
		return "/pages/common/uploadImage.xhtml";
	}

	public Picture getPrevious() {
		if (previousPicture == null) {
			previousPicture = pictureService.getPrevious(getPicture());
		}
		return previousPicture;
	}

	public Picture getNext() {
		if (nextPicture == null) {
			nextPicture = pictureService.getNext(getPicture());
		}
		return nextPicture;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	public void setPictureImageBean(PictureImageBean pictureImageBean) {
		this.pictureImageBean = pictureImageBean;
	}
}

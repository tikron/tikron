/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.bean.gallery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.SelectItem;

import org.springframework.web.util.UriComponentsBuilder;

import de.tikron.manager.bean.common.AbstractSelectableListBean;
import de.tikron.manager.service.common.ImageService;
import de.tikron.manager.service.gallery.CategoryService;
import de.tikron.manager.service.gallery.PictureService;
import de.tikron.persistence.model.gallery.Category;
import de.tikron.persistence.model.gallery.Picture;
import de.tikru.commons.faces.util.Message;

/**
 * Backing Bean für eine Liste von Bilder.
 * 
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class PictureListBean extends AbstractSelectableListBean<Picture, Long> implements Serializable {

	private static final long serialVersionUID = 5264895250753768995L;

	private Category category;

	private List<SelectItem> selectCategories;

	private Category selectedCategory;

	@ManagedProperty(value = "#{pictureService}")
	private PictureService pictureService;

	@ManagedProperty(value = "#{categoryService}")
	private CategoryService categoryService;

	@ManagedProperty(value = "#{imageService}")
	private transient ImageService imageService;

	/**
	 * Bean initialisieren.
	 */
	public void preRenderView(ComponentSystemEvent e) {
		if (getList() == null) {
			setList(pictureService.getPictures(category));
		}
	}

	/**
	 * Eintrag editieren.
	 * 
	 * @return Faces-Navigation.
	 */
	public String edit() {
		List<Picture> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		return UriComponentsBuilder.newInstance().path("/pages/gallery/editPicture.xhtml")
			.queryParam("pictureId",  selectedItems.get(0).getId())
			.queryParam("successView", getNavigationUri())
			.queryParam("faces-redirect", "true")
			.build().encode().toString();
	}

	/**
	 * Einträge löschen.
	 * 
	 * @return Faces-Navigation.
	 */
	public String delete() {
		List<Picture> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		for (Picture picture : selectedItems) {
			if (picture.getImageName() != null) {
				imageService.deleteGalleryImage(picture.getImageName(), picture.getCategory().getName());
			}
			pictureService.delete(picture);
		}
		Message.sendMessage(null, "de.tikron.manager.INFO_SUCCESSFUL_DELETE", new Object[] {});
		return refresh();
	}

	/**
	 * Einträge verschieben. Die Einträge werden in die ausgewählte Kategorie verschoben.
	 * 
	 * @return Faces-Navigation.
	 */
	public String move() {
		List<Picture> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		// Move pictures only when another category was selected
		if (!getCategory().equals(getSelectedCategory())) {
			for (Picture picture : selectedItems) {
				// Move the picture image from the old category to the new one.
				imageService.moveGalleryImage(picture.getImageName(), picture.getCategory().getName(), getSelectedCategory()
						.getName());
				// Set the new category in the picture object
				picture.setCategory(getSelectedCategory());
				pictureService.update(picture);
			}
		}
		Message.sendMessage(null, "de.tikron.manager.INFO_SUCCESSFUL_MOVE", new Object[] {});
		return refresh();
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Liefert eine Liste von Kategorien, die zur Auswahl angezeigt werden.
	 * 
	 * @return Eine Liste von Kategorien.
	 */
	public List<SelectItem> getSelectCategories() {
		if (selectCategories == null) {
			selectCategories = new ArrayList<SelectItem>();
			// List<Category> categories = categoryService.getCategories(getCategory().getCatalog());
			List<Category> categories = categoryService.getAll();
			for (Category category : categories) {
				selectCategories.add(new SelectItem(category, category.getDisplayName()));
			}
		}
		return selectCategories;
	}
	
	/**
	 * Liefert die URI zur aktuellen View.
	 * 
	 * @return Die Faces-URI.
	 */
	public String getNavigationUri() {
		return UriComponentsBuilder.newInstance().path("/pages/gallery/managePictures.xhtml")
				.queryParam("categoryId", getCategory().getId()).build().toString();
	}

	public Category getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(Category selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}
}

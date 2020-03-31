/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.manager.bean.gallery;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.web.util.UriComponentsBuilder;

import de.tikron.manager.bean.common.AbstractSelectableListBean;
import de.tikron.manager.service.gallery.CatalogService;
import de.tikron.persistence.model.gallery.Catalog;
import de.tikru.commons.faces.util.Message;

/**
 * Backing Bean für die Liste der Kataloge.
 * 
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class CatalogListBean extends AbstractSelectableListBean<Catalog, Long> implements Serializable {

	private static final long serialVersionUID = -943335027364563993L;
	
	@ManagedProperty(value = "#{catalogService}")
	private transient CatalogService catalogService;

	/**
	 * Bean initialisieren.
	 */
	public void preRenderView(ComponentSystemEvent e) {
		if (getList() == null) {
			setList(catalogService.getAll());
		}
	}

	/**
	 * Eintrag editieren.
	 * 
	 * @return Faces-Navigation.
	 */
	public String edit() {
		List<Catalog> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		return UriComponentsBuilder.newInstance().path("/pages/gallery/editCatalog.xhtml")
				.queryParam("catalogId",  selectedItems.get(0).getId())
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
		List<Catalog> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		for (Catalog catalog : selectedItems) {
			catalogService.delete(catalog);
		}
		return refresh();
	}

	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}
	
	/**
	 * Liefert die URI zur aktuellen View.
	 * 
	 * @return Die Faces-URI.
	 */
	public String getNavigationUri() {
		return UriComponentsBuilder.newInstance().path("/pages/gallery/manageCatalogs.xhtml").build().toString();
	}

}

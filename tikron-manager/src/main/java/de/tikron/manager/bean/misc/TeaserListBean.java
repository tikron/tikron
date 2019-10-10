/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.manager.bean.misc;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.web.util.UriComponentsBuilder;

import de.tikron.manager.bean.common.AbstractSelectableListBean;
import de.tikron.manager.service.misc.TeaserService;
import de.tikron.persistence.model.misc.Teaser;
import de.tikru.commons.faces.util.Message;

/**
 * Managed bean providing a selectable list of teasers.
 * 
 * @date 31.05.2015
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class TeaserListBean extends AbstractSelectableListBean<Teaser, Long> implements Serializable {

	@ManagedProperty(value = "#{teaserService}")
	private TeaserService teaserService;

	public void preRenderView(ComponentSystemEvent e) {
		if (getList() == null) {
			setList(teaserService.getAll());
		}
	}

	/**
	 * Edit entry.
	 * 
	 * @return Faces outcome.
	 */
	public String edit() {
		List<Teaser> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		return UriComponentsBuilder.newInstance().path("/pages/misc/editTeaser.xhtml")
				.queryParam("teaserId",  selectedItems.get(0).getId())
				.queryParam("successView", getNavigationUri())
				.queryParam("faces-redirect", "true")
				.build().encode().toString();
	}

	/**
	 * Delete entries.
	 * 
	 * @return Faces outcome.
	 */
	public String delete() {
		List<Teaser> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		for (Teaser teaser : selectedItems) {
			teaserService.delete(teaser);
		}
		return refresh();
	}
	
	/**
	 * Returns the navigation URI for the current view.
	 * 
	 * @return Die Faces-URI.
	 */
	public String getNavigationUri() {
		return UriComponentsBuilder.newInstance().path("/pages/misc/manageTeasers.xhtml").build().toString();
	}

	public void setTeaserService(TeaserService teaserService) {
		this.teaserService = teaserService;
	}
}

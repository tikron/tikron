/**
 * Copyright (c) 2012 by Titus Kruse.
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
import de.tikron.manager.service.misc.ClipService;
import de.tikron.persistence.model.misc.Clip;
import de.tikru.commons.faces.util.Message;

/**
 * Managed bean providing a selectable list of video clips.
 * 
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class ClipListBean extends AbstractSelectableListBean<Clip, Long> implements Serializable {

	private static final long serialVersionUID = 2828228622638635884L;
	
	@ManagedProperty(value = "#{clipService}")
	private ClipService clipService;

	public void preRenderView(ComponentSystemEvent e) {
		if (getList() == null) {
			setList(clipService.getAll());
		}
	}

	/**
	 * Edit entry.
	 * 
	 * @return Faces outcome.
	 */
	public String edit() {
		List<Clip> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		return UriComponentsBuilder.newInstance().path("/pages/gallery/editClip.xhtml")
				.queryParam("clipId",  selectedItems.get(0).getId())
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
		List<Clip> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		for (Clip clip : selectedItems) {
			clipService.delete(clip);
		}
		Message.sendMessage(null, "de.tikron.manager.INFO_SUCCESSFUL_DELETE", new Object[] {});
		return refresh();
	}
	
	/**
	 * Returns the navigation URI for the current view.
	 * 
	 * @return Die Faces-URI.
	 */
	public String getNavigationUri() {
		return UriComponentsBuilder.newInstance().path("/pages/misc/manageClips.xhtml").build().toString();
	}

	public void setClipService(ClipService clipService) {
		this.clipService = clipService;
	}
}

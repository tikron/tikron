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

import de.tikron.faces.util.Message;
import de.tikron.manager.bean.common.AbstractSelectableListBean;
import de.tikron.manager.service.misc.ClipService;
import de.tikron.persistence.model.misc.Clip;

/**
 * Managed bean providing a selectable list of video clips.
 * 
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class ClipListBean extends AbstractSelectableListBean<Clip, Long> implements Serializable {

	@ManagedProperty(value = "#{clipService}")
	private ClipService clipService;

	public void preRenderView(ComponentSystemEvent e) {
		if (getList() == null) {
			setList(clipService.getAll());
		}
	}

	/**
	 * Refresh view.
	 * 
	 * @return Faces outcome.
	 */
	public String refresh() {
		setList(null);
		selectNone();
		return null;
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
		return "/pages/misc/editClip.xhtml?clipId=" + selectedItems.get(0).getId() + "&faces-redirect=true";
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
		return "/pages/common/confirmation.xhtml";
	}

	public void setClipService(ClipService clipService) {
		this.clipService = clipService;
	}
}

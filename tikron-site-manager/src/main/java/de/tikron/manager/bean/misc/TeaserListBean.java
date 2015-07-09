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

import de.tikron.faces.util.Message;
import de.tikron.manager.bean.common.AbstractSelectableListBean;
import de.tikron.manager.service.misc.TeaserService;
import de.tikron.persistence.model.misc.Teaser;

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
		List<Teaser> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		return "/pages/misc/editTeaser.xhtml?teaserId=" + selectedItems.get(0).getId() + "&faces-redirect=true";
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
		return "/pages/common/confirmation.xhtml";
	}

	public void setTeaserService(TeaserService teaserService) {
		this.teaserService = teaserService;
	}
}

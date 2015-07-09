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
import de.tikron.manager.service.misc.WebRecommendationService;
import de.tikron.persistence.model.misc.WebRecommendation;

/**
 * Managed bean providing a selectable list of web recommendations.
 * 
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class WebRecommendationListBean extends AbstractSelectableListBean<WebRecommendation, Long> implements Serializable {

	@ManagedProperty(value = "#{webRecommendationService}")
	private WebRecommendationService webRecommendationService;

	public void preRenderView(ComponentSystemEvent e) {
		if (getList() == null) {
			setList(webRecommendationService.getAll());
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
		List<WebRecommendation> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		return "/pages/misc/editWebRecommendation.xhtml?webRecommendationId=" + selectedItems.get(0).getId() + "&faces-redirect=true";
	}

	/**
	 * Delete entries.
	 * 
	 * @return Faces outcome.
	 */
	public String delete() {
		List<WebRecommendation> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		for (WebRecommendation webRecommendation : selectedItems) {
			webRecommendationService.delete(webRecommendation);
		}
		return "/pages/common/confirmation.xhtml";
	}

	public void setWebRecommendationService(WebRecommendationService webRecommendationService) {
		this.webRecommendationService = webRecommendationService;
	}
}

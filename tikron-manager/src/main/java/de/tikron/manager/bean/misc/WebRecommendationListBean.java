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
import de.tikron.manager.service.misc.WebRecommendationService;
import de.tikron.persistence.model.misc.WebRecommendation;
import de.tikru.commons.faces.util.Message;

/**
 * Managed bean providing a selectable list of web recommendations.
 * 
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class WebRecommendationListBean extends AbstractSelectableListBean<WebRecommendation, Long> implements Serializable {

	private static final long serialVersionUID = 8882230306407537798L;
	
	@ManagedProperty(value = "#{webRecommendationService}")
	private WebRecommendationService webRecommendationService;

	public void preRenderView(ComponentSystemEvent e) {
		if (getList() == null) {
			setList(webRecommendationService.getAll());
		}
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
		return UriComponentsBuilder.newInstance().path("/pages/misc/editWebRecommendation.xhtml")
				.queryParam("webRecommendationId",  selectedItems.get(0).getId())
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
		List<WebRecommendation> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		for (WebRecommendation webRecommendation : selectedItems) {
			webRecommendationService.delete(webRecommendation);
		}
		return refresh();
	}
	/**
	 * Returns the navigation URI for the current view.
	 * 
	 * @return Die Faces-URI.
	 */
	public String getNavigationUri() {
		return UriComponentsBuilder.newInstance().path("/pages/misc/manageWebRecommendations.xhtml").build().toString();
	}

	public void setWebRecommendationService(WebRecommendationService webRecommendationService) {
		this.webRecommendationService = webRecommendationService;
	}
}

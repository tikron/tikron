/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.manager.bean.misc;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.tikron.manager.bean.common.NavigationBean;
import de.tikron.manager.navigation.ChildrenProvider;
import de.tikron.manager.navigation.LoadableNode;
import de.tikron.manager.navigation.Node;
import de.tikron.manager.navigation.NodeDescription;
import de.tikron.manager.navigation.SimpleNode;
import de.tikron.manager.service.common.Constants;
import de.tikron.manager.service.misc.WebRecommendationService;
import de.tikron.persistence.model.misc.WebRecommendation;
import de.tikru.commons.faces.util.Message;

/**
 * Managed bean providing the navigation tree for web recommendations.
 *
 * @date 21.03.2015
 * @author Titus Kruse
 */
@ManagedBean
@SessionScoped
public class WebRecommendationNavigationBean implements NavigationBean {

	private static final String MESSAGE_NODE_CLIPS = Message.getMessage("navigationNodeWebRecommendations");

	@ManagedProperty(value = "#{webRecommendationService}")
	private WebRecommendationService webRecommendationService;

	@Override
	public Node getNode() {
		return getWebRecommendationsNode();
	}

	/**
	 * @return
	 */
	private Node getWebRecommendationsNode() {
		NodeDescription description = new NodeDescription(MESSAGE_NODE_CLIPS, Constants.ICON_FOLDER_CLOSED);
		ChildrenProvider childrenProvider = new ChildrenProvider() {
			public List<Node> getChildren() {
				List<Node> nodes = new ArrayList<Node>();
				List<WebRecommendation> webRecommendations = webRecommendationService.getAll();
				for (WebRecommendation webRecommendation : webRecommendations) {
					nodes.add(getWebRecommendationNode(webRecommendation));
				}
				return nodes;
			};
		};
		return new LoadableNode("webRecommendations", description, "/pages/misc/manageWebRecommendations.xhtml", childrenProvider);
	}

	/**
	 * @return
	 */
	private Node getWebRecommendationNode(final WebRecommendation webRecommendation) {
		String identifier = "webRecommendation_" + webRecommendation.getId().toString();
		NodeDescription description = new NodeDescription(webRecommendation.getDisplayName(), Constants.ICON_DOCUMENT);
		String outcome = "/pages/misc/editWebRecommendation.xhtml?webRecommendationId=" + webRecommendation.getId();
		Node node = new SimpleNode(identifier, description, outcome);
		return node;
	}

	public void setWebRecommendationService(WebRecommendationService webRecommendationService) {
		this.webRecommendationService = webRecommendationService;
	}

}

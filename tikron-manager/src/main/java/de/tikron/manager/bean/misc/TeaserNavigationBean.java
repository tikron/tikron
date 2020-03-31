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
import de.tikron.manager.service.misc.TeaserService;
import de.tikron.persistence.model.misc.Teaser;
import de.tikru.commons.faces.util.Message;

/**
 * Managed bean providing the navigation tree for teasers.
 *
 * @date 31.05.2015
 * @author Titus Kruse
 */
@ManagedBean
@SessionScoped
public class TeaserNavigationBean implements NavigationBean {

	private static final String MESSAGE_NODE_TEASERS = Message.getMessage("navigationNodeTeasers");

	@ManagedProperty(value = "#{teaserService}")
	private TeaserService teaserService;

	@Override
	public Node getNode() {
		return getTeasersNode();
	}

	/**
	 * @return
	 */
	private Node getTeasersNode() {
		NodeDescription description = new NodeDescription(MESSAGE_NODE_TEASERS, Constants.ICON_FOLDER_CLOSED);
		ChildrenProvider childrenProvider = new ChildrenProvider() {
			public List<Node> getChildren() {
				List<Node> nodes = new ArrayList<Node>();
				List<Teaser> teasers = teaserService.getAll();
				for (Teaser teaser : teasers) {
					nodes.add(getTeaserNode(teaser));
				}
				return nodes;
			};
		};
		return new LoadableNode("teasers", description, "/pages/misc/manageTeasers.xhtml", childrenProvider);
	}

	/**
	 * @return
	 */
	private Node getTeaserNode(final Teaser teaser) {
		String identifier = "teaser_" + teaser.getId().toString();
		NodeDescription description = new NodeDescription(teaser.getName(), Constants.ICON_DOCUMENT);
		String outcome = "/pages/misc/editTeaser.xhtml?teaserId=" + teaser.getId();
		Node node = new SimpleNode(identifier, description, outcome);
		return node;
	}

	public void setTeaserService(TeaserService teaserService) {
		this.teaserService = teaserService;
	}

}

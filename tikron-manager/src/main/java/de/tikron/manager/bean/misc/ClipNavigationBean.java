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
import de.tikron.manager.service.misc.ClipService;
import de.tikron.persistence.model.misc.Clip;
import de.tikru.commons.faces.util.Message;

/**
 * Managed bean providing the navigation tree for video clips.
 *
 * @author Titus Kruse
 * @since 16.03.2015
 */
@ManagedBean
@SessionScoped
public class ClipNavigationBean implements NavigationBean {

	private static final String MESSAGE_NODE_CLIPS = Message.getMessage("navigationNodeClips");

	@ManagedProperty(value = "#{clipService}")
	private ClipService clipService;

	@Override
	public Node getNode() {
		return getClipsNode();
	}

	/**
	 * @return
	 */
	private Node getClipsNode() {
		NodeDescription description = new NodeDescription(MESSAGE_NODE_CLIPS, Constants.ICON_FOLDER_CLOSED);
		ChildrenProvider childrenProvider = new ChildrenProvider() {
			public List<Node> getChildren() {
				List<Node> nodes = new ArrayList<Node>();
				List<Clip> clips = clipService.getAll();
				for (Clip clip : clips) {
					nodes.add(getClipNode(clip));
				}
				return nodes;
			};
		};
		return new LoadableNode("clips", description, "/pages/misc/manageClips.xhtml", childrenProvider);
	}

	/**
	 * @return
	 */
	private Node getClipNode(final Clip clip) {
		String identifier = "clip_" + clip.getId().toString();
		NodeDescription description = new NodeDescription(clip.getName(), Constants.ICON_DOCUMENT);
		String outcome = "/pages/misc/editClip.xhtml?clipId=" + clip.getId();
		Node node = new SimpleNode(identifier, description, outcome);
		return node;
	}

	public void setClipService(ClipService clipService) {
		this.clipService = clipService;
	}

}

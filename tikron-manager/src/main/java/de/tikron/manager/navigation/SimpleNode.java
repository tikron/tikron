/**
 * Copyright (c) 2011 by Titus Kruse.
 */
package de.tikron.manager.navigation;

import java.util.ArrayList;
import java.util.List;

/**
 * A single node in the navigation tree containing node information and child nodes.
 * 
 * Mandatory properties are identifier and description.
 *
 * @since 11.12.2011
 * @author Titus Kruse
 */
public class SimpleNode implements Node {

	private List<Node> children = new ArrayList<Node>();

	/**
	 * An identifier for this node.
	 */
	private String identifier;

	/**
	 * A descriptive text shown to the user.
	 */
	private NodeDescription description;

	/**
	 * The faces outcome, when this node is link.
	 */
	private String outcome;

	/**
	 * Holds the current state for if the children are visible or collapsed in the tree.
	 */
	private boolean expanded;

	public SimpleNode(String identifier, NodeDescription description) {
		this(identifier, description, null);
	}

	public SimpleNode(String identifier, NodeDescription description, String outcome) {
		this.identifier = identifier;
		this.description = description;
		this.outcome = outcome;
	}

	@Override
	public List<Node> getChildren() {
		return children;
	}
	
	@Override
	public void setChildren(List<Node> children) {
		this.children = children;
	}

	@Override
	public void addChild(Node node) {
		children.add(node);
	}
	
	@Override
	public Node getChild(int index) {
		return children.get(index);
	}

	@Override
	public void removeChildren() {
		children.clear();
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public NodeDescription getDescription() {
		return description;
	}

	@Override
	public void setDescription(NodeDescription description) {
		this.description = description;
	}

	@Override
	public String getOutcome() {
		return outcome;
	}

	@Override
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	@Override
	public boolean isExpanded() {
		return expanded;
	}

	@Override
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	@Override
	public boolean isLeaf() {
		return this.children.isEmpty();
	}

	@Override
	public int getDepth() {
		return checkForChildren(this, 0, 1);
	}

	private static int checkForChildren(Node node, int depth, int level) {
		if (!node.isLeaf()) {
			if (level > depth)
				depth = level;
			for (Node child : node.getChildren()) {
				depth = checkForChildren(child, depth, level + 1);
			}
		}
		return depth;
	}
}

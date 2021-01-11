/**
 * Copyright (c) 2011 by Titus Kruse.
 */
package de.tikron.manager.navigation;

/**
 * A navigation tree containing the root node and all its children.
 *
 * @author Titus Kruse
 * @since 11.12.2011
 */
public class Tree extends SimpleNode {

	public Tree() {
		super("root", new NodeDescription("Root Node"));
	}

	public Node getRoot() {
		return this;
	}

	/**
	 * Expands or callapses the nodes to the given path.
	 * 
	 * @param path A node path. The path must be a string containg the node indexes for each tree level separated by a
	 *          colon (:).
	 * @param expanded true, if the node will be expandede. false, if the node will be collapsed.
	 */
	public void expandNodePath(String path, boolean expanded) {
		// System.out.println("NavigationTree.expandNodePath()" + path + "/" + expanded);
		String[] pathIndexes = path.split(":");
		int level = 0;
		expandNode(getRoot(), pathIndexes, level, expanded);
	}

	private void expandNode(Node node, String[] pathIndexes, int level, boolean expanded) {
		int index = Integer.parseInt(pathIndexes[level]);
		// System.out.println("NavigationBean.expandNode()" + level + "/" + index);
		if (level + 1 < pathIndexes.length) {
			expandNode(node.getChild(index), pathIndexes, level + 1, expanded);
		} else {
			node.getChild(index).setExpanded(expanded);
		}
	}

}

/**
 * Copyright (c) 2011 by Titus Kruse.
 */
package de.tikron.manager.navigation;

import javax.faces.event.ActionEvent;

/**
 * A faces managed bean holding a navigation tree. The bean extends the tree by providing action event methods to control the tree. 
 *
 * @date 11.11.2011
 * @author Titus Kruse
 */
public class TreeNavigationBean {
	
	private Tree tree = new Tree();
	
	public Tree getRoot() {
		return tree;
	}
	
	/**
	 * Action method expanding the tree node with the given path.
	 * 
	 * @param e The action event.
	 */
	public void expandPath(ActionEvent e) {
		String path = (String) e.getComponent().getAttributes().get("path");
		if (path == null) {
			throw new IllegalArgumentException("path is null");
		}
		tree.expandNodePath(path, true);
	}

	/**
	 * Action method collapsing the tree node with the given path.
	 * 
	 * @param e The action event.
	 */
	public void collapsPath(ActionEvent e) {
		String path = (String) e.getComponent().getAttributes().get("path");
		if (path == null) {
			throw new IllegalArgumentException("path is null");
		}
		tree.expandNodePath(path, false);
	}

}

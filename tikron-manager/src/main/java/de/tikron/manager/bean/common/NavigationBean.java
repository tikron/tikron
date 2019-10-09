package de.tikron.manager.bean.common;

import de.tikron.manager.navigation.Node;

public interface NavigationBean {

	/**
	 * Returns the root node of this provided by this navigation bean.
	 * 
	 * @return The navigation node.
	 */
	public Node getNode();

}
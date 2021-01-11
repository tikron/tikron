/**
 * Copyright (c) 2011 by Titus Kruse.
 */
package de.tikron.manager.navigation;

import java.util.List;

/**
 * Abstract class used for a lazy loadable navigation node.
 *
 * @since 17.12.2011
 * @author Titus Kruse
 */
public abstract class ChildrenProvider {

	/**
	 * Returns the node children. The method will be called by the loadable node, if a will be expanded the first time.
	 * 
	 * @return A list of children.
	 */
	public abstract List<Node> getChildren();

}

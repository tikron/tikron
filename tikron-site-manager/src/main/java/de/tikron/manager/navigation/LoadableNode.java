/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.navigation;

/**
 * Extends the navigation tree node by a feature to "lazy load" its children.
 *
 * @date 14.01.2012
 * @author Titus Kruse
 */
public class LoadableNode extends SimpleNode {

	/**
	 * The class providing the children, if the node should be expanded the first time.
	 */
	private ChildrenProvider childrenProvider;

	private boolean childrenLoaded;

	public LoadableNode(String identifier, NodeDescription description, ChildrenProvider childrenProvider) {
		super(identifier, description);
		this.childrenProvider = childrenProvider;
	}

	public LoadableNode(String identifier, NodeDescription description, String outcome, ChildrenProvider childrenProvider) {
		super(identifier, description, outcome);
		this.childrenProvider = childrenProvider;
	}

	public ChildrenProvider getChildrenProvider() {
		return childrenProvider;
	}

	public void setChildrenProvider(ChildrenProvider childrenProvider) {
		this.childrenProvider = childrenProvider;
	}

	/**
	 * Sets this node to expanded. When this method is called the first time, it lazy loads the children by the
	 * childrenProvider.
	 */
	@Override
	public void setExpanded(boolean expanded) {
		// Lazy load children from childrenProvider, if node will be expanded the first time
		if (expanded && !childrenLoaded) {
			setChildren(getChildrenProvider().getChildren());
			childrenLoaded = true;
		}
		super.setExpanded(expanded);
	}

	/**
	 * Checks whether this node is the last node of a branch.
	 * 
	 * @return true, if laoding of chidren has been performed, but no were children found.
	 */
	@Override
	public boolean isLeaf() {
		return childrenLoaded && super.isLeaf();
	}

}

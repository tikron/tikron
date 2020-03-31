package de.tikron.manager.navigation;

import java.util.List;

public interface Node {

	public String getIdentifier();

	public void setIdentifier(String identifier);

	public NodeDescription getDescription();

	public void setDescription(NodeDescription description);

	public String getOutcome();

	public void setOutcome(String outcome);

	public boolean isExpanded();

	public void setExpanded(boolean expanded);

	public List<Node> getChildren();
	
	public void setChildren(List<Node> children);

	public void addChild(Node node);
	
	public Node getChild(int index);

	public void removeChildren();

	/**
	 * Checks whether this node is the last node of a branch.
	 * 
	 * @return true, if this node has no children.
	 */
	public boolean isLeaf();
	
	/**
	 * Returns the maximum number of node levels in the tree beginning from this node.
	 * 
	 * @return The number of levels. 0 will be returned, if this node does not has any children.
	 */
	public int getDepth();

}
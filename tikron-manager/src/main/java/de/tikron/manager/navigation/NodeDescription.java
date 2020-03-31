/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.navigation;

/**
 * The description for a navigation tree node.
 *
 * Mandatory property is the title.
 * 
 * @date 14.01.2012
 * @author Titus Kruse
 */
public class NodeDescription {

	/**
	 * The title for the node shown to the user.
	 */
	private String title;

	/**
	 * The path to an icon.
	 */
	private String icon;

	public NodeDescription(String title) {
		this.title = title;
	}

	public NodeDescription(String title, String icon) {
		this.title = title;
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}

/**
 * Copyright (c) 2011 by Titus Kruse.
 */
package de.tikron.manager.bean.common;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 * Managed bean initializing the navigation tree for this application.
 *
 * @since 11.12.2011
 * @author Titus Kruse
 */
@ManagedBean
@SessionScoped
public class TreeNavigationBean extends de.tikron.manager.navigation.TreeNavigationBean {

	@ManagedProperty("#{galleryNavigationBean}")
	private NavigationBean galleryNavigationBean;

	@ManagedProperty("#{commentNavigationBean}")
	private NavigationBean commentNavigationBean;

	@ManagedProperty("#{userNavigationBean}")
	private NavigationBean userNavigationBean;

	@ManagedProperty("#{clipNavigationBean}")
	private NavigationBean clipNavigationBean;

	@ManagedProperty("#{teaserNavigationBean}")
	private NavigationBean teaserNavigationBean;

	@ManagedProperty("#{webRecommendationNavigationBean}")
	private NavigationBean webRecommendationNavigationBean;

	@PostConstruct
	public void init() {
		getRoot().removeChildren();
		getRoot().addChild(userNavigationBean.getNode());
		getRoot().addChild(commentNavigationBean.getNode());
		getRoot().addChild(galleryNavigationBean.getNode());
		getRoot().addChild(clipNavigationBean.getNode());
		getRoot().addChild(teaserNavigationBean.getNode());
		getRoot().addChild(webRecommendationNavigationBean.getNode());
	}

	public void setGalleryNavigationBean(NavigationBean galleryNavigationBean) {
		this.galleryNavigationBean = galleryNavigationBean;
	}

	public void setCommentNavigationBean(NavigationBean commentNavigationBean) {
		this.commentNavigationBean = commentNavigationBean;
	}

	public void setUserNavigationBean(NavigationBean userNavigationBean) {
		this.userNavigationBean = userNavigationBean;
	}

	public void setClipNavigationBean(NavigationBean clipNavigationBean) {
		this.clipNavigationBean = clipNavigationBean;
	}

	public void setTeaserNavigationBean(NavigationBean teaserNavigationBean) {
		this.teaserNavigationBean = teaserNavigationBean;
	}

	public void setWebRecommendationNavigationBean(NavigationBean webRecommendationNavigationBean) {
		this.webRecommendationNavigationBean = webRecommendationNavigationBean;
	}

}

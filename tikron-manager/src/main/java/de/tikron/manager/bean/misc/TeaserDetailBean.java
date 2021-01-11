/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.bean.misc;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import de.tikron.manager.bean.common.GenericCRUDBean;
import de.tikron.manager.service.misc.TeaserService;
import de.tikron.persistence.model.misc.Teaser;

/**
 * Backing bean for CRUD operations on a single video teaser.
 * 
 * @author Titus Kruse
 * @since 31.05.2015
 */
@ManagedBean
@ViewScoped
public class TeaserDetailBean extends GenericCRUDBean<Teaser, Long> {

	private static final long serialVersionUID = 416253007486656526L;
	@ManagedProperty(value = "#{teaserService}")
	private TeaserService teaserService;

	@Override
	protected Teaser initEntity() {
		return new Teaser();
	}

	public void setTeaserService(TeaserService teaserService) {
		super.setService(teaserService);
	}
}

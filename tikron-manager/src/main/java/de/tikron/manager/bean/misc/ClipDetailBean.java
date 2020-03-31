/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.bean.misc;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import de.tikron.manager.bean.common.GenericCRUDBean;
import de.tikron.manager.service.misc.ClipService;
import de.tikron.persistence.model.misc.Clip;

/**
 * Backing bean for CRUD operations on a single video clip.
 * 
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class ClipDetailBean extends GenericCRUDBean<Clip, Long> {

	private static final long serialVersionUID = -3793500956704110241L;
	
	@ManagedProperty(value = "#{clipService}")
	private ClipService clipService;

	@Override
	protected Clip initEntity() {
		return new Clip();
	}

	public void setClipService(ClipService clipService) {
		super.setService(clipService);
	}
}

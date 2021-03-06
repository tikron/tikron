/**
 * Copyright (c) 2014 by Titus Kruse.
 */
package de.tikron.manager.bean.gallery;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import de.tikron.persistence.model.gallery.DisplayType;

/**
 * Helper bean providing enumeration values.
 *
 * @author Titus Kruse
 * @since 24.11.2014
 */
@ManagedBean
@ApplicationScoped
public class DisplayTypeBean {

	public DisplayType[] getDisplayTypes() {
		return DisplayType.values();
	}

}

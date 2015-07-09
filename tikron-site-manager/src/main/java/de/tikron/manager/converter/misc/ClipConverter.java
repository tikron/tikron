/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.converter.misc;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.tikron.manager.converter.common.NumericKeyEntityConverter;
import de.tikron.manager.service.common.ReadingService;
import de.tikron.persistence.model.misc.Clip;

/**
 * Converter used as an interface between primary key and entity.
 * 
 * @date 12.02.2012
 * @author Titus Kruse
 */
@ManagedBean
@ApplicationScoped
// Service won't be set in FacesConverter
// @FacesConverter(forClass=Clip.class)
public class ClipConverter extends NumericKeyEntityConverter<Clip> {

	@ManagedProperty(value = "#{clipService}")
	private ReadingService<Clip, Long> clipService;

	public ClipConverter() {
		super(Clip.class);
	}

	public void setClipService(ReadingService<Clip, Long> clipService) {
		super.setService(clipService);
	}

}

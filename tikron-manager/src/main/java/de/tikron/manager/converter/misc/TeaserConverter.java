/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.manager.converter.misc;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.tikron.manager.converter.common.NumericKeyEntityConverter;
import de.tikron.manager.service.common.ReadingService;
import de.tikron.persistence.model.misc.Teaser;

/**
 * Converter used as an interface between primary key and entity.
 * 
 * @author Titus Kruse
 * @since 31.05.2015
 */
@ManagedBean
@ApplicationScoped
// Service won't be set in FacesConverter
// @FacesConverter(forClass=Teaser.class)
public class TeaserConverter extends NumericKeyEntityConverter<Teaser> {

	@ManagedProperty(value = "#{teaserService}")
	private ReadingService<Teaser, Long> teaserService;

	public TeaserConverter() {
		super(Teaser.class);
	}

	public void setTeaserService(ReadingService<Teaser, Long> teaserService) {
		super.setService(teaserService);
	}

}

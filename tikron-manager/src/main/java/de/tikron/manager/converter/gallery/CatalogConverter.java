/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.converter.gallery;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.tikron.manager.converter.common.NumericKeyEntityConverter;
import de.tikron.manager.service.gallery.CatalogService;
import de.tikron.persistence.model.gallery.Catalog;

/**
 * Converter used as an interface between primary key and entity.
 * 
 * @since 12.02.2012
 * @author Titus Kruse
 */
@ManagedBean
@ApplicationScoped
// Service won't be set in FacesConverter
// @FacesConverter(forClass=Catalog.class)
public class CatalogConverter extends NumericKeyEntityConverter<Catalog> {

	@ManagedProperty(value = "#{catalogService}")
	private CatalogService catalogService;

	public CatalogConverter() {
		super(Catalog.class);
	}

	public void setCatalogService(CatalogService catalogService) {
		super.setService(catalogService);
	}

}

/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.assembler.gallery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tikron.persistence.model.gallery.Catalog;
import de.tikron.webapp.model.gallery.BasicCatalogDTO;
import de.tikron.webapp.model.gallery.CatalogDTO;
import de.tikron.webapp.util.SeoUtils;
import de.tikru.commons.util.FormattedTextCompiler;

/**
 * Component class mapping from/to data transfer objects of type Catalog. 
 *
 * @author Titus Kruse
 * @since 02.01.2015
 */
@Component
public class CatalogDTOAssembler {
	
	private FormattedTextCompiler textCompiler = FormattedTextCompiler.getInstance();
	
	private SeoUtils seoUtils = SeoUtils.getInstance();
	
	private CategoryDTOAssembler categoryDTOAssembler;

	public BasicCatalogDTO toBasicDTO(Catalog catalog) {
		return toBasicDTO(catalog, false);
	}

	public BasicCatalogDTO toBasicDTO(Catalog catalog, boolean includeChildren) {
		String seoName = seoUtils.adjustRequestParameterValue(catalog.getDisplayName());
		BasicCatalogDTO dto = new BasicCatalogDTO(catalog.getId(), catalog.getName(), catalog.getTitle(),
				catalog.getSequence(), seoName);
		if (includeChildren) {
			dto.addCategories(categoryDTOAssembler.toBasicDTOList(catalog.getCategories()));
		}
		return dto;
	}
	
	public CatalogDTO toDTO(Catalog catalog) {
		return toDTO(catalog, false);
	}
	
	public CatalogDTO toDTO(Catalog catalog, boolean includeChildren) {
		String longDescription = textCompiler.compile(catalog.getLongDescription());
		String seoName = seoUtils.adjustRequestParameterValue(catalog.getDisplayName());
		CatalogDTO dto = new CatalogDTO(catalog.getId(), catalog.getName(), catalog.getTitle(),
				catalog.getShortDescription(), longDescription, catalog.getSequence(), seoName);
		if (includeChildren) {
			dto.addCategories(categoryDTOAssembler.toBasicDTOList(catalog.getCategories()));
		}
		return dto;
	}
	
	public List<BasicCatalogDTO> toBasicDTOList(Collection<Catalog> catalogs) {
		return toBasicDTOList(catalogs, false);
	}

	public List<BasicCatalogDTO> toBasicDTOList(Collection<Catalog> catalogs, boolean includeChildren) {
		List<BasicCatalogDTO> dtoList = new ArrayList<BasicCatalogDTO>(catalogs.size());
		for (Catalog catalog : catalogs) {
			dtoList.add(toBasicDTO(catalog, includeChildren));
		}
		return dtoList;
	}

	@Autowired
	public void setCategoryDTOAssembler(CategoryDTOAssembler categoryDTOAssembler) {
		this.categoryDTOAssembler = categoryDTOAssembler;
	}

}

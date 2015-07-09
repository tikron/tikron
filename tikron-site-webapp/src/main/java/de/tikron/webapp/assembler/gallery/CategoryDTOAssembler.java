/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.assembler.gallery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tikron.common.FormattedTextCompiler;
import de.tikron.persistence.model.gallery.Category;
import de.tikron.webapp.model.gallery.BasicCatalogDTO;
import de.tikron.webapp.model.gallery.BasicCategoryDTO;
import de.tikron.webapp.model.gallery.CategoryDTO;
import de.tikron.webapp.util.SeoUtils;

/**
 * Component class mapping from/to data transfer objects of type Category. 
 *
 * @date 02.01.2015
 * @author Titus Kruse
 */
@Component
public class CategoryDTOAssembler {
	
	private FormattedTextCompiler textCompiler = FormattedTextCompiler.getInstance();
	
	private SeoUtils seoUtils = SeoUtils.getInstance();
	
	private CatalogDTOAssembler catalogDTOAssembler;
	
	public BasicCategoryDTO toBasicDTO(Category category) {
		BasicCatalogDTO catalogDTO = catalogDTOAssembler.toBasicDTO(category.getCatalog(), false);
		String seoName = seoUtils.adjustRequestParameterValue(category.getDisplayName());
		return new BasicCategoryDTO(category.getId(), catalogDTO, category.getName(), category.getTitle(),
				category.getShortTitle(), category.getSequence(), category.getCategoryType().getId().name(), category.getDisplayType(), seoName);
	}

	public CategoryDTO toDTO(Category category) {
		BasicCatalogDTO catalogDTO = catalogDTOAssembler.toBasicDTO(category.getCatalog(), false);
		String longDescription = textCompiler.compile(category.getLongDescription());
		String seoName = seoUtils.adjustRequestParameterValue(category.getDisplayName());
		return new CategoryDTO(category.getId(), catalogDTO, category.getName(), category.getTitle(),
				category.getShortTitle(), category.getShortDescription(), longDescription,
				category.getImageName(), category.getSequence(), category.getCategoryType().getId().name(), category.getDisplayType(), seoName);
	}
	
	public <T extends BasicCategoryDTO> T toDTO(Category category, Class<T> type) {
		if (type.equals(CategoryDTO.class)) {
			BasicCatalogDTO catalogDTO = catalogDTOAssembler.toBasicDTO(category.getCatalog(), false);
			String longDescription = textCompiler.compile(category.getLongDescription());
			String seoName = seoUtils.adjustRequestParameterValue(category.getDisplayName());
			return type.cast(new CategoryDTO(category.getId(), catalogDTO, category.getName(), category.getTitle(),
					category.getShortTitle(), category.getShortDescription(), longDescription,
					category.getImageName(), category.getSequence(), category.getCategoryType().getId().name(), category.getDisplayType(), seoName));
		}	else if (type.equals(BasicCategoryDTO.class)) {
			BasicCatalogDTO catalogDTO = catalogDTOAssembler.toBasicDTO(category.getCatalog(), false);
			String seoName = seoUtils.adjustRequestParameterValue(category.getDisplayName());
			return type.cast(new BasicCategoryDTO(category.getId(), catalogDTO, category.getName(), category.getTitle(),
					category.getShortTitle(), category.getSequence(), category.getCategoryType().getId().name(), category.getDisplayType(), seoName));
		} else {
			throw new IllegalArgumentException("Unsupported subclass of type BasicCategoryDTO.");
		}
	}
	
	public List<BasicCategoryDTO> toBasicDTOList(Collection<Category> categories) {
		List<BasicCategoryDTO> dtoList = new ArrayList<BasicCategoryDTO>(categories.size());
		for (Category category : categories) {
			dtoList.add(toBasicDTO(category));
		}
		return dtoList;
	}
	
	public <T extends BasicCategoryDTO> List<T> toDTOList(Collection<Category> categories, Class<T> type) {
		List<T> dtoList = new ArrayList<T>(categories.size());
		for (Category category : categories) {
			dtoList.add(toDTO(category, type));
		}
		return dtoList;
	}

	@Autowired
	public void setCatalogDTOAssembler(CatalogDTOAssembler catalogDTOAssembler) {
		this.catalogDTOAssembler = catalogDTOAssembler;
	}

}

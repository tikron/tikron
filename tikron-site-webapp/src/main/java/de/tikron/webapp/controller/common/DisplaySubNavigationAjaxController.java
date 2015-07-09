/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.controller.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.tikron.persistence.model.gallery.Catalog;
import de.tikron.persistence.model.gallery.Category;
import de.tikron.webapp.assembler.gallery.CatalogDTOAssembler;
import de.tikron.webapp.assembler.gallery.CategoryDTOAssembler;
import de.tikron.webapp.model.gallery.BasicCatalogDTO;
import de.tikron.webapp.model.gallery.BasicCategoryDTO;
import de.tikron.webapp.service.gallery.GalleryService;

/**
 * 
 *
 * @date 12.01.2015
 * @author Titus Kruse
 */
@Controller
@RequestMapping("/displaySubNavAjax.html")
public class DisplaySubNavigationAjaxController extends AbstractController {

	private GalleryService galleryService;
	
	private CatalogDTOAssembler catalogDTOAssembler;
	
	private CategoryDTOAssembler categoryDTOAssembler;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(ModelMap model, @RequestParam(ControllerConstants.REQUEST_PARAM_CATALOG_ID) Long catalogId) {
		// Validate request parameters
		if (catalogId == null) {
			throw new ApplicationException("exception.missingParameterValue", new Object[]{ControllerConstants.REQUEST_PARAM_CATALOG_ID});
		}
		// Fetch catalog and categories 
		Catalog catalog = galleryService.getCatalogWithCategories(catalogId);
		if (catalog != null && catalog.getVisible().booleanValue()) {
			BasicCatalogDTO catalogDTO = catalogDTOAssembler.toBasicDTO(catalog);
			// Filter categories here, because in JPA conditions for child entities are not available.
			List<BasicCategoryDTO> categoryDTOs = new ArrayList<BasicCategoryDTO>();
			for (Category category : catalog.getCategories()) {
				if (category.getVisible()) {
					categoryDTOs.add(categoryDTOAssembler.toDTO(category));
				}
			}
			Collections.sort(categoryDTOs, Comparator.comparingDouble(BasicCategoryDTO::getSequence));
			catalogDTO.addCategories(categoryDTOs);
			// Add catalog with categories to view model
			model.addAttribute("catalog", catalogDTO);
		} else {
			throw new ResourceNotFoundException("exception.catalogNotFound", new Object[]{catalogId});
		}
		return ViewConstants.DISPLAY_SUB_NAVIGATION_AJAX;
	}

	@Autowired
	public void setGalleryService(GalleryService galleryService) {
		this.galleryService = galleryService;
	}
	
	@Autowired
	public void setCatalogDTOAssembler(CatalogDTOAssembler catalogDTOAssembler) {
		this.catalogDTOAssembler = catalogDTOAssembler;
	}

	@Autowired
	public void setCategoryDTOAssembler(CategoryDTOAssembler categoryDTOAssembler) {
		this.categoryDTOAssembler = categoryDTOAssembler;
	}

}

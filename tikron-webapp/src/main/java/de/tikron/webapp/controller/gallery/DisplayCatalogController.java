/**
 * Copyright (c) 2013 by Titus Kruse.
 */
package de.tikron.webapp.controller.gallery;

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
import de.tikron.webapp.controller.common.AbstractPageController;
import de.tikron.webapp.controller.common.ApplicationException;
import de.tikron.webapp.controller.common.ControllerConstants;
import de.tikron.webapp.controller.common.ResourceNotFoundException;
import de.tikron.webapp.controller.common.ViewConstants;
import de.tikron.webapp.model.gallery.CatalogDTO;
import de.tikron.webapp.model.gallery.CategoryDTO;
import de.tikron.webapp.service.gallery.GalleryService;
import de.tikron.webapp.util.SeoURI;

/**
 * Zeigt einen Katalog an.
 * 
 * @author Titus Kruse
 * @since 02.01.2013
 */
@Controller
@RequestMapping("/gallery/displayCatalog.html")
public class DisplayCatalogController extends AbstractPageController {

	private GalleryService galleryService;
	
	private CatalogDTOAssembler catalogDTOAssembler;
	
	private CategoryDTOAssembler categoryDTOAssembler;

	@RequestMapping(method = RequestMethod.GET)
	public void doGet(ModelMap model, @RequestParam(ControllerConstants.REQUEST_PARAM_CATALOG_ID) Long catalogId) {
		// Validate request parameters
		if (catalogId == null) {
			throw new ApplicationException("exception.missingParameterValue", new Object[]{ControllerConstants.REQUEST_PARAM_CATALOG_ID});
		}
		// Fetch catalog and categories 
		Catalog catalog = galleryService.getCatalog(catalogId);
		if (catalog != null && catalog.getVisible().booleanValue()) {
			// Add catalog
			CatalogDTO catalogDto = catalogDTOAssembler.toDTO(catalog, false);
			model.addAttribute("catalog", catalogDto);
			// Add categories
			List<Category> categories = galleryService.getCategories(catalog, true);
			List<CategoryDTO> categoryDTOs = categoryDTOAssembler.toDTOList(categories, CategoryDTO.class);
			model.addAttribute("categories", categoryDTOs);
			// Add or override HTML meta info
			model.addAttribute(MODEL_ATTR_CANONICAL_URL, buildCanonicalUrl(catalog));
			model.addAttribute(MODEL_ATTR_PAGE_TITLE, formatPageTitle(catalog.getDisplayName()));
			model.addAttribute(MODEL_ATTR_PAGE_DESCRIPTION, catalog.getShortDescription());
		} else {
			throw new ResourceNotFoundException("exception.catalogNotFound", new Object[]{catalogId});
		}
	}

	private static String buildCanonicalUrl(Catalog catalog) {
		SeoURI uri = new SeoURI(ViewConstants.GALLERY_DISPLAY_CATALOG);
		uri.addParameter(ControllerConstants.REQUEST_PARAM_CATALOG_ID, catalog.getId());
		uri.addParameter(ControllerConstants.REQUEST_PARAM_SEO_NAME, catalog.getDisplayName());
		return uri.toString();
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

	/*
	 * Will be set in doGet() depending on request parameter.
	 * 
	 * @Override public String getPageDescription() { return getCatalog().getShortDescription(); }
	 * 
	 * @Override public String getSubTitle() { return getCatalog().getTitle(); }
	 */

}

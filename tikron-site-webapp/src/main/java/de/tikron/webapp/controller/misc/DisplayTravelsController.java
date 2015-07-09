/**
 * Copyright (c) 2013 by Titus Kruse.
 */
package de.tikron.webapp.controller.misc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.tikron.persistence.model.gallery.Catalog;
import de.tikron.persistence.model.gallery.CatalogName;
import de.tikron.persistence.model.gallery.Category;
import de.tikron.webapp.assembler.gallery.CatalogDTOAssembler;
import de.tikron.webapp.assembler.gallery.CategoryDTOAssembler;
import de.tikron.webapp.controller.common.AbstractPageController;
import de.tikron.webapp.controller.common.ResourceNotFoundException;
import de.tikron.webapp.model.gallery.BasicCategoryDTO;
import de.tikron.webapp.model.gallery.CatalogDTO;
import de.tikron.webapp.service.gallery.GalleryService;

/**
 * Zeigt die Index-Seite zum Bereich "Reisen" an. Weil hier auch Bilder angezeigt werden, liefert diese Action
 * auch einen Zugriff auf den zugehörigen Katalog und desses Kategorien.
 * 
 * @date 16.12.2014
 * @author Titus Kruse
 */
@Controller
@RequestMapping("/travels/displayTravels.html")
public class DisplayTravelsController extends AbstractPageController {

	private GalleryService galleryService;
	
	private CatalogDTOAssembler catalogDTOAssembler;
	
	private CategoryDTOAssembler categoryDTOAssembler;

	@RequestMapping(method = RequestMethod.GET)
	public void doGet(ModelMap model) {
		String catalogName = CatalogName.TRAVELS.toString();
		Catalog catalog = galleryService.getCatalog(catalogName);
		if (catalog != null && catalog.getVisible().booleanValue()) {
			// Add catalog
			CatalogDTO catalogDto = catalogDTOAssembler.toDTO(catalog, false);
			model.addAttribute("catalog", catalogDto);
			// Add categories
			List<Category> categories = galleryService.getCategories(catalog, true);
			List<BasicCategoryDTO> categoryDTOs = categoryDTOAssembler.toBasicDTOList(categories);
			model.addAttribute("categories", categoryDTOs);
		} else {
			throw new ResourceNotFoundException("exception.catalogNotFound", new Object[]{catalogName});
		}
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

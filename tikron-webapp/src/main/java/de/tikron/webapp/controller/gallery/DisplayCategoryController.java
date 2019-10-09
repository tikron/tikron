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

import de.tikron.persistence.model.gallery.Category;
import de.tikron.persistence.model.gallery.Picture;
import de.tikron.webapp.assembler.gallery.CategoryDTOAssembler;
import de.tikron.webapp.assembler.gallery.PictureDTOAssembler;
import de.tikron.webapp.controller.common.AbstractPageController;
import de.tikron.webapp.controller.common.ApplicationException;
import de.tikron.webapp.controller.common.ControllerConstants;
import de.tikron.webapp.controller.common.ResourceNotFoundException;
import de.tikron.webapp.controller.common.ViewConstants;
import de.tikron.webapp.model.gallery.BasicPictureDTO;
import de.tikron.webapp.model.gallery.CategoryDTO;
import de.tikron.webapp.service.gallery.GalleryService;
import de.tikron.webapp.util.SeoURI;

/**
 * Zeigt eine Kategorie an.
 * 
 * @date 02.01.2013
 * @author Titus Kruse
 */
@Controller
@RequestMapping("/gallery/displayCategory.html")
public class DisplayCategoryController extends AbstractPageController {

	// private static int MATRIX_COLUMNS = 6;

	private GalleryService galleryService;
	
	private CategoryDTOAssembler categoryDTOAssembler;
	
	private PictureDTOAssembler pictureDTOAssembler;

	@RequestMapping(method = RequestMethod.GET)
	public void doGet(ModelMap model, @RequestParam(ControllerConstants.REQUEST_PARAM_CATEGORY_ID) Long categoryId) {
		// Validate request parameters
		if (categoryId == null) {
			throw new ApplicationException("exception.missingParameterValue", new Object[]{ControllerConstants.REQUEST_PARAM_CATEGORY_ID});
		}
		// Fetch catalog and categories 
		Category category = galleryService.getCategory(categoryId);
		if (category != null && category.getVisible().booleanValue()) {
			// Add category
			CategoryDTO categoryDto = categoryDTOAssembler.toDTO(category);
			model.addAttribute("category", categoryDto);
			// Add picture thumbnails
			List<Picture> pictures = galleryService.getPictures(category);
			List<BasicPictureDTO> pictureDTOs = pictureDTOAssembler.toBasicDTOList(pictures);
			model.addAttribute("pictures", pictureDTOs);
			// Add or override HTML meta info
			model.addAttribute(MODEL_ATTR_CANONICAL_URL, buildCanonicalUrl(category));
			model.addAttribute(MODEL_ATTR_PAGE_TITLE, formatPageTitle(category.getDisplayName()));
			model.addAttribute(MODEL_ATTR_PAGE_DESCRIPTION, category.getShortDescription());
		} else {
			throw new ResourceNotFoundException("exception.categoryNotFound", new Object[]{categoryId});
		}
	}

	private static String buildCanonicalUrl(Category category) {
		SeoURI uri = new SeoURI(ViewConstants.GALLERY_DISPLAY_CATEGORY);
		uri.addParameter(ControllerConstants.REQUEST_PARAM_CATEGORY_ID, category.getId());
		uri.addParameter(ControllerConstants.REQUEST_PARAM_SEO_NAME, category.getDisplayName());
		return uri.toString();
	}

	@Autowired
	public void setGalleryService(GalleryService galleryService) {
		this.galleryService = galleryService;
	}

	@Autowired
	public void setCategoryDTOAssembler(CategoryDTOAssembler categoryDTOAssembler) {
		this.categoryDTOAssembler = categoryDTOAssembler;
	}

	@Autowired
	public void setPictureDTOAssembler(PictureDTOAssembler pictureDTOAssembler) {
		this.pictureDTOAssembler = pictureDTOAssembler;
	}

	/**
	 * Erzeugt eine 2-dimensionales Matrix aus Bildern.
	 * 
	 * @param pictures Liste von Bildern.
	 * @return 2-dimensionales Array aus Pictures.
	 */
	/*
	 * private Picture[][] getPictureMatrix(List<Picture> pictures) { if (pictures != null) { int matrixColumns =
	 * MATRIX_COLUMNS; int matrixRows = (pictures.size() - 1) / matrixColumns + 1; Picture[][] matrix = new
	 * Picture[matrixRows][matrixColumns]; Iterator<Picture> pictureIterator = pictures.iterator(); for (int row = 0; row
	 * < matrixRows; ++row) for (int column = 0; column < matrixColumns; ++column) if (pictureIterator.hasNext())
	 * matrix[row][column] = pictureIterator.next(); else matrix[row][column] = null; return matrix; } else { return null;
	 * } }
	 */
	/*
	 * @Override public String getPageDescription() { return getCategory().getShortDescription(); }
	 * 
	 * @Override public String getSubTitle() { return getCategory().getTitle(); }
	 */

}

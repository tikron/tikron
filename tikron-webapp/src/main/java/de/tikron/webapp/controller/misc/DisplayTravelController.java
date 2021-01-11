/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.controller.misc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.tikron.persistence.model.gallery.Category;
import de.tikron.persistence.model.gallery.Picture;
import de.tikron.persistence.model.user.CategoryComment;
import de.tikron.persistence.model.user.CommentTypeId;
import de.tikron.webapp.assembler.gallery.CategoryDTOAssembler;
import de.tikron.webapp.assembler.gallery.PictureDTOAssembler;
import de.tikron.webapp.assembler.user.CommentDTOAssembler;
import de.tikron.webapp.controller.common.AbstractPageController;
import de.tikron.webapp.controller.common.ApplicationException;
import de.tikron.webapp.controller.common.ControllerConstants;
import de.tikron.webapp.controller.common.ResourceNotFoundException;
import de.tikron.webapp.controller.common.ViewConstants;
import de.tikron.webapp.controller.user.AddCommentController;
import de.tikron.webapp.model.gallery.CategoryDTO;
import de.tikron.webapp.model.gallery.PictureDTO;
import de.tikron.webapp.model.user.CommentDTO;
import de.tikron.webapp.model.user.CommentForm;
import de.tikron.webapp.service.gallery.GalleryService;
import de.tikron.webapp.util.SeoURI;

/**
 * A controller showing a single travel report. Most of the information is pulled from the category table, so this
 * controller works similar like {@link de.tikron.webapp.controller.gallery.DisplayCategoryController}.
 * 
 * Note: This controller can be enhanced to show all kind of reports later.
 * 
 * @author Titus Kruse
 * @since 25.07.2014
 */
@Controller
@RequestMapping("/travels/displayTravel.html")
public class DisplayTravelController extends AbstractPageController {

	private GalleryService galleryService;
	
	private CategoryDTOAssembler categoryDTOAssembler;
	
	private PictureDTOAssembler pictureDTOAssembler;
	
	private CommentDTOAssembler commentDTOAssembler;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(ModelMap model, @RequestParam(ControllerConstants.REQUEST_PARAM_CATEGORY_ID) Long categoryId) {
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
			// Load pictures for travel report category and store in map for access by name in front end.
			List<Picture> pictures = galleryService.getPictures(category);
			Map<String, PictureDTO> pictureDTOs = new HashMap<String, PictureDTO>();
			for (Picture picture : pictures) {
				pictureDTOs.put(picture.getName(), pictureDTOAssembler.toDTO(picture));
			}
			model.addAttribute("pictures", pictureDTOs);
			// Prepare comment form
			model.addAttribute(AddCommentController.MODEL_ATTR_COMMENT_FORM, new CommentForm(CommentTypeId.CATEGORY, category.getId()));
			// Prepare comment list
			List<CategoryComment> comments = galleryService.getComments(category);
			List<CommentDTO> commentDTOs = commentDTOAssembler.toDTOList(comments, CommentDTO.class, localizationContext);
			model.addAttribute("comments", commentDTOs);
			// Add or override HTML meta info
			model.addAttribute(MODEL_ATTR_CANONICAL_URL, buildCanonicalUrl(category));
			model.addAttribute(MODEL_ATTR_PAGE_TITLE, formatPageTitle(category.getDisplayName()));
			model.addAttribute(MODEL_ATTR_PAGE_DESCRIPTION, category.getShortDescription());
			// Build view name to use one view per report
			return viewName(category);
		} else {
			throw new ResourceNotFoundException("exception.categoryNotFound", new Object[]{categoryId});
		}
	}

	/**
	 * Build view name depending on given category.
	 * 
	 * @param category The category.
	 * @return The view name.
	 */
	private String viewName(Category category) {
		return ViewConstants.TRAVELS_DISPLAY_TRAVEL + "_" + category.getName();
	}

	private static String buildCanonicalUrl(Category category) {
		SeoURI uri = new SeoURI(ViewConstants.TRAVELS_DISPLAY_TRAVEL);
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

	@Autowired
	public void setCommentDTOAssembler(CommentDTOAssembler commentDTOAssembler) {
		this.commentDTOAssembler = commentDTOAssembler;
	}

}

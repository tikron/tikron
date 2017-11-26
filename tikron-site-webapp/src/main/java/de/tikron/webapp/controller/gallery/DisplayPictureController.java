/**
 * Copyright (c) 2013 by Titus Kruse.
 */
package de.tikron.webapp.controller.gallery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.tikron.persistence.model.gallery.Picture;
import de.tikron.persistence.model.user.CommentTypeId;
import de.tikron.persistence.model.user.PictureComment;
import de.tikron.persistence.model.user.RatingResult;
import de.tikron.webapp.assembler.gallery.PictureDTOAssembler;
import de.tikron.webapp.assembler.user.CommentDTOAssembler;
import de.tikron.webapp.controller.common.AbstractPageController;
import de.tikron.webapp.controller.common.ApplicationException;
import de.tikron.webapp.controller.common.ControllerConstants;
import de.tikron.webapp.controller.common.ResourceNotFoundException;
import de.tikron.webapp.controller.common.ViewConstants;
import de.tikron.webapp.controller.user.AddCommentController;
import de.tikron.webapp.model.gallery.BasicPictureDTO;
import de.tikron.webapp.model.gallery.PictureDTO;
import de.tikron.webapp.model.user.CommentDTO;
import de.tikron.webapp.model.user.CommentForm;
import de.tikron.webapp.service.gallery.GalleryService;
import de.tikron.webapp.service.user.RatingService;
import de.tikron.webapp.util.Pager;
import de.tikron.webapp.util.RobotsDirective;
import de.tikron.webapp.util.SeoURI;
import de.tikron.webapp.util.SeoUtils;

/**
 * Zeigt ein Bild an.
 * 
 * @date 02.01.2013
 * @author Titus Kruse
 */
@Controller
@RequestMapping("/gallery/displayPicture*.html")
public class DisplayPictureController extends AbstractPageController {

	private GalleryService galleryService;
	
	private RatingService ratingService;
	
	private PictureDTOAssembler pictureDTOAssembler;
	
	private CommentDTOAssembler commentDTOAssembler;
	
	@RequestMapping(method = RequestMethod.GET)
	public String doGet(ModelMap model, @RequestParam(ControllerConstants.REQUEST_PARAM_PICTURE_ID) Long pictureId) {
		// Validate request parameters
		if (pictureId == null) {
			throw new ApplicationException("exception.missingParameterValue", new Object[]{ControllerConstants.REQUEST_PARAM_PICTURE_ID});
		}
		// Fetch picture 
		Picture picture = galleryService.getPicture(pictureId);
		if (picture != null) {
			// Add picture to view model
			PictureDTO pictureDTO = pictureDTOAssembler.toDTO(picture);
			model.addAttribute("picture", pictureDTO);
			// Add pager
			// TODO Reduce number of queries and entities to fetch
			List<BasicPictureDTO> pictureDTOs = pictureDTOAssembler.toBasicDTOList(galleryService.getPictures(picture.getCategory()));
			Pager<BasicPictureDTO> pager = new Pager<>(pictureDTOs);
			pager.setCurrent(pictureDTO);
			model.addAttribute("pager", pager);
			// Prepare comment form
			model.addAttribute(AddCommentController.MODEL_ATTR_COMMENT_FORM, new CommentForm(CommentTypeId.PICTURE, picture.getId()));
			// Add comments
			List<PictureComment> comments = galleryService.getComments(picture);
			List<CommentDTO> commentDTOs = commentDTOAssembler.toDTOList(comments, CommentDTO.class, localizationContext);
			model.addAttribute("comments", commentDTOs);
			// Add rating
			RatingResult rating = ratingService.getRatingResult(picture);
			model.addAttribute("rating", rating);
			if (StringUtils.isEmpty(picture.getDescription())) {
				model.addAttribute(MODEL_ATTR_ROBOTS_DIRECTIVE, RobotsDirective.NOINDEX.toString());
			}
			// Add or override HTML meta info
			model.addAttribute(MODEL_ATTR_CANONICAL_URL, buildCanonicalUrl(picture));
			model.addAttribute(MODEL_ATTR_PAGE_TITLE, formatPageTitle(picture.getDisplayName()));
			model.addAttribute(MODEL_ATTR_PAGE_DESCRIPTION, SeoUtils.getInstance().formatMetaDescription(picture.getDescription()));
			if (isXmlHttpRequest()) {
				return ViewConstants.GALLERY_DISPLAY_PICTURE_AJAX;
			} else {
				return ViewConstants.GALLERY_DISPLAY_PICTURE;
			}
		} else {
			throw new ResourceNotFoundException("exception.pictureNotFound", new Object[]{pictureId});
		}
	}

	private static String buildCanonicalUrl(Picture picture) {
		SeoURI uri = new SeoURI(ViewConstants.GALLERY_DISPLAY_PICTURE);
		uri.addParameter(ControllerConstants.REQUEST_PARAM_PICTURE_ID, picture.getId());
		uri.addParameter(ControllerConstants.REQUEST_PARAM_SEO_NAME, picture.getDisplayName());
		return uri.toString();
	}

	@Autowired
	public void setGalleryService(GalleryService galleryService) {
		this.galleryService = galleryService;
	}

	@Autowired
	public void setRatingService(RatingService ratingService) {
		this.ratingService = ratingService;
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

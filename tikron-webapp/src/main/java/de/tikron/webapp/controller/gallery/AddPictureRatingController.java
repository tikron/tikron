/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.controller.gallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.tikron.persistence.model.gallery.Picture;
import de.tikron.persistence.model.user.PictureRating;
import de.tikron.persistence.model.user.RatingResult;
import de.tikron.webapp.controller.common.ApplicationException;
import de.tikron.webapp.controller.common.ControllerConstants;
import de.tikron.webapp.controller.common.ResourceNotFoundException;
import de.tikron.webapp.controller.user.AddRatingController;
import de.tikron.webapp.service.gallery.GalleryService;

/**
 * Controller adding a rating for a picture.
 *
 * @author Titus Kruse
 * @since 26.04.2015
 */
@Controller
@RequestMapping("/gallery/addPictureRating.json")
public class AddPictureRatingController extends AddRatingController {

	private GalleryService galleryService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public RatingResult doGet(@RequestParam(ControllerConstants.REQUEST_PARAM_PICTURE_ID) Long pictureId, @RequestParam(ControllerConstants.REQUEST_PARAM_RATING_VALUE) Double value) {
		// Validate request parameters
		if (pictureId == null) {
			throw new ApplicationException("exception.missingParameterValue", new Object[]{ControllerConstants.REQUEST_PARAM_PICTURE_ID});
		}
		if (value == null) {
			throw new ApplicationException("exception.missingParameterValue", new Object[]{ControllerConstants.REQUEST_PARAM_RATING_VALUE});
		}
		// Validate for existing picture 
		Picture picture = galleryService.getPicture(pictureId);
		if (picture == null) {
			throw new ResourceNotFoundException("exception.pictureNotFound", new Object[]{pictureId});
		}
		// Validate for allowed rating
		if (!picture.getCategory().getRateable()) {
			throw new ApplicationException("exception.objectNotRateable");
		}
		// Persist rating
		PictureRating rating = new PictureRating(picture, value);
		return ratingService.addRating(rating);
	}

	@Autowired
	public void setGalleryService(GalleryService galleryService) {
		this.galleryService = galleryService;
	}

}

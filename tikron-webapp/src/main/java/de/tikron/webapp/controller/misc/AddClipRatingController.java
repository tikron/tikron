/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.controller.misc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.tikron.persistence.model.misc.Clip;
import de.tikron.persistence.model.user.ClipRating;
import de.tikron.persistence.model.user.RatingResult;
import de.tikron.webapp.controller.common.ApplicationException;
import de.tikron.webapp.controller.common.ControllerConstants;
import de.tikron.webapp.controller.common.ResourceNotFoundException;
import de.tikron.webapp.controller.user.AddRatingController;
import de.tikron.webapp.service.misc.ClipService;

/**
 * Controller adding a rating for a video clip.
 *
 * @date 26.04.2015
 * @author Titus Kruse
 */
@Controller
@RequestMapping("/clips/addClipRating.json")
public class AddClipRatingController extends AddRatingController {

	private ClipService clipService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public RatingResult doGet(@RequestParam(ControllerConstants.REQUEST_PARAM_CLIP_ID) Long clipId, @RequestParam(ControllerConstants.REQUEST_PARAM_RATING_VALUE) Double value) {
		// Validate request parameters
		if (clipId == null) {
			throw new ApplicationException("exception.missingParameterValue", new Object[]{ControllerConstants.REQUEST_PARAM_CLIP_ID});
		}
		if (value == null) {
			throw new ApplicationException("exception.missingParameterValue", new Object[]{ControllerConstants.REQUEST_PARAM_RATING_VALUE});
		}
		// Validate for existing clip 
		Clip clip = clipService.getClip(clipId);
		if (clip == null) {
			throw new ResourceNotFoundException("exception.clipNotFound", new Object[]{clipId});
		}
		// Persist rating
		ClipRating rating = new ClipRating(clip, value);
		return ratingService.addRating(rating);
	}

	@Autowired
	public void setClipService(ClipService clipService) {
		this.clipService = clipService;
	}

}

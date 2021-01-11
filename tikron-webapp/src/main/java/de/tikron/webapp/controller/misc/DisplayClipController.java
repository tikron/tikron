/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.controller.misc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.tikron.persistence.model.misc.Clip;
import de.tikron.persistence.model.user.RatingResult;
import de.tikron.webapp.controller.common.AbstractPageController;
import de.tikron.webapp.controller.common.ApplicationException;
import de.tikron.webapp.controller.common.ControllerConstants;
import de.tikron.webapp.controller.common.ResourceNotFoundException;
import de.tikron.webapp.controller.common.ViewConstants;
import de.tikron.webapp.model.misc.ClipEntityBean;
import de.tikron.webapp.service.common.FileArchiveService;
import de.tikron.webapp.service.misc.ClipService;
import de.tikron.webapp.service.user.RatingService;
import de.tikron.webapp.util.SeoURI;

/**
 * Shows a single video clip.
 * 
 * @author Titus Kruse
 * @since 14.05.2014
 */
@Controller
@RequestMapping("/clips/displayClip.html")
public class DisplayClipController extends AbstractPageController {
	
	private ClipService clipService;
	
	private RatingService ratingService;
	
	@RequestMapping(method = RequestMethod.GET)
	public void doGet(ModelMap model, @RequestParam(ControllerConstants.REQUEST_PARAM_CLIP_ID) Long clipId) {
		// Validate request parameters
		if (clipId == null) {
			throw new ApplicationException("exception.missingParameterValue", new Object[]{ControllerConstants.REQUEST_PARAM_CLIP_ID});
		}
		// Fetch video clip
		Clip clip = clipService.getClip(clipId);
		if (clip != null) {
			// Add clip to view model
			ClipEntityBean clipEntityBean = entityBeanHelper.getBean(ClipEntityBean.NAME, clip);
			model.addAttribute("clip", clipEntityBean);
			// Add rating
			RatingResult rating = ratingService.getRatingResult(clip);
			model.addAttribute("rating", rating);
			// Add or override HTML meta info
			model.addAttribute(MODEL_ATTR_CANONICAL_URL, buildCanonicalUrl(clip));
			model.addAttribute(MODEL_ATTR_PAGE_TITLE, formatPageTitle(clip.getDisplayName()));
			model.addAttribute(MODEL_ATTR_PAGE_DESCRIPTION, clip.getShortDescription());
		} else {
			throw new ResourceNotFoundException("exception.clipNotFound", new Object[]{clipId});
		}
	}

	private static String buildCanonicalUrl(Clip clip) {
		SeoURI uri = new SeoURI(ViewConstants.CLIPS_DISPLAY_CLIP);
		uri.addParameter(ControllerConstants.REQUEST_PARAM_CLIP_ID, clip.getId());
		uri.addParameter(ControllerConstants.REQUEST_PARAM_SEO_NAME, clip.getDisplayName());
		return uri.toString();
	}

	@Autowired
	public void setClipService(ClipService clipService) {
		this.clipService = clipService;
	}

	@Autowired
	public void setRatingService(RatingService ratingService) {
		this.ratingService = ratingService;
	}

	@Autowired
	public void setFileArchiveService(FileArchiveService fileArchiveService) {
		this.fileArchiveService = fileArchiveService;
	}

}

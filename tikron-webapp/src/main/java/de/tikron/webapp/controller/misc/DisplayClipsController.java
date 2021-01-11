/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.controller.misc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.map.DefaultedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.tikron.persistence.model.misc.Clip;
import de.tikron.persistence.model.user.RatingResult;
import de.tikron.persistence.model.user.RatingTypeId;
import de.tikron.webapp.controller.common.AbstractPageController;
import de.tikron.webapp.model.misc.ClipEntityBean;
import de.tikron.webapp.service.common.FileArchiveService;
import de.tikron.webapp.service.misc.ClipService;
import de.tikron.webapp.service.user.RatingService;

/**
 * Shows a list of video clips.
 * 
 * @since 14.05.2014
 * @author Titus Kruse
 */
@Controller
@RequestMapping("/clips/displayClips.html")
public class DisplayClipsController extends AbstractPageController {
	
	private ClipService clipService;
	
	private RatingService ratingService;

	@RequestMapping(method = RequestMethod.GET)
	public void doGet(ModelMap model) {
		List<Clip> clips = clipService.getClips();
		List<ClipEntityBean> clipBeans = entityBeanHelper.toList(ClipEntityBean.NAME, clips);
		model.addAttribute("clips", clipBeans);
		List<RatingResult> ratings = ratingService.getClipResults();
		Map<Number, RatingResult> ratingsMap = toDefaultedMap(ratings, RatingTypeId.CLIP);
		model.addAttribute("ratings", ratingsMap);
	}

	/**
	 * Converts a list of ratings results into a defaulted map. A default value for a missing rating will be constructed
	 * by defaultType and missed key.
	 * 
	 * @param ratings The list of rating result.
	 * @param defaultType The default rating type ID for missing entries.
	 * @return A map of entity IDs and rating results.
	 */
	private Map<Number, RatingResult> toDefaultedMap(List<RatingResult> ratings, RatingTypeId defaultType) {
		Map<Number, RatingResult> ratingsMap = new HashMap<Number, RatingResult>(ratings.size());
		for (RatingResult rating : ratings) {
			ratingsMap.put(rating.getId(), rating);
		}
		Map<Number, RatingResult> ratingsDefaultedMap = DefaultedMap.defaultedMap(ratingsMap, new Transformer<Number, RatingResult>() {
			@Override
			public RatingResult transform(Number key) {
				return new RatingResult(defaultType, key);
			}
		});
		return ratingsDefaultedMap;
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

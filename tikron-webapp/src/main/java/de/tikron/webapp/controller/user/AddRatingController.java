/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;

import de.tikron.webapp.controller.common.AbstractController;
import de.tikron.webapp.service.user.RatingService;

/**
 * Base class for controller adding a rating for a publication.
 *
 * @date 26.04.2015
 * @author Titus Kruse
 */
public abstract class AddRatingController extends AbstractController {
	
	protected RatingService ratingService;

	@Autowired
	public void setRatingService(RatingService ratingService) {
		this.ratingService = ratingService;
	}

}

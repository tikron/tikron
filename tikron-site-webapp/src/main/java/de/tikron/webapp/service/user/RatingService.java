/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.service.user;

import java.util.List;

import de.tikron.persistence.model.gallery.Picture;
import de.tikron.persistence.model.misc.Clip;
import de.tikron.persistence.model.user.ClipRating;
import de.tikron.persistence.model.user.PictureRating;
import de.tikron.persistence.model.user.Rating;
import de.tikron.persistence.model.user.RatingResult;


/**
 * Service for rating publications. 
 *
 * @date 26.04.2015
 * @author Titus Kruse
 */
public interface RatingService {
	
	/**
	 * Returns the result of all ratings for a given picture.
	 * 
	 * @param picture The Picture.
	 * @return The rating result.
	 */
	public RatingResult getRatingResult(Picture picture);
	
	/**
	 * Returns the result of all ratings for a given video clip.
	 * 
	 * @param clip The video clip.
	 * @return The rating result.
	 */
	public RatingResult getRatingResult(Clip clip);
	
	/**
	 * Returns a list of rating results for all video clips.
	 * 
	 * @return The list of rating results.
	 */
	public List<RatingResult> getClipResults();
	
	/**
	 * Adds a rating.
	 * 
	 * @param rating The rating to add.
	 */
	public void addRating(Rating rating);
	
	/**
	 * Adds a picture rating and returns the new rating result.
	 * 
	 * @param rating The rating to add.
	 * @return The rating result.
	 */
	public RatingResult addRating(PictureRating rating);
	
	/**
	 * Adds a clip rating and returns the new rating result.
	 * 
	 * @param rating The rating to add.
	 * @return The rating result.
	 */
	public RatingResult addRating(ClipRating rating);

}

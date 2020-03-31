/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.persistence.dao.user;

import java.util.List;

import de.tikron.persistence.model.gallery.Picture;
import de.tikron.persistence.model.misc.Clip;
import de.tikron.persistence.model.user.Rating;
import de.tikron.persistence.model.user.RatingResult;
import de.tikru.commons.jpa.dao.GenericDao;

/**
 * DAO for ratings.
 *
 * @date 27.04.2015
 * @author Titus Kruse
 */
public interface RatingDao extends GenericDao<Rating, Long> {
	
	/**
	 * Returns the result of all ratings for a given picture.
	 * 
	 * @param picture The Picture.
	 * @return The rating result.
	 */
	public RatingResult getResult(Picture picture);
	
	/**
	 * Returns the result of all ratings for a given video clip.
	 * 
	 * @param clip The video clip.
	 * @return The rating result.
	 */
	public RatingResult getResult(Clip clip);
	
	/**
	 * Returns a list of rating results for all video clips.
	 * 
	 * @return The list of rating results.
	 */
	public List<RatingResult> getClipResults();
	
}

/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.service.user;

import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.tikron.persistence.dao.user.RatingDao;
import de.tikron.persistence.model.gallery.Picture;
import de.tikron.persistence.model.misc.Clip;
import de.tikron.persistence.model.user.ClipRating;
import de.tikron.persistence.model.user.PictureRating;
import de.tikron.persistence.model.user.Rating;
import de.tikron.persistence.model.user.RatingResult;
import de.tikron.persistence.model.user.RatingTypeId;

/**
 * Default implementation of {@link RatingService}.
 *
 * @author Titus Kruse
 * @since 27.04.2015
 */
@Service("ratingService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class RatingServiceImpl implements RatingService {

	private static Logger userAction = LoggerFactory.getLogger("de.tikron.webapp.service.user.UserAction");

	private RatingDao ratingDao;

	@Override
	public RatingResult getRatingResult(Picture picture) {
		// Convert null to empty rating result for easier hanlding 
		return (RatingResult) ObjectUtils.defaultIfNull(ratingDao.getResult(picture), new RatingResult(RatingTypeId.PICTURE, picture.getId()));
	}

	@Override
	public RatingResult getRatingResult(Clip clip) {
		return (RatingResult) ObjectUtils.defaultIfNull(ratingDao.getResult(clip), new RatingResult(RatingTypeId.CLIP, clip.getId()));
	}

	@Override
	public List<RatingResult> getClipResults() {
		return ratingDao.getClipResults();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addRating(Rating rating) {
		ratingDao.insert(rating);
		userAction.info(MessageFormat.format("{0} \"{1}\" rated wih score {2}.", rating.getRatingType().getDescription(), rating.getRelatedEntity().getDisplayName(), rating.getValue()));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RatingResult addRating(PictureRating rating) {
		ratingDao.insert(rating);
		userAction.info(MessageFormat.format("Picture {0} \"{1}\" rated wih score {2}.", rating.getPicture().getId(), rating.getPicture().getDisplayName(), rating.getValue()));
		return ratingDao.getResult(rating.getPicture());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RatingResult addRating(ClipRating rating) {
		ratingDao.insert(rating);
		userAction.info(MessageFormat.format("Video Clip {0} \"{1}\" rated wih score {2}.", rating.getClip().getId(), rating.getClip().getDisplayName(), rating.getValue()));
		return ratingDao.getResult(rating.getClip());
	}

	@Autowired
	public void setRatingDao(RatingDao ratingDao) {
		this.ratingDao = ratingDao;
	}

}

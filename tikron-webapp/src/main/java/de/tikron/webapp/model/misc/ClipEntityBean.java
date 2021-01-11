/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.model.misc;

import java.net.URI;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import de.tikron.persistence.model.misc.Clip;
import de.tikron.persistence.model.user.RatingResult;
import de.tikron.webapp.model.common.BaseEntityBean;
import de.tikron.webapp.service.misc.ClipService;
import de.tikron.webapp.service.user.RatingService;
import de.tikron.webapp.util.SeoUtils;
import de.tikru.commons.util.FormattedTextCompiler;

/**
 * An entity bean for a video clip.
 *
 * @author Titus Kruse
 * @since 18.03.2015
 */
public class ClipEntityBean extends BaseEntityBean<Clip> {
	
	private static final long serialVersionUID = 6884927968374984831L;

	public static final String NAME = "clipEntityBean";
	
	private String seoName;
	
	private String formattedShortDescription;
	
	private String formattedLongDescription;
	
	private RatingResult ratingResult;
	
	private Map<String, URI> videoUris;

	public ClipEntityBean(Clip entity) {
		super(entity);
	}

	/**
	 * Returns the SEO name for URLs to this entity.
	 * 
	 * @return the seoName The SEO name of this entity.
	 */
	public String getSeoName() {
		if (seoName == null) {
			seoName = SeoUtils.getInstance().adjustRequestParameterValue(getDisplayName());
		}
		return seoName;
	}
	
	public String getFormattedShortDescription() {
		if (formattedShortDescription == null) {
			formattedShortDescription = FormattedTextCompiler.getInstance().compile(getShortDescription());
		}
		return formattedShortDescription;
	}
	
	public String getFormattedLongDescription() {
		if (formattedLongDescription == null) {
			formattedLongDescription = FormattedTextCompiler.getInstance().compile(getLongDescription());
		}
		return formattedLongDescription;
	}

	@Deprecated
	public RatingResult getRatingResult() {
		if (ratingResult == null) {
			// TODO Calculating the rating count and avarage for each clip results into 1+n fetch issue.   
			RatingService ratingService = applicationContext.getBean(RatingService.class);
			ratingResult = ratingService.getRatingResult(getEntity());
		}
		return ratingResult;
	}

	public Map<String, URI> getVideoUris() {
		if (videoUris == null) {
			videoUris = applicationContext.getBean(ClipService.class).getVideoUris(getEntity());
		}
		return videoUris;
	}
	
	// Delegate methods

	public Number getId() {
		return entity.getId();
	}

	public LocalDateTime getCreatedOn() {
		return entity.getCreatedOn();
	}

	public String getName() {
		return entity.getName();
	}

	public String getTitle() {
		return entity.getTitle();
	}

	public String getShortDescription() {
		return entity.getShortDescription();
	}

	public String getLongDescription() {
		return entity.getLongDescription();
	}

	public String getImageName() {
		return entity.getImageName();
	}

	public String getVideoName() {
		return entity.getVideoName();
	}

	public Short getVideoWidth() {
		return entity.getVideoWidth();
	}

	public Short getVideoHeight() {
		return entity.getVideoHeight();
	}

	public LocalDate getDateRecorded() {
		return entity.getDateRecorded();
	}

	public Duration getPlaytime() {
		return entity.getPlaytime();
	}
	
	public String getDisplayName() {
		return entity.getDisplayName();
	}

}

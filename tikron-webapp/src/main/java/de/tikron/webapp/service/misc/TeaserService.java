/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.service.misc;

import java.util.List;

import de.tikron.persistence.model.misc.Teaser;

/**
 * Service for teasers.
 *
 * @author Titus Kruse
 * @since 01.06.2015
 */
public interface TeaserService {

	/**
	 * Returns a list of all teaser to show on the home page.
	 * 
	 * @return The list of teasers.
	 */
	public List<Teaser> getTeasers();

}

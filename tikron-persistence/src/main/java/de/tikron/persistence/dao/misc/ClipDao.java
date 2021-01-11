/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.persistence.dao.misc;

import java.util.List;

import de.tikron.persistence.model.misc.Clip;
import de.tikru.commons.jpa.dao.GenericDao;

/**
 * Data Acccess Object for video clips.
 *
 * @since 14.03.2015
 * @author Titus Kruse
 */
public interface ClipDao extends GenericDao<Clip, Long> {

	/**
	 * Returns the clip with the given name.
	 * 
	 * @param name The unique clip name.
	 * @return The video clip or null, if none has been found.
	 */
	public Clip findByName(String name);
	
	/**
	 * Fetches a list of all clips ordered by date recorded.
	 * 
	 * @return A list of video clips.
	 */
	public List<Clip> findAllOrderByDateRecorded();

}

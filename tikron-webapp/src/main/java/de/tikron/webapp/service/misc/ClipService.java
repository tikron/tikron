/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.service.misc;

import java.net.URI;
import java.util.List;
import java.util.Map;

import de.tikron.persistence.model.misc.Clip;

/**
 * Service for video clips.
 *
 * @author Titus Kruse
 * @since 18.03.2015
 */
public interface ClipService {

	/**
	 * Returns a single video clip.
	 * 
	 * @param clipId The ID of the clip to return.
	 * @return The clip or null, if not exists.
	 */
	public Clip getClip(Long clipId);

	/**
	 * Returns a list of all video clips.
	 * 
	 * @return The list of clips.
	 */
	public List<Clip> getClips();

	/**
	 * Returns the URI of the archive location for the given video clip and format.
	 * 
	 * @param clip The video clip providing the name of the video itself.
	 * @param format The encoding format (like mpg).
	 * @return The URI.
	 */
	public URI getVideoURI(Clip clip, String format);

	/**
	 * Returns a map of video formats and URIs. The map contains URIs to video files for all in the archive stored
	 * formats.
	 * 
	 * @param clip The video clip providing the name of the video itself.
	 * @return The map of formats and URIs.
	 */
	public Map<String, URI> getVideoUris(Clip clip);

}

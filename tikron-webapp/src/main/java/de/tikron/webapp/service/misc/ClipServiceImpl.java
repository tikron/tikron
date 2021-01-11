/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.service.misc;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.tikron.persistence.dao.misc.ClipDao;
import de.tikron.persistence.model.misc.Clip;

/**
 * Spring implementation of ClipService.
 *
 * @since 18.03.2015
 * @author Titus Kruse
 */
@Service("clipService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ClipServiceImpl implements ClipService {
	
	private static final String[] VIDEO_FORMATS = {"mp4", "ogv"};
	
	private ClipDao clipDao;

	@Override
	public Clip getClip(Long clipId) {
		return clipDao.findById(clipId);
	}

	@Override
	public List<Clip> getClips() {
		List<Clip> result = clipDao.findAllOrderByDateRecorded();
		Collections.reverse(result);
		return result;
	}

	@Override
	public URI getVideoURI(Clip clip, String format) {
		try {
			return new URI("/video/" + clip.getName() + "." + format.toLowerCase());
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(MessageFormat.format("Possible invalid video name [{0}] or format {[1]}.", clip.getName(), format), e);
		}
	}

	@Override
	public Map<String, URI> getVideoUris(Clip clip) {
		Map<String, URI> videoUris = new HashMap<String, URI>(VIDEO_FORMATS.length);
		for (int i = 0; i < VIDEO_FORMATS.length; i++) {
			videoUris.put(VIDEO_FORMATS[i], getVideoURI(clip, VIDEO_FORMATS[i]));
		}
		return videoUris;
	}

	@Autowired
	public void setClipDao(ClipDao clipDao) {
		this.clipDao = clipDao;
	}

}

/**
 * Copyright (c) 2010 by Titus Kruse.
 */
package de.tikron.webapp.service.common;

import java.text.MessageFormat;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Stellt Konfigurationsparameter für das Tracking von Benutzeraktivitäten bereit.
 *
 * @author Titus Kruse
 * @since 09.08.2010
 */
@Service("trackingService")
public class TrackingServiceImpl implements TrackingService {

	private static Logger logger = LoggerFactory.getLogger(TrackingServiceImpl.class);

	private boolean enabled;

	@PostConstruct
	public void init() {
		logger.info(MessageFormat.format("Inizialized Tracking Service with tracking enabled [{0}].", isEnabled()));
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * Legt fest, ob das Tracking aktiviert ist.
	 * 
	 * @param enabled true, falls das Tracking aktiviert ist.
	 */
	@Value("${tikron.tracking.enabled:true}")
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}

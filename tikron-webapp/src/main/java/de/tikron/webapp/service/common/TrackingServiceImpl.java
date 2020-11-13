/**
 * Copyright (c) 2010 by Titus Kruse.
 */
package de.tikron.webapp.service.common;

import java.text.MessageFormat;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Stellt Konfigurationsparameter für das Tracking von Benutzeraktivitäten bereit.
 *
 * @date 09.08.2010
 * @author Titus Kruse
 */
@Service("trackingService")
public class TrackingServiceImpl implements TrackingService {

	private static Logger logger = LogManager.getLogger();

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
	@Value("${tracking.enabled:true}")
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}

/**
 * Copyright (c) 2010 by Titus Kruse.
 */
package de.tikron.webapp.service.common;

import org.springframework.stereotype.Service;

/**
 * Stellt Konfigurationsparameter für das Tracking von Benutzeraktivitäten bereit.
 *
 * @date 09.08.2010
 * @author Titus Kruse
 */
@Service("trackingService")
public class TrackingServiceImpl implements TrackingService {

	private boolean enabled;

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * Legt fest, ob das Tracking aktiviert ist.
	 * 
	 * @param enabled true, falls das Tracking aktiviert ist.
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}

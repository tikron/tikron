/**
 * Copyright (c) 2010 by Titus Kruse.
 */
package de.tikron.webapp.service.common;

/**
 * Stellt Konfigurationsparameter für das Tracking von Benutzeraktivitäten bereit.
 *
 * @date 09.08.2010
 * @author Titus Kruse
 */
public interface TrackingService {

	/**
	 * Gibt zurück, ob das Tracking aktiviert ist.
	 * 
	 * @return true, falls das Tracking aktiviert ist.
	 */
	public boolean isEnabled();

}

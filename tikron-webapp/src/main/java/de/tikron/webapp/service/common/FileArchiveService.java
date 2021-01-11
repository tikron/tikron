/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.service.common;

import java.net.URL;

/**
 * Stellt Methoden für den Zugang zum File Archive bereit.
 *
 * @since 17.09.2012
 * @author Titus Kruse
 */
public interface FileArchiveService {

	/**
	 * Liefert die URL zum File Archive.
	 * 
	 * @return Die URL.
	 */
	public URL getArchiveUrl();

	/**
	 * Liefert die URL zum File Archive. Abhängig vom Parameter secure wird das Protokoll http oder https angewendet.
	 * 
	 * @param secure Gibt an, ob die URL das protokoll https enthält.
	 * @return Die URL.
	 */
	public URL getArchiveUrl(boolean secure);

	/**
	 * Liefert die URL einer Datei im File Archive.
	 * 
	 * @param secure Gibt an, ob die URL das protokoll https enthält.
	 * @param filePath Gibt einen Pfadnamen der Datei an.
	 * @return Die URL zur Datei.
	 */
	public URL getFileUrl(boolean secure, String filePath);

}

/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.service.common;

import java.net.URI;
import java.net.URL;

/**
 * Stellt Methoden für den Zugang zum Image Server bereit (ibase).
 * 
 * @since 12.03.2012
 * @author Titus Kruse
 */
public interface ImageService {

	/**
	 * Liefert den URL zum Image Server.
	 * 
	 * @return Den URL.
	 */
	public URL getImageServerUrl();

	/**
	 * Liefert den URL zum Image Server. Abhängig vom Parameter secure wird das Protokoll HTTP oder HTTPS angewendet.
	 * 
	 * @param secure Gibt an, ob die URL das Protokoll HTTPS enthält.
	 * @return Den URL.
	 */
	public URL getImageServerUrl(boolean secure);

	/**
	 * Liefert den Pfadnamen für ein Bild auf dem Image Server.
	 * 
	 * @param section Der Bereich ("gallery", "cover"...).
	 * @param group Die Gruppe (z.B. die Kategorie).
	 * @param image Der einfache Name des Bildes.
	 * 
	 * @return Der Pfadname.
	 */
	public String getImagePathName(String section, String group, String image);

	/**
	 * Liefert den relativen URI zu einem Bild auf dem Image Server. Das Bild wird für das angegebene Template erzeugt.
	 * 
	 * @param section Der Bereich ("gallery", "cover"...).
	 * @param group Die Gruppe (z.B. die Kategorie).
	 * @param image Der einfache Name des Bildes.
	 * @param template Das Template, welches das Aussehen des Bildes beschreibt.
	 * 
	 * @return Den URI.
	 */
	public URI getImageUri(String section, String group, String image, String template);

	/**
	 * Liefert den relativen URI zu einem Bild auf dem Image Server. Das Bild wird für das angegebene Template erzeugt.
	 * Der URI ist eine für Suchmaschinen optimierte Version, die statt des Servlet-Namen des Images server direkt den
	 * qualifitierten Dateinamen des Bilder enthält.
	 * 
	 * @param section Der Bereich ("gallery", "cover"...).
	 * @param group Die Gruppe (z.B. die Kategorie).
	 * @param image Der einfache Name des Bildes.
	 * @param template Das Template, welches das Aussehen des Bildes beschreibt.
	 * 
	 * @return Den URI.
	 */
	public URI getSeoImageUri(String section, String group, String image, String template);

}

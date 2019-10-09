/**
 * Copyright (c) 2019 by Titus Kruse.
 */
package de.tikron.webapp.service.common;

import javax.servlet.http.HttpServletRequest;

/**
 * Geo-Location Service providing information about the site users earth location based on HTTP servlet request
 * 
 * Note: For simplification currently coutry only
 *
 * @date 29.03.2019
 * @author Titus Kruse
 */
public interface GeoLocationService {
	
	/**
	 * Returns country ISO code.
	 * 
	 * @param request The HTTP servlet request
	 * 
	 * @return The country ISO code or null, if unknown
	 */
	public String getCountryIsoCode(HttpServletRequest request);

}

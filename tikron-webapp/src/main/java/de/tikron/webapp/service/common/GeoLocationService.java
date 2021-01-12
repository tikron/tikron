/**
 * Copyright (c) 2019 by Titus Kruse.
 */
package de.tikron.webapp.service.common;

import java.net.InetAddress;

/**
 * Geo-Location Service providing information about the site users earth location based on remote IP address.
 * 
 * Note: For simplification currently country only
 *
 * @author Titus Kruse
 * @since 29.03.2019
 */
public interface GeoLocationService {
	
	/**
	 * Tries to identify users home country ISO code based on the given IP address (ipv4 or ipv6).
	 * 
	 * @param ipAddress The IP address.
	 * 
	 * @return The country ISO code or null, if unknown
	 * 
	 * @throws GeoLocationServiceException Thrown on error in Geo IP lookup. 
	 */
	public String getCountryIsoCode(InetAddress ipAddress) throws GeoLocationServiceException;
	
	/**
	 * Tries to identify users home country ISO code based on the given IP address (ipv4 or ipv6).
	 * 
	 * @param ipAddress The IP address as string.
	 * 
	 * @return The country ISO code or null, if unknown
	 * 
	 * @throws GeoLocationServiceException Thrown on error in Geo IP lookup. 
	 */
	public String getCountryIsoCode(String ipAddress) throws GeoLocationServiceException;

}

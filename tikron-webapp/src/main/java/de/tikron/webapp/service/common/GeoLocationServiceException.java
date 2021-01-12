/**
	* Copyright (c) 2021 by Titus Kruse.
	*/

package de.tikron.webapp.service.common;

/**
	* Exception wrapper for Geo IP lookup service.
	*
	* @author Titus Kruse
	* @since Jan 12, 2021
	*/

public class GeoLocationServiceException extends Exception {

	private static final long serialVersionUID = 2411836981453461390L;

	public GeoLocationServiceException() {
	}

	public GeoLocationServiceException(String message) {
		super(message);
	}

	public GeoLocationServiceException(Throwable cause) {
		super(cause);
	}
	public GeoLocationServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}

/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.controller.common;


/**
 * Exception indicating a resource was not found. When requesting a not existing database record for example, this
 * exception results into a response with HTTP status 404.  
 *
 * @date 12.01.2015
 * @author Titus Kruse
 */
// @ResponseStatus(HttpStatus.NOT_FOUND)
// HTTP Response code in exception will not be forwarded by exceptions handler annotated with ControllerAdvice.   
public class ResourceNotFoundException extends ApplicationException {

	public ResourceNotFoundException(String msgKey, Object[] msgParam, String resourceBundle) {
		super(msgKey, msgParam, resourceBundle);
	}

	public ResourceNotFoundException(String msgKey, Object[] msgParam, Throwable cause) {
		super(msgKey, msgParam, cause);
	}

	public ResourceNotFoundException(String msgKey, Object[] msgParam) {
		super(msgKey, msgParam);
	}

	public ResourceNotFoundException(String msgKey, Throwable cause) {
		super(msgKey, cause);
	}

	public ResourceNotFoundException(String msgKey) {
		super(msgKey);
	}

}

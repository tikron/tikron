/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.controller.common;

/**
 * An AjaxResponse for the sucess case holding data to update the view.
 *
 * @since 17.05.2015
 * @author Titus Kruse
 */
public class SuccessResponse implements AjaxResponse {
	
	private String message;
	
	private Object data;

	public SuccessResponse() {
	}

	public SuccessResponse(String message) {
		this.message = message;
	}

	public SuccessResponse(Object data) {
		this.data = data;
	}

	public SuccessResponse(String message, Object data) {
		this.message = message;
		this.data = data;
	}

	@Override
	public String getStatus() {
		return SUCCESS;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

}

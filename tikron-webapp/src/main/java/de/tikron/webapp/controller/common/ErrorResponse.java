/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.controller.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.NoSuchMessageException;
import org.springframework.validation.BindingResult;

import de.tikron.webapp.util.LocalizationContext;

/**
 * An AjaxResponse for the error case holding global and field errors.
 *
 * @author Titus Kruse
 * @since 09.05.2015
 */
public class ErrorResponse implements AjaxResponse {
	
	private List<FieldError> fieldErrors = Collections.emptyList();
	
	private List<ObjectError> globalErrors = Collections.emptyList();
	
	public ErrorResponse(BindingResult result, LocalizationContext localizationContext) {
		this.fieldErrors = new ArrayList<FieldError>(result.getFieldErrors().size());
		for (org.springframework.validation.FieldError error : result.getFieldErrors()) {
			this.fieldErrors.add(new FieldError(error, localizationContext));
		}
		this.globalErrors = new ArrayList<ObjectError>(result.getGlobalErrors().size());
		for (org.springframework.validation.ObjectError error : result.getGlobalErrors()) {
			this.globalErrors.add(new ObjectError(error, localizationContext));
		}
	}
	
	public ErrorResponse(List<org.springframework.validation.ObjectError> globalErrors, LocalizationContext localizationContext) {
		this.globalErrors = new ArrayList<ObjectError>(globalErrors.size());
		for (org.springframework.validation.ObjectError error : globalErrors) {
			this.globalErrors.add(new ObjectError(error, localizationContext));
		}
	}
	
	public ErrorResponse(org.springframework.validation.ObjectError globalError, LocalizationContext localizationContext) {
		this.globalErrors = new ArrayList<ObjectError>(1);
		this.globalErrors.add(new ObjectError(globalError, localizationContext));
	}
	
	public ErrorResponse(String code, Object[] arguments, LocalizationContext localizationContext) {
		this.globalErrors = new ArrayList<ObjectError>(1);
		this.globalErrors.add(new ObjectError(localizationContext.getMessage(code, arguments)));
	}
	
	public ErrorResponse(String code, LocalizationContext localizationContext) {
		this(code, new Object[]{}, localizationContext);
	}

	public String getStatus() {
		return ERROR;
	}

	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}
	
	public List<ObjectError> getGlobalErrors() {
		return globalErrors;
	}

	public static class ObjectError {
		
		private final String message;
		
		public ObjectError(org.springframework.validation.ObjectError error, LocalizationContext localizationContext) {
			this(resolveMessage(error, localizationContext));
		}

		public ObjectError(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}
	}
	
	public static class FieldError extends ObjectError {
		
		private final String field;
		
		public FieldError(org.springframework.validation.FieldError error, LocalizationContext localizationContext) {
			this(error.getField(), resolveMessage(error, localizationContext));
		}

		public FieldError(String field, String message) {
			super(message);
			this.field = field;
		}

		public String getField() {
			return field;
		}
	}
	
	private static String resolveMessage(org.springframework.validation.ObjectError error, LocalizationContext localizationContext) {
		try {
			return localizationContext.getMessage(error.getCode(), error.getArguments());
		} catch (NoSuchMessageException e) {
			return error.getDefaultMessage();
		}
	}

}

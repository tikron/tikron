/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.controller.common;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Base excpetion class for all runtime exceptions of this application. To provide a message in localized form, the JVM
 * default locale will be used.
 *
 * @date 21.01.2015
 * @author Titus Kruse
 */
public class ApplicationException extends RuntimeException {
	
	private static final long serialVersionUID = -8517510245636325957L;

	private static final String DEFAULT_BUNDLE = "de/tikron/webapp/mvc/exceptions";

	private final ResourceBundle resourceBundle;
	
	private final String msgKey;
	
	private final Object[] msgParam;

	public ApplicationException(String msgKey) {
		this(msgKey, new Object[]{});
	}

	public ApplicationException(String msgKey, Object[] msgParam) {
		this(msgKey, msgParam, DEFAULT_BUNDLE);
	}

	public ApplicationException(String msgKey, Object[] msgParam, String resourceBundle) {
		super("Application exception occurred: " + msgKey);
		this.msgKey = msgKey;
		this.msgParam = msgParam;
		this.resourceBundle = ResourceBundle.getBundle(resourceBundle);
	}

	public ApplicationException(String msgKey, Throwable cause) {
		this(msgKey, new Object[]{}, cause);
	}

	public ApplicationException(String msgKey, Object[] msgParam, Throwable cause) {
		super("Application exception occurred: " + msgKey, cause);
		this.msgKey = msgKey;
		this.msgParam = msgParam;
		this.resourceBundle = ResourceBundle.getBundle(DEFAULT_BUNDLE);
	}

	public String getKey() {
		return msgKey;
	}

	public Object[] getParam() {
		return msgParam;
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	@Override
	public String getLocalizedMessage() {
		return MessageFormat.format(resourceBundle.getString(this.msgKey), this.msgParam);
	}

	@Override
	public String toString() {
		return "Application exception: " + msgKey;
	}

}

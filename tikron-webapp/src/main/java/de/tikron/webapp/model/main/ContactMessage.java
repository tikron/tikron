/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.model.main;

import java.io.Serializable;

/**
 * Eine Kontaktnachricht.
 *
 * @author Titus Kruse
 * @since 30.12.2012
 */
public class ContactMessage implements Serializable {

	private static final long serialVersionUID = -5248668669736528839L;

	public static final String NAME = "contactMessage";

	private String name;

	private String email;

	private String message;

	public ContactMessage(String name, String email, String message) {
		this.name = name;
		this.email = email;
		this.message = message;
	}

	public ContactMessage() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

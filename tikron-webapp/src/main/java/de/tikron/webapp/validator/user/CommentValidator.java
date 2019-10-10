/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.validator.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import de.tikron.persistence.model.user.Comment;
import de.tikron.webapp.model.user.CommentForm;
import de.tikru.commons.spring.ValidationUtils;

/**
 * Validator für Kommentare. (Sollte später durch JSR303 Bean-Validation ersetzt werden.)
 *
 * @date 30.12.2012
 * @author Titus Kruse
 */
public abstract class CommentValidator implements Validator {

	private static final int AUTHOR_MAXLENGTH = 100;
	private static final int EMAIL_MAXLENGTH = 75;
	private static final int URL_MAXLENGTH = 255;

	private String[] spamPhrases = new String[] {};

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return Comment.class.isAssignableFrom(clazz);
	}

	public void validate(Object command, Errors errors) {
		CommentForm comment = (CommentForm) command;

		// Validate required fields
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "comment.error.author.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "comment.error.text.required");

		if (errors.hasErrors())
			return;

		// Validate author
		ValidationUtils.rejectIfTooLong(errors, "author", AUTHOR_MAXLENGTH, "comment.error.author.length");
		ValidationUtils.rejectIfContainsString(errors, "author", "error.contains.spam", this.spamPhrases);

		// Validate e-mail address
		if (!StringUtils.isEmpty(comment.getEmail())) {
			ValidationUtils.rejectIfTooLong(errors, "email", EMAIL_MAXLENGTH, "comment.error.email.length");
			ValidationUtils.rejectIfInvalidEmail(errors, "email", "comment.error.email.format");
		}

		// Validate URL
		if (!StringUtils.isEmpty(comment.getUrl())) {
			ValidationUtils.rejectIfTooLong(errors, "url", URL_MAXLENGTH, "comment.error.url.length");
			ValidationUtils.rejectIfInvalidURL(errors, "url", "comment.error.url.format");
		}

		// Validate text
		ValidationUtils.rejectIfContainsString(errors, "text", "error.contains.spam", this.spamPhrases);

		// Fend of may 2014 spam attack
		if (comment.getText().matches("Hello!\\s*")) {
			errors.reject("error.invalid.field");
		}

	}

	public void setSpamPhrases(String spamPhrases) {
		this.spamPhrases = StringUtils.split(spamPhrases, ",");
	}

}

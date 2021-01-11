/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.validator.main;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import de.tikron.webapp.model.main.ContactMessage;
import de.tikron.webapp.service.common.CaptchaService;
import de.tikru.commons.spring.ValidationUtils;

/**
 * Validator für Kontaktnachrichten. (Sollte später durch JSR303 Bean-Validation ersetzt werden.)
 *
 * @since 30.12.2012
 * @author Titus Kruse
 */
public class ContactMessageValidator implements Validator {

	private static final int NAME_MAXLENGTH = 50;
	private static final int EMAIL_MAXLENGTH = 255;
	private static final int MESSAGE_MAXLENGTH = 1000;

	private CaptchaService captchaService;

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return ContactMessage.class.isAssignableFrom(clazz);
	}

	public void validate(Object command, Errors errors) {
		// Validate required fields
		// TODO Konstanten für Feldnamen extrahieren
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "contactMessage.error.name.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "contactMessage.error.email.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "contactMessage.error.message.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "captchaCode", "error.captchaCode.required");

		if (errors.hasErrors())
			return;

		// Validate name
		ValidationUtils.rejectIfTooLong(errors, "name", NAME_MAXLENGTH, "contactMessage.error.name.length");

		// Validate e-mail-address
		ValidationUtils.rejectIfTooLong(errors, "email", EMAIL_MAXLENGTH, "contactMessage.error.email.length");
		ValidationUtils.rejectIfInvalidEmail(errors, "email", "contactMessage.error.email.invalid");

		// Validate message
		ValidationUtils.rejectIfTooLong(errors, "message", MESSAGE_MAXLENGTH, "contactMessage.error.message.length");

		// Validate Captcha Code
		String captchaCode = ((ContactMessage) command).getCaptchaCode();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String captchaId = request.getSession().getId();
		if (!captchaService.validateCaptchaCode(captchaId, captchaCode)) {
			errors.rejectValue("captchaCode", "error.captchaCode.invalid");
		}
	}

	@Autowired
	public void setCaptchaService(CaptchaService captchaService) {
		this.captchaService = captchaService;
	}

}

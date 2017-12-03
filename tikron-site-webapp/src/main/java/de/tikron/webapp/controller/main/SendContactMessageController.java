/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.controller.main;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.tikron.common.spring.EmailService;
import de.tikron.webapp.controller.common.AbstractFormController;
import de.tikron.webapp.controller.common.AjaxResponse;
import de.tikron.webapp.controller.common.ErrorResponse;
import de.tikron.webapp.controller.common.SuccessResponse;
import de.tikron.webapp.controller.common.ViewConstants;
import de.tikron.webapp.model.main.ContactMessage;
import de.tikron.webapp.util.RobotsDirective;

/**
 * Controller for caontact message page.
 *
 * @date 30.12.2012
 * @author Titus Kruse
 */
@Controller
public class SendContactMessageController extends AbstractFormController {

	private static Logger logger = LogManager.getLogger();

	private EmailService emailService;

	private Validator validator;

	/**
	 * Prepare page.
	 * 
	 * @param model ModelMap to pass model objects to frontend.
	 * @return The view name to show.
	 */
	@RequestMapping(value = "/sendContactMessage.html", method = RequestMethod.GET)
	public String setupForm(ModelMap model) {
		model.addAttribute(ContactMessage.NAME, new ContactMessage());
		return ViewConstants.SEND_CONTACT_MESSAGE;
	}

	/**
	 * Process comment form.
	 * 
	 * @param commentForm The comment to add.
	 * @param result Spring form result.
	 * 
	 * @return The form response. On success the response data contains the new comment. On error the reponse data contains the Spring errors. 
	 */
	@RequestMapping(value = "/sendContactMessage.json", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse processSubmit(@ModelAttribute(ContactMessage.NAME) ContactMessage contactMessage, BindingResult result) {
		validator.validate(contactMessage, result);
		if (!result.hasErrors()) {
			String emailSubject = getMessage("sendContactMessage.email.subject");
			if (!emailService.sendEmail(contactMessage.getEmail(), contactMessage.getName(), null, emailSubject,
					contactMessage.getMessage())) {
				logger.warn("Error occured sending contact message.");
				return new ErrorResponse("sendContactMessage.confirmation.error", localizationContext);
			}
			return new SuccessResponse(localizationContext.getMessage("sendContactMessage.confirmation.success", new Object[]{}));
		} else {
			return new ErrorResponse(result, getLocalizationContext());
		}
	}

	@Override
	public String getRobotsDirective() {
		return RobotsDirective.NOINDEX.toString();
	}

	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	@Resource(name = "contactMessageValidator")
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

}

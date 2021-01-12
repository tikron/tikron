/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.controller.main;

import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.tikron.webapp.controller.common.AbstractFormController;
import de.tikron.webapp.controller.common.AjaxResponse;
import de.tikron.webapp.controller.common.ErrorResponse;
import de.tikron.webapp.controller.common.SuccessResponse;
import de.tikron.webapp.controller.common.ViewConstants;
import de.tikron.webapp.model.main.ContactMessage;
import de.tikron.webapp.service.common.GeoLocationService;
import de.tikron.webapp.service.common.GeoLocationServiceException;
import de.tikron.webapp.util.RobotsDirective;
import de.tikru.commons.spring.MailService;

/**
 * Controller for the contact message page.
 *
 * @author Titus Kruse
 * @since 30.12.2012
 */
@Controller
public class SendContactMessageController extends AbstractFormController {

	private static Logger logger = LoggerFactory.getLogger(SendContactMessageController.class);
	
	// TODO Allowed countries shall be configurable
	private static final Set<String> allowedCountries = Set.of("DE", "AT", "CH");

	private MailService mailService;
	
	private GeoLocationService geoLocationService;

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
	 * Process form submit.
	 * 
	 * @param contactMessage The contact message to add.
	 * @param result Spring form result.
	 * 
	 * @return The Spring form response. {@link de.tikron.webapp.controller.common.SuccessResponse} on success, {@link de.tikron.webapp.controller.common.ErrorResponse} otherwise.  
	 */
	@RequestMapping(value = "/sendContactMessage.json", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse processSubmit(@ModelAttribute(ContactMessage.NAME) ContactMessage contactMessage, BindingResult result) {
		String remoteAddr = getHttpServletRequest().getRemoteAddr();
		String countryIsoCode = retrieveGeoLocation(remoteAddr);
		if (countryIsoCode != null && !allowedCountries.contains(countryIsoCode)) {
			logger.warn("Contact message submitted by user from unauthorized country {}.", countryIsoCode);
			return new ErrorResponse("sendContactMessage.error.invalidCountry", localizationContext);
		}
		validator.validate(contactMessage, result);
		if (!result.hasErrors()) {
			String emailSubject = getMessage("sendContactMessage.email.subject");
			// TODO Should be send async to avoid user waiting for SMTP communication
			if (!mailService.send(contactMessage.getEmail(), contactMessage.getName(), null, emailSubject,
					contactMessage.getMessage())) {
				logger.warn("Error occured sending contact message.");
				return new ErrorResponse("sendContactMessage.confirmation.error", localizationContext);
			}
			return new SuccessResponse(localizationContext.getMessage("sendContactMessage.confirmation.success", new Object[]{}));
		} else {
			return new ErrorResponse(result, getLocalizationContext());
		}
	}
	
	private String retrieveGeoLocation(String remoteAddress) {
		try {
			String countryIsoCode = geoLocationService.getCountryIsoCode(remoteAddress);
			if (countryIsoCode == null) {
				logger.warn("No country code found for address {} by Geo IP lookup.", remoteAddress);
			}
			return countryIsoCode;
		} catch (GeoLocationServiceException e) {
			logger.error("Exception occurred in Geo IP lookup: ", e);
			return null;
		}
	}

	@Override
	public String getRobotsDirective() {
		return RobotsDirective.NOINDEX.toString();
	}

	@Autowired
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	@Autowired
	public void setGeoLocationService(GeoLocationService geoLocationService) {
		this.geoLocationService = geoLocationService;
	}

	@Resource(name = "contactMessageValidator")
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

}

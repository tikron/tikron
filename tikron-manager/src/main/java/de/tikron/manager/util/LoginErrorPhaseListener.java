/**
 * Copyright (c) 2010 by Titus Kruse.
 */
package de.tikron.manager.util;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;

import de.tikru.commons.faces.util.Message;

/**
 * Listener converting Spring BadCredentialsException into faces message shown in the frontend.
 * 
 * @date 29.11.2010
 * @author Titus Kruse
 * 
 * @see http://slackspace.de/articles/custom-login-page-with-jsf-and-spring-security-3-2/
 */
public class LoginErrorPhaseListener implements PhaseListener {

	private static final long serialVersionUID = -1216620620302322995L;

	@Override
	public void beforePhase(final PhaseEvent arg0) {
		Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(WebAttributes.AUTHENTICATION_EXCEPTION);

		if (e instanceof BadCredentialsException) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
			Message.sendMessage(null, "de.tikron.manager.ERROR_BAD_CREDENTIALS", null);
		}
	}

	@Override
	public void afterPhase(final PhaseEvent arg0) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}

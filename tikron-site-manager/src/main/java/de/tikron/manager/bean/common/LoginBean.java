/**
 * Copyright (c) 2010 by Titus Kruse.
 */
package de.tikron.manager.bean.common;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Managed bean delegating user authentication to the Spring security check.
 * 
 * @date 29.11.2010
 * @author Titus Kruse
 * 
 * @see http://slackspace.de/articles/custom-login-page-with-jsf-and-spring-security-3-2/
 * 
 *      Other incomplete tutorials:
 * 
 * @see http://mprabhat.wordpress.com/2012/07/15/implementing-spring-security-remember-me-with-jsf-2-0/
 * @see http://laabidi-raissi.blogspot.de/2013/07/jsf-2-spring-security-integration-with.html
 */
@ManagedBean
@RequestScoped
public class LoginBean {

	/**
	 * 
	 * Redirects the login request directly to spring security check. Leave this method as it is to properly support
	 * spring security.
	 * 
	 * @return Faces Outcome.
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	public String doLogin() throws ServletException, IOException {
		ExternalContext facesContext = FacesContext.getCurrentInstance().getExternalContext();

		RequestDispatcher dispatcher = ((ServletRequest) facesContext.getRequest())
				.getRequestDispatcher("/j_spring_security_check");

		dispatcher.forward((ServletRequest) facesContext.getRequest(), (ServletResponse) facesContext.getResponse());

		FacesContext.getCurrentInstance().responseComplete();

		return null;
	}

}

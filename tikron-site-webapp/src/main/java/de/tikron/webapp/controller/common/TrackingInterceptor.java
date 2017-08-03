/**
 * Copyright (c) 2013 by Titus Kruse.
 */
package de.tikron.webapp.controller.common;

import java.text.MessageFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import de.tikron.webapp.service.common.TrackingService;

/**
 * Interceptor checking for enabled user tracking.
 *
 * @date 17.01.2013
 * @author Titus Kruse
 */
@Component
public class TrackingInterceptor extends HandlerInterceptorAdapter {

	private static Logger LOGGER = LogManager.getLogger();

	private static final String REQUEST_PARAM_TRACKING = "tracking";

	private static final String COOKIE_NAME = "tracking";

	private static final int COOKIE_LIFETIME = 86400;

	private TrackingService trackingService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		if (modelAndView != null) {
			ModelMap modelMap = modelAndView.getModelMap();
			// Check configuration
			boolean trackingEnabled = trackingService.isEnabled();
			// Check request parameter and create cookie
			String requestParameter = request.getParameter(REQUEST_PARAM_TRACKING);
			if (requestParameter != null) {
				boolean requestTrackingEnabled = Boolean.parseBoolean(requestParameter);
				LOGGER.info(MessageFormat.format("Tracking enabled {0} for session {1}.", requestTrackingEnabled, request
						.getSession().getId()));
				Cookie cookie = new Cookie(COOKIE_NAME, Boolean.toString(requestTrackingEnabled));
				cookie.setPath("/");
				cookie.setMaxAge(COOKIE_LIFETIME);
				response.addCookie(cookie);
				// Override configured tracking state by request param
				trackingEnabled = requestTrackingEnabled;
			} else {
				// Check cookie and propably override tracking state
				Cookie cookies[] = request.getCookies();
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals(COOKIE_NAME)) {
							trackingEnabled = Boolean.valueOf(cookie.getValue());
						}
					}
				}
			}
			modelMap.addAttribute("trackingEnabled", Boolean.valueOf(trackingEnabled));
		}
	}

	@Autowired
	public void setTrackingService(TrackingService trackingService) {
		this.trackingService = trackingService;
	}

}

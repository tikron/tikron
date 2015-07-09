package de.tikron.webapp.util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.log4j.Logger;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;

/**
 * Servlet filter that checks all request parameters for potential XSS attacks
 * 
 * @see http://bazageous.com/2011/04/14/preventing-xss-attacks-with-antisamy/
 * @see https://github.com/barrypitman/antisamy-servlet-filter
 *
 * @author barry pitman
 * @since 2011/04/12 5:13 PM
 */
public class AntiSamyFilter implements Filter {

	private static final Logger LOG = Logger.getLogger(AntiSamyFilter.class);

	/**
	 * AntiSamy is unfortunately not immutable, but is threadsafe if we only call
	 * {@link AntiSamy#scan(String taintedHTML, int scanType)}
	 */
	private final AntiSamy antiSamy;

	public AntiSamyFilter() {
		try {
			// Find antisamy configuration files here:
			// https://code.google.com/p/owaspantisamy/downloads/list
			URL url = this.getClass().getClassLoader().getResource("antisamy-slashdot-1.4.4.xml");
			Policy policy = Policy.getInstance(url);
			antiSamy = new AntiSamy(policy);
		} catch (PolicyException e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		if (request instanceof HttpServletRequest) {
			CleanServletRequest cleanRequest = new CleanServletRequest((HttpServletRequest) request, antiSamy);
			chain.doFilter(cleanRequest, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	/**
	 * Wrapper for a {@link HttpServletRequest} that returns 'safe' parameter values by passing the raw request parameters
	 * through the anti-samy filter. Should be private
	 */
	private static class CleanServletRequest extends HttpServletRequestWrapper {

		private final AntiSamy antiSamy;

		private CleanServletRequest(HttpServletRequest request, AntiSamy antiSamy) {
			super(request);
			this.antiSamy = antiSamy;
		}

		/**
		 * overriding getParameter functions in {@link ServletRequestWrapper}
		 */
		@Override
		public String[] getParameterValues(String name) {
			String[] originalValues = super.getParameterValues(name);
			if (originalValues == null) {
				return null;
			}
			List<String> newValues = new ArrayList<String>(originalValues.length);
			for (String value : originalValues) {
				newValues.add(filterString(value));
			}
			return newValues.toArray(new String[newValues.size()]);
		}

		@Override
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Map getParameterMap() {
			Map<String, String[]> originalMap = super.getParameterMap();
			Map<String, String[]> filteredMap = new ConcurrentHashMap<String, String[]>(originalMap.size());
			for (String name : originalMap.keySet()) {
				filteredMap.put(name, getParameterValues(name));
			}
			return Collections.unmodifiableMap(filteredMap);
		}

		@Override
		public String getParameter(String name) {
			String potentiallyDirtyParameter = super.getParameter(name);
			return filterString(potentiallyDirtyParameter);
		}

		/**
		 * @param potentiallyDirtyParameter string to be cleaned
		 * @return a clean version of the same string
		 */
		private String filterString(String potentiallyDirtyParameter) {
			if (potentiallyDirtyParameter == null || "".equals(potentiallyDirtyParameter)) {
				return potentiallyDirtyParameter;
			}
			// Escape CR, LF to bypass serializer in org.owasp.validator.html.scan.AntiSamyDOMScanner:175. 
			String excapedPotentiallyDirtyParameter = potentiallyDirtyParameter.replaceAll("\\n", "%0A").replaceAll("\\r", "%0D");
			try {
				CleanResults cr = antiSamy.scan(excapedPotentiallyDirtyParameter, AntiSamy.DOM);
				if (cr.getNumberOfErrors() > 0) {
					LOG.warn("antisamy encountered problem with input: " + cr.getErrorMessages());
				}
				return cr.getCleanHTML().replaceAll("%0A", "\n").replaceAll("%0D", "\r");
			} catch (Exception e) {
				throw new IllegalStateException(e.getMessage(), e);
			}
		}
	}
}
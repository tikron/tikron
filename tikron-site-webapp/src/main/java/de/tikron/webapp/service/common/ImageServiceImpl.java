/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.service.common;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Stellt Methoden für den Zugang zum Image Server bereit (ibase).
 *
 * @date 12.03.2012
 * @author Titus Kruse
 */
@Service("imageService")
public class ImageServiceImpl implements ImageService {

	private static Logger LOGGER = LogManager.getLogger();

	private static final String IMAGE_SERVLET_PATH = "/ibase/getImage.jsp";

	private URL imageServerUrl;
	
	@PostConstruct
	public void init() {
		LOGGER.info(MessageFormat.format("Image service initialized with server URL [{0}].", this.imageServerUrl));
	}

	@Override
	public URL getImageServerUrl() {
		return imageServerUrl;
	}

	@Override
	public URL getImageServerUrl(boolean secure) {
		try {
			return new URL(secure ? "https" : "http", imageServerUrl.getHost(), imageServerUrl.getPort(),
					imageServerUrl.getPath());
		} catch (MalformedURLException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public String getImagePathName(String section, String group, String image) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
		if (!StringUtils.isEmpty(section))
			uriBuilder.pathSegment(section);
		if (!StringUtils.isEmpty(group))
			uriBuilder.pathSegment(group);
		if (StringUtils.isEmpty(image))
			throw new IllegalArgumentException("Image name is empty");
		uriBuilder.pathSegment(image);
		return uriBuilder.build().toUriString();
	}

	@Override
	public URI getImageUri(String section, String group, String image, String template) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(IMAGE_SERVLET_PATH);
		String imagePathName = getImagePathName(section, group, image);
		try {
			uriBuilder.queryParam("name", URLEncoder.encode(imagePathName, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		uriBuilder.queryParam("template", template);
		return uriBuilder.build().toUri();
	}

	@Override
	public URI getSeoImageUri(String section, String group, String image, String template) {
		String imagePathName = getImagePathName(section, group, image);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(imagePathName);
		uriBuilder.queryParam("template", template);
		return uriBuilder.build().toUri();
	}

	@Required
	public void setImageServerUrl(URL url) {
		// Remove possible configured path from URL to simplify concatenation of server URL und file path on JSPs.
		try {
			this.imageServerUrl = new URL(url.getProtocol(), url.getHost(), url.getPort(), "");
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("Invalid image server URL: " + url.toString(), e);
		}
	}

}

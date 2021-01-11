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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Stellt Methoden für den Zugang zum Image Server bereit (ibase).
 *
 * @author Titus Kruse
 * @since 12.03.2012
 */
@Service("imageService")
public class ImageServiceImpl implements ImageService {

	private static Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

	private static final String IMAGE_SERVLET_PATH = "/ibase/getImage.jsp";
	
	private String host;

	@PostConstruct
	public void init() {
		if (getHost() == null) {
			throw new IllegalStateException("Property host must not be null.");
		}
		logger.info(MessageFormat.format("Initialized Image Service with host [{0}].", getHost()));
	}

	public String getHost() {
		return host;
	}

	@Value("${tikron.images.host}")
	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public URL getImageServerUrl() {
		return getImageServerUrl(true);
	}

	@Override
	public URL getImageServerUrl(boolean secure) {
		try {
			return new URL(secure ? "https" : "http", getHost(), "");
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
}

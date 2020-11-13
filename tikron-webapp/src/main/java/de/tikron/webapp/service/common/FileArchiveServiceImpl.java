/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.service.common;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Stellt Methoden für den Zugang zum File Archive bereit.
 *
 * @date 17.09.2012
 * @author Titus Kruse
 */
@Service("fileArchiveService")
public class FileArchiveServiceImpl implements FileArchiveService {

	private static Logger logger = LogManager.getLogger();
	
	private String host;

	@PostConstruct
	public void init() {
		if (getHost() == null) {
			throw new IllegalStateException("Property host must not be null.");
		}
		logger.info(MessageFormat.format("Initialized File Archive Service with host [{0}].", getHost()));
	}

	public String getHost() {
		return host;
	}

	@Value("${tikron.files.host}")
	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public URL getArchiveUrl() {
		return getArchiveUrl(true);
	}

	@Override
	public URL getArchiveUrl(boolean secure) {
		try {
			return new URL(secure ? "https" : "http", getHost(), "");
		} catch (MalformedURLException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public URL getFileUrl(boolean secure, String filePath) {
		try {
			return new URL(secure ? "https" : "http", getHost(), filePath);
		} catch (MalformedURLException e) {
			throw new IllegalStateException(e);
		}
	}
}

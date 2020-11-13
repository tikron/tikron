/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.service.common;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private final URL archiveUrl;
	
	public FileArchiveServiceImpl(String host) {
		Objects.requireNonNull(host, "Constructor argument host must not be null");
		try {
			this.archiveUrl = new URL("https", host, "");
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("Could not create URL from file archive host: " + host, e);
		}
	}

	@PostConstruct
	public void init() {
		logger.info(MessageFormat.format("File archive service initialized with URL [{0}].", this.archiveUrl));
	}

	@Override
	public URL getArchiveUrl() {
		return archiveUrl;
	}

	@Override
	public URL getArchiveUrl(boolean secure) {
		try {
			return new URL(secure ? "https" : "http", archiveUrl.getHost(), archiveUrl.getPort(), archiveUrl.getPath());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return archiveUrl;
		}
	}

	@Override
	public URL getFileUrl(boolean secure, String filePath) {
		try {
			return new URL(archiveUrl.getProtocol(), archiveUrl.getHost(), archiveUrl.getPort(), filePath);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return archiveUrl;
		}
	}
}

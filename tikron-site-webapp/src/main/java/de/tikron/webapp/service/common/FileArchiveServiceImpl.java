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
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

/**
 * Stellt Methoden für den Zugang zum File Archive bereit.
 *
 * @date 17.09.2012
 * @author Titus Kruse
 */
@Service("fileArchiveService")
public class FileArchiveServiceImpl implements FileArchiveService {

	private static Logger LOGGER = LogManager.getLogger(FileArchiveServiceImpl.class);

	private URL archiveUrl;
	
	@PostConstruct
	public void init() {
		LOGGER.info(MessageFormat.format("File archive service initialized with URL [{0}].", this.archiveUrl));
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

	@Required
	public void setArchiveUrl(URL url) {
		// Remove possible configured path from URL to simplify concatenation of server URL und file path on JSPs.
		try {
			this.archiveUrl = new URL(url.getProtocol(), url.getHost(), url.getPort(), "");
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("Invalid file archive URL: " + url.toString(), e);
		}
	}

}

/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.service.common;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import de.tikron.ibase.client.IBaseClient;
import de.tikron.ibase.client.IBaseClientException;

/**
 * Implementierung des ImageService auf Basis von iBase.
 *
 * @author Titus Kruse
 * @since 20.02.2012
 */
@Service("imageService")
public class ImageServiceIBaseImpl implements ImageService {
	
	private String host;

	private String user;

	private String password;

	private IBaseClient iBaseClient;
	
	private String serverPath;

	@PostConstruct
	public void init() {
		if (getHost() == null) {
			throw new IllegalStateException("Property host must not be null.");
		}
		// Init iBase client to access image archive
		iBaseClient = new IBaseClient(getHost());
		if (getUser() != null) {
			iBaseClient.setUserName(getUser());
		}
		if (getPassword() != null) {
			iBaseClient.setPassword(getPassword());
		}
		// Build server path for access in view layer
		try {
			serverPath = new URL("https", iBaseClient.getHost(), iBaseClient.getPort(), iBaseClient.getContextPath()).toString();
		} catch (MalformedURLException e) {
			throw new IllegalStateException("Could not create server URL.", e);
		}
	}

	@PreDestroy
	public void destroy() {
		iBaseClient.close();
	}

	@Override
	public String getServerPath() {
		return serverPath;
	}

	@Override
	public boolean saveAlbumCover(String name, byte[] image) {
		return addImage(Constants.ALBUM_IMAGE_PATH + name, image);
	}

	@Override
	public boolean deleteAlbumCover(String name) {
		return removeImage(Constants.ALBUM_IMAGE_PATH + name);
	}

	@Override
	public boolean saveGalleryImage(String name, String category, byte[] image) {
		return addImage(Constants.GALLERY_IMAGE_PATH + category + "/" + name, image);
	}

	@Override
	public boolean deleteGalleryImage(String name, String category) {
		return removeImage(Constants.GALLERY_IMAGE_PATH + category + "/" + name);
	}

	@Override
	public boolean moveGalleryImage(String name, String sourceCategory, String targetCategory) {
		return renameImage(Constants.GALLERY_IMAGE_PATH + sourceCategory + "/" + name, Constants.GALLERY_IMAGE_PATH
				+ targetCategory + "/" + name);
	}

	/**
	 * Adds (uploads) an image to the image server.
	 * 
	 * @param name The image name.
	 * @param image The image.
	 * @return true, if the operation was successful.
	 */
	private boolean addImage(String name, byte[] image) {
		boolean success = false;
		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new ByteArrayInputStream(image));
			success = iBaseClient.putSourceImage(name, inputStream);
		} catch (IBaseClientException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
		return success;
	}

	/**
	 * Removes an image from the image server.
	 * 
	 * @param name The image name.
	 * @return true, if the operation was successful.
	 */
	private boolean removeImage(String name) {
		boolean success = false;
		try {
			success = iBaseClient.removeSourceImage(name);
		} catch (IBaseClientException e) {
			e.printStackTrace();
		}
		return success;
	}

	/**
	 * Renames an image on the image server.
	 * 
	 * @param name The old name.
	 * @param newName The new name.
	 * @return true, if the operation was successful.
	 */
	private boolean renameImage(String name, String newName) {
		boolean success = false;
		try {
			success = iBaseClient.renameSourceImage(name, newName);
		} catch (IBaseClientException e) {
			e.printStackTrace();
		}
		return success;
	}

	public String getHost() {
		return host;
	}

	@Value("${tikron.image-server.host}")
	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	@Value("${tikron.image-server.user}")
	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	@Value("${tikron.image-server.password}")
	public void setPassword(String password) {
		this.password = password;
	}
}

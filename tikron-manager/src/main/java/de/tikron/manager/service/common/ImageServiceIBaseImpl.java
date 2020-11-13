/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.service.common;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import de.tikron.ibase.client.IBaseClient;
import de.tikron.ibase.client.IBaseClientException;

/**
 * Implementierung des ImageService auf Basis von iBase.
 *
 * @date 20.02.2012
 * @author Titus Kruse
 */
@Service("imageService")
public class ImageServiceIBaseImpl implements ImageService {
	
	private final String serverHost;

	private String serverUser;

	private String serverPassword;

	private IBaseClient iBaseClient;
	
	private String serverPath;

	public ImageServiceIBaseImpl(String host) {
		this.serverHost = Objects.requireNonNull(host, "Constructor argument host must not be null");
	}

	@PostConstruct
	public void init() {
		iBaseClient = new IBaseClient(serverHost);
		if (serverUser != null) {
			iBaseClient.setUserName(serverUser);
		}
		if (serverPassword != null) {
			iBaseClient.setPassword(serverPassword);
		}
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

	public String getServerUser() {
		return serverUser;
	}

	public void setServerUser(String serverUser) {
		this.serverUser = serverUser;
	}

	public String getServerPassword() {
		return serverPassword;
	}

	public void setServerPassword(String serverPassword) {
		this.serverPassword = serverPassword;
	}
}

/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.service.common;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
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

	private String serverPath;

	private String serverUser;

	private String serverPassword;

	private IBaseClient iBaseClient;

	@PostConstruct
	public void init() throws URISyntaxException {
		iBaseClient = new IBaseClient(new URI(serverPath));
		if (serverUser != null) {
			iBaseClient.setUserName(serverUser);
		}
		if (serverPassword != null) {
			iBaseClient.setPassword(serverPassword);
		}
	}

	@PreDestroy
	public void destroy() {
		iBaseClient.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.tikron.common.service.ImageService#getServerUrl()
	 */
	@Override
	public String getServerPath() {
		return serverPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.tikron.common.service.ImageService#saveCover(java.lang.String, byte[])
	 */
	@Override
	public boolean saveAlbumCover(String name, byte[] image) {
		return addImage(Constants.ALBUM_IMAGE_PATH + name, image);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.tikron.common.service.ImageService#deleteCover(java.lang.String)
	 */
	@Override
	public boolean deleteAlbumCover(String name) {
		return removeImage(Constants.ALBUM_IMAGE_PATH + name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.tikron.common.service.ImageService#saveGalleryImage(java.lang.String, byte[])
	 */
	@Override
	public boolean saveGalleryImage(String name, String category, byte[] image) {
		return addImage(Constants.GALLERY_IMAGE_PATH + category + "/" + name, image);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.tikron.common.service.ImageService#deleteGalleryImage(java.lang.String)
	 */
	@Override
	public boolean deleteGalleryImage(String name, String category) {
		return removeImage(Constants.GALLERY_IMAGE_PATH + category + "/" + name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.tikron.common.service.ImageService#moveGalleryImage(java.lang.String, java.lang.String, java.lang.String)
	 */
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

	@Required
	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

	@Autowired
	public void setServerUser(String serverUser) {
		this.serverUser = serverUser;
	}

	@Autowired
	public void setServerPassword(String serverPassword) {
		this.serverPassword = serverPassword;
	}

}

/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.service.common;

/**
 * A service providing methods for dynamic images like album covers or pictures.
 *
 * @date 20.02.2012
 * @author Titus Kruse
 */
public interface ImageService {

	/**
	 * Returns the path to the image server.
	 * 
	 * @return The image server URL.
	 */
	public String getServerPath();

	/**
	 * Saves the given image as cover on the image server.
	 * 
	 * @param name The name of the cover.
	 * @param image The cover image.
	 * @return true, if the operation was successful.
	 */
	public boolean saveAlbumCover(String name, byte[] image);

	/**
	 * Deletes a cover image from the image server.
	 * 
	 * @param name The name of the cover.
	 * @return true, if the operation was successful.
	 */
	public boolean deleteAlbumCover(String name);

	/**
	 * Saves the given image as gallery image on the image server.
	 * 
	 * @param name The name of the image.
	 * @param category The category name.
	 * @param image The image data.
	 * @return true, if the operation was successful.
	 */
	public boolean saveGalleryImage(String name, String category, byte[] image);

	/**
	 * Deletes a gallery image from the image server.
	 * 
	 * @param name The name of the image.
	 * @param category The category name.
	 * @return true, if the operation was successful.
	 */
	public boolean deleteGalleryImage(String name, String category);

	/**
	 * Moves a gallery image from one category to another.
	 * 
	 * @param name The name of the image.
	 * @param sourceCategory The source category name.
	 * @param targetCategory The target category name.
	 * @return true, if the operation was successful.
	 */
	public boolean moveGalleryImage(String name, String sourceCategory, String targetCategory);

}

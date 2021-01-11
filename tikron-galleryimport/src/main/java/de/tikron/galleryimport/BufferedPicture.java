/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.galleryimport;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import de.tikron.persistence.model.gallery.Picture;

/**
 * Ein Bild zum Puffern.
 * 
 * @since 05.11.2008
 * @author Titus Kruse
 */
public class BufferedPicture {

	private String name;
	private String title;
	private String description;
	private String imageName;

	public BufferedPicture(String name) {
		this.name = name;
	}

	public BufferedPicture(Picture picture) {
		copyFromPicture(picture);
	}

	public void copyFromPicture(Picture picture) {
		this.name = picture.getName();
		this.title = picture.getTitle();
		this.description = picture.getDescription();
		this.imageName = picture.getImageName();
	}

	public void copyToPicture(Picture picture) {
		picture.setName(name);
		picture.setTitle(title);
		picture.setDescription(description);
		picture.setImageName(imageName);
	}

	public void overridePicture(Picture picture) {
		picture.setName(name);
		if (StringUtils.isEmpty(picture.getTitle()))
			picture.setTitle(title);
		if (StringUtils.isEmpty(picture.getDescription()))
			picture.setDescription(description);
		picture.setImageName(imageName);
	}

	@Override
	public boolean equals(Object anObject) {
		if (this == anObject) {
			return true;
		}
		if (anObject instanceof BufferedPicture) {
			BufferedPicture anotherBufferedPicture = (BufferedPicture) anObject;
			if (Objects.equals(this.getName(), anotherBufferedPicture.getName())) {
				if (Objects.equals(this.getTitle(), anotherBufferedPicture.getTitle())) {
					if (Objects.equals(this.getDescription(), anotherBufferedPicture.getDescription())) {
						if (Objects.equals(this.getImageName(), anotherBufferedPicture.getImageName())) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

}

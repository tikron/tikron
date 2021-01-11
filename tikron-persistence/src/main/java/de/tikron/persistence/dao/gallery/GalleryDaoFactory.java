/**
 * Copyright (c) 2009 by Titus Kruse.
 */
package de.tikron.persistence.dao.gallery;

import javax.persistence.EntityManager;

/**
 * 
 *
 * @since 10.04.2009
 * @author Titus Kruse
 */
public class GalleryDaoFactory {

	private static final GalleryDaoFactory INSTANCE = new GalleryDaoFactory();

	private static CatalogDao catalogDao;

	private static CategoryDao categoryDao;

	private static PictureDao pictureDao;

	private GalleryDaoFactory() {
	}

	public static GalleryDaoFactory getInstance() {
		return INSTANCE;
	}

	public static CatalogDao getCatalogDao(EntityManager entityManager) {
		if (catalogDao == null)
			catalogDao = new CatalogDaoJpaImpl(entityManager);
		return catalogDao;
	}

	public static CategoryDao getCategoryDao(EntityManager entityManager) {
		if (categoryDao == null)
			categoryDao = new CategoryDaoJpaImpl(entityManager);
		return categoryDao;
	}

	public static PictureDao getPictureDao(EntityManager entityManager) {
		if (pictureDao == null)
			pictureDao = new PictureDaoJpaImpl(entityManager);
		return pictureDao;
	}

}

/**
 * Copyright (c) 2009 by Titus Kruse.
 */
package de.tikron.webapp.service.gallery;

import java.net.URI;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.tikron.persistence.dao.gallery.CatalogDao;
import de.tikron.persistence.dao.gallery.CategoryDao;
import de.tikron.persistence.dao.gallery.PictureDao;
import de.tikron.persistence.dao.user.CommentDao;
import de.tikron.persistence.model.gallery.Catalog;
import de.tikron.persistence.model.gallery.Category;
import de.tikron.persistence.model.gallery.Picture;
import de.tikron.persistence.model.user.CategoryComment;
import de.tikron.persistence.model.user.Comment;
import de.tikron.persistence.model.user.PictureComment;
import de.tikron.webapp.service.common.ImageService;
import de.tikron.webapp.util.Pager;

/**
 * Standard-Implementation des GalleryService.
 * 
 * @date 15.03.2009
 * @author Titus Kruse
 */
@Service("galleryService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GalleryServiceImpl implements GalleryService {

	private static final String IMAGE_SECTIONPATH = "gallery";

	private static final String[] IMAGE_TEMPLATES = { "galleryImage", "galleryThumbnail", "galleryTeaser", "galleryStory" };

	private CatalogDao catalogDao;

	private CategoryDao categoryDao;

	private PictureDao pictureDao;

	private CommentDao commentDao;

	private ImageService imageService;

	@Override
	public Catalog getCatalog(Long catalogId) {
		return catalogDao.findById(catalogId);
	}

	@Override
	public Catalog getCatalog(String name) {
		return catalogDao.findByName(name);
	}

	@Override
	public Catalog getCatalogWithCategories(Long catalogId) {
		return catalogDao.findByIdFetchCategories(catalogId);
	}

	@Override
	public List<Catalog> getCatalogs(boolean visibleOnly) {
		return catalogDao.findByVisibility(visibleOnly);
	}

	@Override
	public List<Catalog> getCatalogsWithCategories(boolean visibleOnly) {
		List<Catalog> catalogs = catalogDao.findByVisibilityFetchCategories(visibleOnly);
		// http://www.nosid.org/java8-comparator-extensions.html
		catalogs.sort(Comparator.comparingDouble(Catalog::getSequence));
		return catalogs;
	}

	@Override
	public Category getCategory(Long categoryId) {
		return categoryDao.findById(categoryId);
	}

	@Override
	public Category getCategory(String name) {
		return categoryDao.findByName(name);
	}

	@Override
	public List<Category> getCategories(boolean visibleOnly) {
		List<Category> categories = categoryDao.findByVisibility(visibleOnly);
		categories.sort(Comparator.comparingDouble(Category::getSequence));
		return categories;
	}

	@Override
	public List<Category> getCategories(Catalog catalog, boolean visibleOnly) {
		List<Category> categories = categoryDao.findByCatalogAndVisibility(catalog, visibleOnly);
		categories.sort(Comparator.comparingDouble(Category::getSequence));
		return categories;
	}

	@Override
	public Picture getPicture(Long pictureId) {
		return pictureDao.findById(pictureId);
	}

	@Override
	public Picture getPicture(String name) {
		return pictureDao.findByName(name);
	}

	@Override
	public List<Picture> getPictures(Category category) {
		return pictureDao.findByCategoryOrderByName(category);
	}

	@Override
	public Pager<Picture> getPicturePager(Category category) {
		// Object[] params = new Object[]{category};
		// List<Picture> pictures =
		// dataAccessHelper.executeQuery("SELECT o FROM Picture o WHERE o.category = ?1 ORDER BY o.name", params);
		// return new Pager(pictures);
		return new Pager<Picture>(getPictures(category));
	}

	@Override
	public List<CategoryComment> getComments(Category category) {
		// TODO Join fetch comment type and user
		List<CategoryComment> comments = commentDao.findVisibleByCategory(category);
		comments.sort(Comparator.comparing(Comment::getCreatedOn).reversed());
		return comments;
	}

	@Override
	public List<PictureComment> getComments(Picture picture) {
		// TODO Join fetch comment type and user
		List<PictureComment> comments = commentDao.findVisibleByPicture(picture);
		comments.sort(Comparator.comparing(Comment::getCreatedOn).reversed());
		return comments;
	}

	@Override
	public Picture getRandomPicture() {
		return pictureDao.findForTeaser();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
	public URI getImageUri(Picture picture, String template) {
		return imageService.getSeoImageUri(IMAGE_SECTIONPATH, picture.getCategory().getName(), picture.getImageName(), template);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
	public Map<String, URI> getImageUris(Picture picture) {
		Map<String, URI> imageUris = new HashMap<>(IMAGE_TEMPLATES.length);
		for (String template : IMAGE_TEMPLATES) {
			imageUris.put(template, getImageUri(picture, template));
		}
		return imageUris;
	}

	@Autowired
	public void setCatalogDao(CatalogDao catalogDao) {
		this.catalogDao = catalogDao;
	}

	@Autowired
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Autowired
	public void setPictureDao(PictureDao pictureDao) {
		this.pictureDao = pictureDao;
	}

	@Autowired
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Autowired
	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

}

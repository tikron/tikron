/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.service.gallery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.tikron.manager.service.common.CRUDServiceImpl;
import de.tikron.persistence.dao.gallery.PictureDao;
import de.tikron.persistence.model.gallery.Category;
import de.tikron.persistence.model.gallery.Picture;

/**
 * Default-Implementation des PictureService.
 * 
 * @date 12.02.2012
 * @author Titus Kruse
 */
@Service("pictureService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PictureServiceImpl extends CRUDServiceImpl<Picture, Long> implements PictureService {

	@Override
	public List<Picture> getPictures(Category category) {
		return getPictureDao().findByCategoryOrderByName(category);
	}

	@Override
	public Picture getPrevious(Picture picture) {
		return getPictureDao().findPrevious(picture.getCategory(), picture);
	}

	@Override
	public Picture getNext(Picture picture) {
		return getPictureDao().findNext(picture.getCategory(), picture);
	}

	protected PictureDao getPictureDao() {
		return (PictureDao) super.getDao();
	}

	@Autowired
	public void setPictureDao(PictureDao dao) {
		super.setDao(dao);
	}

}

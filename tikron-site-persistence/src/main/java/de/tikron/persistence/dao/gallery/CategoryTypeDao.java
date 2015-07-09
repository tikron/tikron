/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.persistence.dao.gallery;

import de.tikron.jpa.dao.GenericDao;
import de.tikron.persistence.model.gallery.CategoryType;
import de.tikron.persistence.model.gallery.CategoryTypeId;

/**
 * DAO for category types.
 *
 * @date 05.04.2015
 * @author Titus Kruse
 */
public interface CategoryTypeDao extends GenericDao<CategoryType, CategoryTypeId> {

}

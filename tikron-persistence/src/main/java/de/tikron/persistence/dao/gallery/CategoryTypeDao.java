/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.persistence.dao.gallery;

import de.tikron.persistence.model.gallery.CategoryType;
import de.tikron.persistence.model.gallery.CategoryTypeId;
import de.tikru.commons.jpa.dao.GenericDao;

/**
 * DAO for category types.
 *
 * @author Titus Kruse
 * @since 05.04.2015
 */
public interface CategoryTypeDao extends GenericDao<CategoryType, CategoryTypeId> {

}

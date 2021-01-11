/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.persistence.dao.user;

import de.tikron.persistence.model.user.Role;
import de.tikron.persistence.model.user.RoleId;
import de.tikru.commons.jpa.dao.GenericDao;

/**
 * DAO for roles.
 *
 * @author Titus Kruse
 * @since 01.04.2015
 */
public interface RoleDao extends GenericDao<Role, RoleId> {

}

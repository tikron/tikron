/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.persistence.dao.user;

import de.tikron.jpa.dao.GenericDao;
import de.tikron.persistence.model.user.Role;
import de.tikron.persistence.model.user.RoleId;

/**
 * DAO for roles.
 *
 * @date 01.04.2015
 * @author Titus Kruse
 */
public interface RoleDao extends GenericDao<Role, RoleId> {

}

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
 * @date 01.04.2015
 * @author Titus Kruse
 */
public interface RoleDao extends GenericDao<Role, RoleId> {

}

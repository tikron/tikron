/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.tikron.manager.service.common.CacheServiceImpl;
import de.tikron.persistence.dao.user.RoleDao;
import de.tikron.persistence.model.user.Role;
import de.tikron.persistence.model.user.RoleId;

/**
 * Default implementation of {@link RoleService}.
 *
 * @date 01.04.2015
 * @author Titus Kruse
 */
@Service("roleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class RoleServiceImpl extends CacheServiceImpl<Role, RoleId> implements RoleService {

	protected RoleDao getRoleDao() {
		return (RoleDao) super.getDao();
	}

	@Autowired
	public void setRoleDao(RoleDao dao) {
		super.setDao(dao);
	}

}

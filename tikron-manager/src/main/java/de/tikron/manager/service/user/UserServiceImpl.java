/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.tikron.manager.service.common.CRUDServiceImpl;
import de.tikron.persistence.dao.user.UserDao;
import de.tikron.persistence.model.user.User;

/**
 * Default-Implementation des UserService.
 *
 * @author Titus Kruse
 * @since 05.02.2012
 */
@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl extends CRUDServiceImpl<User, Long> implements UserService {

	protected UserDao getUserDao() {
		return (UserDao) super.getDao();
	}

	@Autowired
	public void setUserDao(UserDao dao) {
		super.setDao(dao);
	}
	
	@Override
	public User getWithRoles(Long id) {
		return getUserDao().findByIdFetchRoles(id);
	}

	@Override
	public User get(String name) {
		return getUserDao().findByName(name);
	}

}

/**
 * Copyright (c) 2010 by Titus Kruse.
 */
package de.tikron.manager.service.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.tikron.persistence.dao.user.UserDao;
import de.tikron.persistence.model.user.Role;

/**
 * Implementaion of UserDetailsService.
 *
 * @date 09.12.2010
 * @author Titus Kruse
 */
@Service("userDetailsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		de.tikron.persistence.model.user.User user = this.userDao.findByNameFetchRoles(username);
		if (user == null)
			throw new UsernameNotFoundException("User not found.");
		if (!user.isRegistered())
			throw new UsernameNotFoundException("User not registered.");
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getId().toString()));
		}
		return new User(user.getName(), user.getPassword(), true, true, true, true, authorities);
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}

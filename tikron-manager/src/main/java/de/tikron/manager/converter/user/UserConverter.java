/**
 * Copyright (c) 2011 by Titus Kruse.
 */
package de.tikron.manager.converter.user;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.tikron.manager.converter.common.NumericKeyEntityConverter;
import de.tikron.manager.service.user.UserService;
import de.tikron.persistence.model.user.User;

/**
 * Converter used as an interface between primary key and entity.
 * 
 * @date 27.11.2011
 * @author Titus Kruse
 */
@ManagedBean
@ApplicationScoped
public class UserConverter extends NumericKeyEntityConverter<User> {

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	public UserConverter() {
		super(User.class);
	}

	public void setUserService(UserService userService) {
		super.setService(userService);
	}

}

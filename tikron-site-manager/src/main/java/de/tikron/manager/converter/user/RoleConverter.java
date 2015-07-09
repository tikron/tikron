/**
 * Copyright (c) 2011 by Titus Kruse.
 */
package de.tikron.manager.converter.user;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.tikron.manager.converter.common.AbstractEntityConverter;
import de.tikron.manager.service.user.RoleService;
import de.tikron.persistence.model.user.Role;
import de.tikron.persistence.model.user.RoleId;

/**
 * Converter used as an interface between primary key and entity.
 * 
 * @date 27.11.2011
 * @author Titus Kruse
 * @author BalusC
 * 
 * @see http://balusc.blogspot.com/2011/09/communication-in-jsf-20.html
 */
@ManagedBean
@ApplicationScoped
public class RoleConverter extends AbstractEntityConverter<Role, RoleId> {

	@ManagedProperty(value = "#{roleService}")
	private RoleService roleService;

	public RoleConverter() {
		super(Role.class);
	}

	@Override
	protected RoleId toKey(String value) {
		return RoleId.valueOf(value);
	}

	public void setRoleService(RoleService roleService) {
		super.setService(roleService);
	}

}

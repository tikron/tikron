/**
 * Copyright (c) 2014 by Titus Kruse.
 */
package de.tikron.manager.bean.user;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.web.jsf.FacesContextUtils;

import de.tikron.manager.service.user.RoleService;
import de.tikron.persistence.model.user.Role;

/**
 * Helper bean providing a list of available roles.
 *
 * @author Titus Kruse
 * @since 24.11.2014
 */
@ManagedBean
@ApplicationScoped
public class RoleBean implements Serializable {

private static final long serialVersionUID = -4465980950752118142L;
	//	@ManagedProperty(value = "#{roleService}")
	private transient RoleService roleService;

	public List<Role> getRoles() {
		return getRoleService().getAll();
	}

	/**
	 * Returns configured {@link de.tikron.manager.service.user.RoleService} (even after restore of serialized bean).
	 * 
	 * @see http://stackoverflow.com/questions/20067698/injecting-non-serializable-application-scoped-bean-as-managed-property-of-serial
	 *  
	 * @return The RoleService.
	 */
	private RoleService getRoleService() {
		if (roleService == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			roleService = FacesContextUtils.getWebApplicationContext(context).getBean(RoleService.class);
		}
		return roleService;
	}

}

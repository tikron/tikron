/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.persistence.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import de.tikru.commons.jpa.domain.EnumeratedKeyEntity;

/**
 * A single role. Each role determines application permissions for users. A user can have none, one or multiple roles.
 * 
 * @since 01.04.2015
 * @author Titus Kruse
 */
@Entity
@NamedQueries({ @NamedQuery(name = Role.NQ_FIND_ALL, query = "SELECT o FROM Role o"),
		@NamedQuery(name = Role.NQ_FIND_BY_ID, query = "SELECT o FROM Role o WHERE o.id = :id"), })
@Table(name = "role")
public class Role extends EnumeratedKeyEntity<RoleId> {

	private static final long serialVersionUID = -1498416861726489027L;
	
	public static final String NQ_FIND_ALL = "Role.findAll";
	public static final String NQ_FIND_BY_ID = "Role.findById";
	
	public static final Role ADMINISTRATE = new Role(RoleId.ROLE_ADMIN);
	public static final Role MANAGE = new Role(RoleId.ROLE_MANAGE);
	public static final Role USE = new Role(RoleId.ROLE_USE);

	@Column
	private String description;

	public Role() {
	}

	public Role(RoleId id) {
		super(id);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("description", description).toString();
	}

}

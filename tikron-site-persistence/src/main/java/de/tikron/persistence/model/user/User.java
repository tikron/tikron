/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.persistence.model.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;

import de.tikron.jpa.domain.GeneratedKeyEntity;
import de.tikron.jpa.domain.ShowableEntity;
import de.tikron.jpa.validation.AllowedDomain;
import de.tikron.jpa.validation.ValidationConstants;

/**
 * A single user.
 * 
 * @author Titus Kruse
 */
@Entity
@NamedQueries({ @NamedQuery(name = User.NQ_FIND_ALL, query = "SELECT o FROM User o"),
		@NamedQuery(name = User.NQ_FIND_BY_NAME, query = "SELECT DISTINCT o FROM User o WHERE o.name = :name") })
@NamedEntityGraphs({
	@NamedEntityGraph(name = User.NEG_ROLES, attributeNodes={@NamedAttributeNode("roles")})
})
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class User extends GeneratedKeyEntity<Long> implements ShowableEntity<Long> {

	public static final String NQ_FIND_ALL = "User.findAll";
	public static final String NQ_FIND_BY_NAME = "User.findByName";

	public static final String NEG_ROLES = "User.includeRoles";

	@Column(nullable = false)
	@NotNull(message = "{user.name.NotNull}")
	@Size(max = 255, message = "{user.name.Size}")
	private String name;
	
	@Column
	@Size(max = 255, message = "{user.password.Size}")
	private String password;

	@Column
	@Size(max = 255, message = "{user.email.Size}")
	@Pattern(regexp = ValidationConstants.EMAIL_MASK, message = "{user.email.Pattern}")
	private String email;

	@Size(max = 255, message = "{user.url.Size}")
	@Pattern.List({
		@Pattern(regexp = ValidationConstants.URL_MASK, message = "{user.url.Pattern}"),
//		@Pattern(regexp = ValidationConstants.URL_MASK_DE, message = "{user.url.NotSpam}")
	})
	@AllowedDomain(value = {".de", ".at", ".ch"}, message = "{user.url.AllowedDomain}")
	private String url;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
	private Set<Role> roles;

	public User() {
	}

	/**
	 * Constructor with required fields.
	 * 
	 * @param name Benutzername.
	 * @param role Rolle.
	 */
	public User(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		roles.add(role);
	}
	
	public void removeRole(Role role) {
		roles.remove(role);
	}
	
	@Override
	public String getDisplayName() {
		return getName();
	}

	/**
	 * Indicates whether this user is a registered user.
	 *  
	 * @return true, if the user is a registered user.
	 */
	public boolean isRegistered() {
		// Assume this user is registered after he or she entered a password in the registration process.
		return getPassword() != null;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("name", name).toString();
	}

}

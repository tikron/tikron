/**
 * Copyright (c) 2014 by Titus Kruse.
 */
package de.tikron.persistence.model.user;

/**
 * The primary key for roles. 
 *
 * @since 24.11.2014
 * @author Titus Kruse
 */
public enum RoleId {
	
	// We have to use (upper case) enumerarion values as persistent values, because Hibernate uses getValue() to determine it.
	// Only possible solution to use alternate values is implementing a org.hibernate.usertype.UserType.
	// see https://developer.jboss.org/wiki/UserTypeforpersistingaTypesafeEnumerationwithaVARCHARcolumn
	ROLE_ADMIN, ROLE_MANAGE, ROLE_USE;
}

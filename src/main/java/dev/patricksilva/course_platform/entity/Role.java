package dev.patricksilva.course_platform.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Role {
	private Long roleId;
	private String name;

	private Set<User> users = new HashSet<User>();

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

// Getters and Setters

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

// hashCode and equals
	@Override
	public int hashCode() {
		return Objects.hash(name, roleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(name, other.name) && Objects.equals(roleId, other.roleId);
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", name=" + name + "]";
	}

}

package dev.patricksilva.course_platform.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.patricksilva.course_platform.entity.Role;

public interface RoleDao extends JpaRepository<Role, Long> {

	public Role findByName(String name);
}

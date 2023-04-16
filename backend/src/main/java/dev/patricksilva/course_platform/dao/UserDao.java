package dev.patricksilva.course_platform.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.patricksilva.course_platform.entity.User;

public interface UserDao extends JpaRepository<User, Long> {

	public User findByEmail(String email);
}

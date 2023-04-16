package dev.patricksilva.course_platform.service;

import dev.patricksilva.course_platform.entity.User;

public interface UserService {

	User loadUserByEmail(String email);

	User createUser(String email, String password);

	void assignRoleToUser(String email, String roleName);
}

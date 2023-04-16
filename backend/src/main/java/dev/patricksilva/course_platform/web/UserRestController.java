package dev.patricksilva.course_platform.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.patricksilva.course_platform.service.UserService;

@RestController
public class UserRestController {

	private UserService userService;

	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public boolean checkIfEmailExists(@RequestParam(name = "email", defaultValue = "") String email) {
		return userService.loadUserByEmail(email) != null;
	}
}

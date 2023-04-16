package dev.patricksilva.course_platform.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.patricksilva.course_platform.dto.CourseDTO;
import dev.patricksilva.course_platform.dto.InstructorDTO;
import dev.patricksilva.course_platform.entity.User;
import dev.patricksilva.course_platform.service.CourseService;
import dev.patricksilva.course_platform.service.InstructorService;
import dev.patricksilva.course_platform.service.UserService;

@RestController
@RequestMapping("/instructors")
public class InstructorRestController {
	private InstructorService instructorService;

	private UserService userService;

	private CourseService courseService;

	public InstructorRestController(InstructorService instructorService, UserService userService,
			CourseService courseService) {
		this.instructorService = instructorService;
		this.userService = userService;
		this.courseService = courseService;
	}

	@GetMapping
	public Page<InstructorDTO> searchInstructors(@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {

		return instructorService.findInstructorsByName(keyword, page, size);
	}

	@GetMapping("/all")
	public List<InstructorDTO> findAllInstructors() {

		return instructorService.fetchInstructors();
	}

	@DeleteMapping("/{instructorId}")
	public void deleteInstructor(@PathVariable Long instructorId) {
		instructorService.removeInstructor(instructorId);
	}

	@PostMapping
	public InstructorDTO saveInstructor(@RequestBody InstructorDTO instructorDTO) {
		User user = userService.loadUserByEmail(instructorDTO.getUser().getEmail());
		if (user != null)
			throw new RuntimeException("Email Already Exist");

		return instructorService.createInstructor(instructorDTO);
	}

	@PutMapping("/{instructorId}")
	public InstructorDTO updateInstructor(@RequestBody InstructorDTO instructorDTO, @PathVariable Long instructorId) {
		instructorDTO.setInstructorId(instructorId);

		return instructorService.updateInstructor(instructorDTO);
	}

	@GetMapping("/{instructorId}/courses")
	public Page<CourseDTO> coursesByInstructorId(@PathVariable Long instructorId,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {

		return courseService.fetchCoursesForInstructor(instructorId, page, size);
	}

	@GetMapping("/find")
	public InstructorDTO loadInstructorByEmail(@RequestParam(name = "email", defaultValue = "") String email) {

		return instructorService.loadInstructorByEmail(email);
	}
}

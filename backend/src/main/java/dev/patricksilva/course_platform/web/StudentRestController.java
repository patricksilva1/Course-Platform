package dev.patricksilva.course_platform.web;

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
import dev.patricksilva.course_platform.dto.StudentDTO;
import dev.patricksilva.course_platform.entity.User;
import dev.patricksilva.course_platform.service.CourseService;
import dev.patricksilva.course_platform.service.StudentService;
import dev.patricksilva.course_platform.service.UserService;

@RestController
@RequestMapping("/students")
public class StudentRestController {

	private StudentService studentService;

	private UserService userService;

	private CourseService courseService;

	public StudentRestController(StudentService studentService, UserService userService, CourseService courseService) {
		this.studentService = studentService;
		this.userService = userService;
		this.courseService = courseService;
	}

	@GetMapping
	public Page<StudentDTO> searchStudents(@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {

		return studentService.loadStudentsByName(keyword, page, size);
	}

	@DeleteMapping("/{studentId}")
	public void deleteStudent(@PathVariable Long studentId) {
		studentService.removeStudent(studentId);
	}

	@PostMapping
	public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO) {
		User user = userService.loadUserByEmail(studentDTO.getUser().getEmail());
		if (user != null)
			throw new RuntimeException("Email Already Exist");

		return studentService.createStudent(studentDTO);
	}

	@PutMapping("/{studentId}")
	public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable Long studentId) {
		studentDTO.setStudentId(studentId);

		return studentService.updateStudent(studentDTO);
	}

	@GetMapping("/{studentId}/courses")
	public Page<CourseDTO> coursesByStudentId(@PathVariable Long studentId,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {

		return courseService.fetchCoursesForStudent(studentId, page, size);
	}

	@GetMapping("/{studentId}/other-courses")
	public Page<CourseDTO> nonSubscribedCoursesByStudentId(@PathVariable Long studentId,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {

		return courseService.fetchNonEnrolledInCoursesForStudent(studentId, page, size);
	}

	@GetMapping("/find")
	public StudentDTO loadStudentByEmail(@RequestParam(name = "email", defaultValue = "") String email) {

		return studentService.loadStudentByEmail(email);
	}
}

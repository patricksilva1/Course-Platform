package dev.patricksilva.course_platform.service;

import org.springframework.data.domain.Page;

import dev.patricksilva.course_platform.dto.CourseDTO;
import dev.patricksilva.course_platform.entity.Course;

public interface CourseService {

	Course loadCourseById(Long id);

	CourseDTO createCourse(CourseDTO courseDTO);

	CourseDTO updateCourse(CourseDTO courseDTO);

	void assignStudentToCourse(Long courseId, Long studentId);

	void removeCourse(Long courseId);

	Page<CourseDTO> findCoursesByCourseName(String keyword, int page, int size);

	Page<CourseDTO> fetchCoursesForStudent(Long studentId, int page, int size);

	Page<CourseDTO> fetchNonEnrolledInCoursesForStudent(Long studentId, int page, int size);

	Page<CourseDTO> fetchCoursesForInstructor(Long instructorId, int page, int size);
}

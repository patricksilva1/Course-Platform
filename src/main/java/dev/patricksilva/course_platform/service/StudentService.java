package dev.patricksilva.course_platform.service;

import org.springframework.data.domain.Page;

import dev.patricksilva.course_platform.dto.StudentDTO;
import dev.patricksilva.course_platform.entity.Student;

public interface StudentService {
	Student loadStudentById(Long studentId);

	Page<StudentDTO> loadStudentsByName(String name, int page, int size);

	StudentDTO loadStudentByEmail(String email);

	StudentDTO createStudent(StudentDTO studentDTO);

	StudentDTO updateStudent(StudentDTO studentDTO);

	void removeStudent(Long studentId);
}

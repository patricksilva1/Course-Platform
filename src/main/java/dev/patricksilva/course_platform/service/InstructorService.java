package dev.patricksilva.course_platform.service;

import java.util.List;

import org.springframework.data.domain.Page;

import dev.patricksilva.course_platform.dto.InstructorDTO;
import dev.patricksilva.course_platform.entity.Instructor;

public interface InstructorService {

	Instructor loadInstructorById(Long instructorId);

	Page<InstructorDTO> findInstructorsByName(String name, int page, int size);

	InstructorDTO loadInstructorByEmail(String email);

	InstructorDTO createInstructor(InstructorDTO instructorDTO);

	InstructorDTO updateInstructor(InstructorDTO instructorDTO);

	List<InstructorDTO> fetchInstructors();

	void removeInstructor(Long instructorId);

}

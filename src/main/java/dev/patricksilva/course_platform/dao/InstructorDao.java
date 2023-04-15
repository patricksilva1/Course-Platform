package dev.patricksilva.course_platform.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.patricksilva.course_platform.entity.Instructor;

public interface InstructorDao extends JpaRepository<Instructor, Long> {

	@Query(value = "select i from Instructor as i where i.firstName like %:name% or i.lastName like %:name%")
	List<Instructor> findInstructorsByName(@Param("name") String name);

	@Query(value = "select i from Instructor as i where i.user.email=:email")
	List<Instructor> findInstructorByEmail(@Param("email") String email);
}

package dev.patricksilva.course_platform.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.patricksilva.course_platform.entity.Student;

public interface StudentDao extends JpaRepository<Student, Long> {

	@Query(value = "select s from Student as s where s.firstName like %:name% or s.lastName like %:name%")
	List<Student> findStudentsByName(@Param("name") String name);

	@Query(value = "select s from Student as s where s.user.email=:email")
	public Student findStudentByEmail(@Param("email") String email);
}

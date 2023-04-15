package dev.patricksilva.course_platform.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.patricksilva.course_platform.entity.Course;

public interface CourseDao extends JpaRepository<Course, Long> {

	Page<Course> findCoursesByCourseNameContains(String keyword, Pageable pageable);

	@Query(value = "select * from courses as c where c.course_id in (select e.course_id from enrolled_in as e where e.student_id=:studentId)", nativeQuery = true)
	Page<Course> getCoursesByStudentId(@Param("studentId") Long studentId, Pageable pageable);

	@Query(value = "select * from courses as c where c.course_id not in (select e.course_id from enrolled_in as e where e.student_id=:studentId)", nativeQuery = true)
	Page<Course> getNonEnrolledIdCoursesByStudentId(@Param("studentId") Long studentId, Pageable pageable);

	@Query(value = "select c from Course as c where c.instructor.instructorId=:instructorId")
	Page<Course> getCoursesByInstructorId(@Param("intructorId") Long intructorId, Pageable pageable);
}

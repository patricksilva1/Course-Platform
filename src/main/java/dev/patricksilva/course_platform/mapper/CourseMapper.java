package dev.patricksilva.course_platform.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import dev.patricksilva.course_platform.dto.CourseDTO;
import dev.patricksilva.course_platform.entity.Course;

@Service
public class CourseMapper {

	private InstructorMapper instructorMapper;

	public CourseMapper(InstructorMapper instructorMapper) {
		this.instructorMapper = instructorMapper;
	}

	public CourseDTO fromCourse(Course course) {
		CourseDTO courseDTO = new CourseDTO();
		BeanUtils.copyProperties(course, courseDTO);

		courseDTO.setInstructor(instructorMapper.fromInstructor(course.getInstructor()));

		return courseDTO;
	}

	public Course fromCourseDTO(CourseDTO courseDTO) {
		Course course = new Course();
		BeanUtils.copyProperties(courseDTO, course);

		return course;
	}
}

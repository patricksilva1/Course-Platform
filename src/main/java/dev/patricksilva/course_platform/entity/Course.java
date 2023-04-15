package dev.patricksilva.course_platform.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Course {

	private Long courseId;
	private String courseName;
	private String courseDuration;
	private String courseDescription;

	private Instructor instructor;
	private Set<Student> students = new HashSet<Student>();

	public Course() {

	}

	public Course(Long courseId, String courseName, String courseDuration, String courseDescription,
			Instructor instructor) {

		this.courseId = courseId;
		this.courseName = courseName;
		this.courseDuration = courseDuration;
		this.courseDescription = courseDescription;
		this.instructor = instructor;
	}

	// Getters and Setters
	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseDuration=" + courseDuration
				+ ", courseDescription=" + courseDescription + "]";
	}

	// hashCode and equals
	@Override
	public int hashCode() {
		return Objects.hash(courseDescription, courseDuration, courseId, courseName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(courseDescription, other.courseDescription)
				&& Objects.equals(courseDuration, other.courseDuration) && Objects.equals(courseId, other.courseId)
				&& Objects.equals(courseName, other.courseName);
	}

}

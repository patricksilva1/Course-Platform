package dev.patricksilva.course_platform.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id", nullable = false)
	private Long courseId;

	@Basic
	@Column(name = "course_name", nullable = false, length = 45)
	private String courseName;

	@Basic
	@Column(name = "course_duration", nullable = false, length = 45)
	private String courseDuration;

	@Basic
	@Column(name = "course_description", nullable = false, length = 64)
	private String courseDescription;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instructor_id", referencedColumnName = "instructor_id", nullable = false)
	private Instructor instructor;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "enrolled_in",
	joinColumns = {@JoinColumn(name = "course_id")},
	inverseJoinColumns = {@JoinColumn(name = "student_id")})
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

	public void assignStudentToCourse(Student student) {
		this.students.add(student);
		student.getCourses().add(this);
	}

	public void removeStudentFromCourse(Student student) {
		this.students.remove(student);
		student.getCourses().remove(this);
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

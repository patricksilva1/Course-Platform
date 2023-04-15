package dev.patricksilva.course_platform.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Student {

	private Long studentId;
	private String firstName;
	private String lastName;
	private String level;

	private Set<Course> courses = new HashSet<Course>();
	private User user;

	public Student() {
	}

	public Student(Long studentId, String firstName, String lastName, String level, User user) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.level = level;
		this.user = user;
	}

	// Getters and Setters
	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName + ", level="
				+ level + "]";
	}

	// hashCode and equals
	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, level, studentId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(level, other.level) && Objects.equals(studentId, other.studentId);
	}
}

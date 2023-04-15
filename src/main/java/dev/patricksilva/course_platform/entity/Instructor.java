package dev.patricksilva.course_platform.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Instructor {

	private Long instructorId;
	private String firstName;
	private String lastName;
	private String summary;

	private Set<Course> courses = new HashSet<Course>();
	private User user;

	public Instructor() {
	}

	public Instructor(Long instructorId, String firstName, String lastName, String summary, User user) {
		this.instructorId = instructorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.summary = summary;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Instructor [instructorId=" + instructorId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", summary=" + summary + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, instructorId, lastName, summary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Instructor other = (Instructor) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(instructorId, other.instructorId)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(summary, other.summary);
	}

	// Getters and Setters
	public Long getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(Long instructorId) {
		this.instructorId = instructorId;
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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

}

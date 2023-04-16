package dev.patricksilva.course_platform.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.patricksilva.course_platform.dao.InstructorDao;
import dev.patricksilva.course_platform.dto.InstructorDTO;
import dev.patricksilva.course_platform.entity.Course;
import dev.patricksilva.course_platform.entity.Instructor;
import dev.patricksilva.course_platform.entity.User;
import dev.patricksilva.course_platform.mapper.InstructorMapper;
import dev.patricksilva.course_platform.service.CourseService;
import dev.patricksilva.course_platform.service.InstructorService;
import dev.patricksilva.course_platform.service.UserService;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {

	private InstructorDao instructorDao;

	private InstructorMapper instructorMapper;

	private UserService userService;

	private CourseService courseService;

	public InstructorServiceImpl(InstructorDao instructorDao, InstructorMapper instructorMapper,
			UserService userService, CourseService courseService) {
		this.instructorDao = instructorDao;
		this.instructorMapper = instructorMapper;
		this.userService = userService;
		this.courseService = courseService;
	}

	@Override
	public Instructor loadInstructorById(Long instructorId) {
		return instructorDao.findById(instructorId)
				.orElseThrow(() -> new EntityNotFoundException("Instructor with ID" + instructorId + " not found"));
	}

	@Override
	public Page<InstructorDTO> findInstructorsByName(String name, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<Instructor> instructorsPage = instructorDao.findInstructorsByName(name, pageRequest);
		return new PageImpl<>(instructorsPage.getContent().stream()
				.map(instructor -> instructorMapper.fromInstructor(instructor)).collect(Collectors.toList()),
				pageRequest, instructorsPage.getTotalElements());
	}

	@Override
	public InstructorDTO loadInstructorByEmail(String email) {
		return instructorMapper.fromInstructor(instructorDao.findInstructorByEmail(email));
	}

	@Override
	public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
		User user = userService.createUser(instructorDTO.getUser().getEmail(), instructorDTO.getUser().getPassword());
		userService.assignRoleToUser(user.getEmail(), "Instructor");
		Instructor instructor = instructorMapper.fromInstructorDTO(instructorDTO);
		instructor.setUser(user);
		Instructor savedInstructor = instructorDao.save(instructor);
		return instructorMapper.fromInstructor(savedInstructor);
	}

	@Override
	public InstructorDTO updateInstructor(InstructorDTO instructorDTO) {
		Instructor loadedInstructor = loadInstructorById(instructorDTO.getInstructorId());
		Instructor instructor = instructorMapper.fromInstructorDTO(instructorDTO);
		instructor.setUser(loadedInstructor.getUser());
		instructor.setCourses(loadedInstructor.getCourses());
		Instructor updatedInstructor = instructorDao.save(instructor);
		return instructorMapper.fromInstructor(updatedInstructor);
	}

	@Override
	public List<InstructorDTO> fetchInstructors() {
		return instructorDao.findAll().stream().map(instructor -> instructorMapper.fromInstructor(instructor))
				.collect(Collectors.toList());
	}

	@Override
	public void removeInstructor(Long instructorId) {
		Instructor instructor = loadInstructorById(instructorId);
		for (Course course : instructor.getCourses()) {
			courseService.removeCourse(course.getCourseId());
		}
		instructorDao.deleteById(instructorId);
	}

}

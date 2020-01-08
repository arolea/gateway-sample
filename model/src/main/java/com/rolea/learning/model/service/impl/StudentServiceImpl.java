package com.rolea.learning.model.service.impl;

import com.rolea.learning.model.domain.Student;
import com.rolea.learning.model.persistence.StudentRepository;
import com.rolea.learning.model.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StudentServiceImpl implements StudentService {

	private static final Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Optional<Student> findStudentById(Long id) {
		LOG.info("Finding student with id {}", id);
		return studentRepository.findById(id);
	}

	@Override
	public List<Student> findAllStudents() {
		LOG.info("Finding all students");
		return studentRepository.findAll();
	}

	@Override
	public Student createStudent(Student student) {
		LOG.info("Creating student");
		return studentRepository.save(student);
	}

}

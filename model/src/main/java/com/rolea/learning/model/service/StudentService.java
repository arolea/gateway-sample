package com.rolea.learning.model.service;

import com.rolea.learning.model.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

	Optional<Student> findStudentById(Long id);

	List<Student> findAllStudents();

	Student createStudent(Student student);

}

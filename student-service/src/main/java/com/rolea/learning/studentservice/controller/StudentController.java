package com.rolea.learning.studentservice.controller;

import com.rolea.learning.model.domain.Student;
import com.rolea.learning.model.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping(value = "/{id}")
	public Optional<Student> findStudentById(@PathVariable(name = "id") Long id){
		return studentService.findStudentById(id);
	}

	@GetMapping
	public List<Student> findAllStudents(){
		return studentService.findAllStudents();
	}

	@PostMapping
	public Student createStudent(@RequestBody Student student){
		return studentService.createStudent(student);
	}

}

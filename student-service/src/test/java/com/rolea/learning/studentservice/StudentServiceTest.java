package com.rolea.learning.studentservice;

import com.rolea.learning.model.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentServiceTest {

	@Autowired
	private TestRestTemplate template;

	@Test
	void test_student_controller() {
		Student student = new Student();
		student.setFirstName("John");
		student.setLastName("Doe");

		ResponseEntity<Student> createStudentResponse = template.exchange(
				"/students",
				HttpMethod.POST,
				new HttpEntity<>(student),
				Student.class
		);
		assertThat(createStudentResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		Student createdStudent = createStudentResponse.getBody();
		assertThat(createdStudent).isNotNull();
		assertThat(createdStudent.getStudentId()).isNotNull();

		UriComponents uri = UriComponentsBuilder
				.fromPath("/students/{id}")
				.buildAndExpand(createdStudent.getStudentId());

		ResponseEntity<Student> findStudentByIdResponse = template.exchange(
				uri.toUriString(),
				HttpMethod.GET,
				new HttpEntity<>(new HttpHeaders()),
				Student.class
		);
		assertThat(findStudentByIdResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(findStudentByIdResponse.getBody()).isNotNull();

		ResponseEntity<List<Student>> findAllStudentsResponse = template.exchange(
				"/students",
				HttpMethod.GET,
				new HttpEntity<>(new HttpHeaders()),
				new ParameterizedTypeReference<>() {
				}
		);
		assertThat(findAllStudentsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(findAllStudentsResponse.getBody().size()).isEqualTo(1);
	}

}

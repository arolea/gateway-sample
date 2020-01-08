package com.rolea.learning.studentservice;

import com.rolea.learning.model.config.ModelConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {ModelConfig.class})
public class StudentService {

	public static void main(String[] args) {
		SpringApplication.run(StudentService.class, args);
	}

}

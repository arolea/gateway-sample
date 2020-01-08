package com.rolea.learning.model.persistence;

import com.rolea.learning.model.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {



}

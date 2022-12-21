package com.jacaranda.studentdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.studentdb.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
}

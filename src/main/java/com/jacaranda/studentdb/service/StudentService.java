package com.jacaranda.studentdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacaranda.studentdb.model.Student;
import com.jacaranda.studentdb.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	StudentRepository repositorio;
	
	public Student getStudent(long id) {
		return repositorio.findById(id).orElse(null);
	}
	
	public List<Student> getStudents() {
		return repositorio.findAll();
	}
	
	public Student addStudent(Student student) {
		return repositorio.save(student);
	}
	
	public void deleteStudent(Student student) {
		repositorio.delete(student);
	}
	
	public Student updateStudent(Student student) {
		if(getStudent(student.getId()) != null) {
			return repositorio.save(student);
		} else {
			return null;
		}
	}
}

package com.jacaranda.studentdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.studentdb.model.Student;
import com.jacaranda.studentdb.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	StudentService servicio;
	
	@GetMapping("/listStudents")
	public String listStudent(Model modelo) {
		
		modelo.addAttribute("students", servicio.getStudents());
		return "listStudents";
	}
	
	@GetMapping("addStudent")
	public String addStudent(Model modelo) {
		
		Student student= new Student();
		
		modelo.addAttribute("student", student);
		
		return "addStudent";
	}
	
	@PostMapping("/addStudent/submit")
	public String addStudentSubmit(@Validated @ModelAttribute Student student, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "addStudent";
		} else {
			servicio.addStudent(student);
			return "redirect:/listStudents";
		}
	}
	
	@GetMapping("/delStudent")
	public String delStudent(Model model, @RequestParam long id) {
		
		Student student = servicio.getStudent(id);
		System.out.println(student.getId());
		model.addAttribute("student", student);
		
		return "deleteStudent";
	}
	
	@PostMapping("/delStudent/submit")
	public String listStudentsdelMethod (@ModelAttribute("student") Student student) {
		System.out.println(student.getId());
		servicio.deleteStudent(student);
			
		return "redirect:/listStudents";
	}
	
	@GetMapping("/editStudent")
	public String editStudent(Model model, @RequestParam(name="id") long id) {
		
		Student student = servicio.getStudent(id);
		
		model.addAttribute("student", student);
		
		return "editStudent";
	}
	
	@PostMapping("/editStudent/submit")
	public String listStudentseditMethod (@ModelAttribute Student student) {
		
		servicio.updateStudent(student);
			
		return "redirect:/listStudents";
	}
}

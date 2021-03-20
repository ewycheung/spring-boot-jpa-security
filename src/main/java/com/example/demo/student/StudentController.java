package com.example.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
	public List<Student> getStudents() {
		return studentService.getStudents();
	}

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Long studentId) {
        return studentService.getStudent(studentId);
    }    
    
    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId, @RequestBody Student student) {
        studentService.updateStudent(studentId, student);    
    }    
}

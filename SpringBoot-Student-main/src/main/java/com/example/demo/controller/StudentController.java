package com.example.demo.controller;

import com.example.demo.domain.Student;
import com.example.demo.domain.StudentRequest;
import com.example.demo.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public Student createStudent(@RequestBody StudentRequest studentRequest) {
        return studentService.addStudent(studentRequest);
    }

    @GetMapping("/{nim}")
    public Student getStudent(@PathVariable String nim) {
        return studentService.getStudent(nim);
    }

    @PutMapping("/{nim}")
    public Student updateStudent(@PathVariable String nim, @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudent(nim, studentRequest);
    }

    @DeleteMapping("/{nim}")
    public String removeStudent(@PathVariable String nim) {
        try {
            studentService.deleteStudent(nim);
            return "Successfully deleted";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}

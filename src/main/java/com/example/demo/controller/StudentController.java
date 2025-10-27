package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // GET endpoint untuk menampilkan semua students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST endpoint untuk menambah student baru
    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        try {
            // Validasi input
            if (student.getNim() == null || student.getNim().trim().isEmpty()) {
                return new ResponseEntity<>("NIM is required", HttpStatus.BAD_REQUEST);
            }
            if (student.getFullName() == null || student.getFullName().trim().isEmpty()) {
                return new ResponseEntity<>("Full name is required", HttpStatus.BAD_REQUEST);
            }
            if (student.getDob() == null) {
                return new ResponseEntity<>("Date of birth is required", HttpStatus.BAD_REQUEST);
            }

            Student newStudent = studentService.addStudent(student);
            return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding student: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
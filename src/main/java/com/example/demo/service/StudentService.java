package com.example.demo.service;

import com.example.demo.model.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();

    // Initialize with some sample data
    public StudentService() {
        // Add some sample students
        students.add(new Student("20210001", "John Doe", LocalDate.of(2000, 5, 15), "Jakarta"));
        students.add(new Student("20210002", "Jane Smith", LocalDate.of(1999, 8, 22), "Bandung"));
        students.add(new Student("20210003", "Bob Johnson", LocalDate.of(2001, 3, 10), "Surabaya"));
    }

    // Get all students
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    // Add new student
    public Student addStudent(Student student) {
        // Check if NIM already exists
        boolean nimExists = students.stream()
                .anyMatch(s -> s.getNim().equals(student.getNim()));
        
        if (nimExists) {
            throw new IllegalArgumentException("Student with NIM " + student.getNim() + " already exists");
        }
        
        students.add(student);
        return student;
    }

    // Find student by NIM (optional helper method)
    public Optional<Student> findStudentByNim(String nim) {
        return students.stream()
                .filter(student -> student.getNim().equals(nim))
                .findFirst();
    }
}
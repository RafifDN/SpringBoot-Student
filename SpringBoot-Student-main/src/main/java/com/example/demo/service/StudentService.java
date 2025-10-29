package com.example.demo.service;

import com.example.demo.domain.Student;
import com.example.demo.domain.StudentRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();

    public StudentService() {
        students.add(new Student("001", "Rafif Dado Naufal", LocalDate.of(2005, 7, 11), "Tangerang"));
        students.add(new Student("002", "Jane Smith", LocalDate.of(1999, 8, 22), "Bandung")); 
        students.add(new Student("003", "Bob Johnson", LocalDate.of(2001, 3, 10), "Surabaya"));
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public Student addStudent(StudentRequest request) {
        String count = String.format("%03d", students.size() + 1);
        Student newStudent = new Student(count, request.getFullName(), request.getDob(), request.getAddress());
        students.add(newStudent);
        return newStudent;
    }

    public Student getStudent(String nim) {
        return findStudentByNim(nim)
                .orElseThrow(() -> new RuntimeException("Student with nim " + nim + " not found"));
    }

    public Student updateStudent(String nim, StudentRequest request) {
        Student student = getStudent(nim);
        student.setFullName(request.getFullName());
        student.setDob(request.getDob());
        student.setAddress(request.getAddress());
        return student;
    }

    public void deleteStudent(String nim) {
        Optional<Student> studentOptional = students.stream()
                .filter(student -> student.getNim().equals(nim))
                .findFirst();
        
        if (studentOptional.isPresent()) {
            Student studentToBeDeleted = studentOptional.get();
            students.remove(studentToBeDeleted);
        } else {
            throw new RuntimeException("Student with nim " + nim + " not found");
        }
    }

    private Optional<Student> findStudentByNim(String nim) {
        return students.stream()
                .filter(student -> student.getNim().equals(nim))
                .findFirst();
    }
}
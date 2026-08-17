package com.smartattendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.smartattendance.dto.LoginRequest;
import com.smartattendance.entity.Student;
import com.smartattendance.service.StudentService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/students")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
    
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id) {

        studentService.deleteStudent(id);

        return "Student Deleted Successfully";
    }
    
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Integer id,
                                 @RequestBody Student student){

        return studentService.updateStudent(id, student);

    }
    @PostMapping("/login")
    public Student login(
            @RequestBody LoginRequest request,
            HttpSession session) {

        Student student = studentService.login(
                request.getEmail(),
                request.getPassword());

        if (student != null) {

            session.setAttribute("userId", student.getId());
            session.setAttribute("username", student.getName());
            session.setAttribute("email", student.getEmail());
            session.setAttribute("role", "Student");
        }

        return student;
    }
    @PostMapping("/register")
    public String registerStudent(
            @RequestBody Student student){

        return studentService
                .registerStudent(student);

    }
    @GetMapping("/{id}")

    public Student getStudentById(

    @PathVariable Integer id){

    return studentService.getStudentById(id);

    }
}
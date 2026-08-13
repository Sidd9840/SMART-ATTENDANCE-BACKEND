package com.smartattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartattendance.entity.Student;
import com.smartattendance.repository.StudentRepository;


@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // -----------------------------
    // Save Student
    // -----------------------------
    public Student saveStudent(Student student){

        student.setPassword(
                passwordEncoder.encode(student.getPassword())
        );

        return studentRepository.save(student);

    }
    // -----------------------------
    // Get All Students
    // -----------------------------
    public List<Student> getAllStudents(){

        return studentRepository.findAll();

    }

    // -----------------------------
    // Get Student By Id
    // -----------------------------
    public Student getStudentById(Integer id){

        return studentRepository.findById(id).orElse(null);

    }

    // -----------------------------
    // Update Student
    // -----------------------------
    public Student updateStudent(Integer id,
                                 Student student){

        Student existingStudent =
                studentRepository.findById(id).orElse(null);

        if(existingStudent == null){

            return null;

        }

        existingStudent.setName(student.getName());
        existingStudent.setRollNo(student.getRollNo());
        existingStudent.setCourse(student.getCourse());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPassword(student.getPassword());

        return studentRepository.save(existingStudent);

    }

    // -----------------------------
    // Delete Student
    // -----------------------------
    public void deleteStudent(Integer id){

        studentRepository.deleteById(id);

    }

    // -----------------------------
    // Student Login
    // -----------------------------
    public Student login(String email,
            String password){

Student student =
   studentRepository.findByEmail(email);

if(student != null &&
passwordEncoder.matches(
      password,
      student.getPassword())){

return student;

}

return null;

}

    // -----------------------------
    // Register Student
    // -----------------------------
    public String registerStudent(Student student){

        Student existingStudent =
                studentRepository.findByEmail(student.getEmail());

        if(existingStudent != null){

            return "Email Already Exists";

        }

        student.setPassword(
                passwordEncoder.encode(student.getPassword())
        );

        studentRepository.save(student);

        return "Student Registered Successfully";

    }

}
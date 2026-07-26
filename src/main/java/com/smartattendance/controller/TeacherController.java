package com.smartattendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.smartattendance.dto.LoginRequest;
import com.smartattendance.entity.Teacher;
import com.smartattendance.service.TeacherService;

@RestController
@RequestMapping("/teachers")
@CrossOrigin("*")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // -----------------------------
    // Register Teacher
    // -----------------------------
    @PostMapping("/register")
    public String registerTeacher(
            @RequestBody Teacher teacher){

        return teacherService.registerTeacher(teacher);

    }

    // -----------------------------
    // Add Teacher
    // -----------------------------
    @PostMapping
    public Teacher saveTeacher(
            @RequestBody Teacher teacher){

        return teacherService.saveTeacher(teacher);

    }

    // -----------------------------
    // Get All Teachers
    // -----------------------------
    @GetMapping
    public List<Teacher> getAllTeachers(){

        return teacherService.getAllTeachers();

    }

    // -----------------------------
    // Update Teacher
    // -----------------------------
    @PutMapping("/{id}")
    public Teacher updateTeacher(
            @PathVariable Integer id,
            @RequestBody Teacher teacher){

        return teacherService.updateTeacher(id, teacher);

    }

    // -----------------------------
    // Delete Teacher
    // -----------------------------
    @DeleteMapping("/{id}")
    public String deleteTeacher(
            @PathVariable Integer id){

        teacherService.deleteTeacher(id);

        return "Teacher Deleted Successfully";

    }

    // -----------------------------
    // Teacher Login
    // -----------------------------
    @PostMapping("/login")
    public Teacher loginTeacher(
            @RequestBody LoginRequest loginRequest){

        return teacherService.loginTeacher(

                loginRequest.getEmail(),

                loginRequest.getPassword()

        );

    }

    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable Integer id){

        return teacherService.getTeacherById(id);

    }
}
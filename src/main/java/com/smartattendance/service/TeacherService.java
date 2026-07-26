package com.smartattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartattendance.entity.Teacher;
import com.smartattendance.repository.TeacherRepository;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    // -----------------------------
    // Register Teacher
    // -----------------------------
    public String registerTeacher(Teacher teacher){

        if(teacherRepository.existsByEmail(teacher.getEmail())){

            return "Email Already Registered";

        }

        if(teacherRepository.existsByEmployeeId(teacher.getEmployeeId())){

            return "Employee ID Already Exists";

        }

        teacherRepository.save(teacher);

        return "Teacher Registered Successfully";

    }

    // -----------------------------
    // Save Teacher
    // -----------------------------
    public Teacher saveTeacher(Teacher teacher){

        return teacherRepository.save(teacher);

    }

    // -----------------------------
    // Get All Teachers
    // -----------------------------
    public List<Teacher> getAllTeachers(){

        return teacherRepository.findAll();

    }

    // -----------------------------
    // Delete Teacher
    // -----------------------------
    public void deleteTeacher(Integer id){

        teacherRepository.deleteById(id);

    }

    // -----------------------------
    // Update Teacher
    // -----------------------------
    public Teacher updateTeacher(Integer id,
                                 Teacher teacher){

        Teacher existingTeacher =
                teacherRepository.findById(id).orElse(null);

        if(existingTeacher != null){

            existingTeacher.setEmployeeId(
                    teacher.getEmployeeId());

            existingTeacher.setName(
                    teacher.getName());

            existingTeacher.setEmail(
                    teacher.getEmail());

            existingTeacher.setPassword(
                    teacher.getPassword());

            existingTeacher.setSubject(
                    teacher.getSubject());

            existingTeacher.setDepartment(
                    teacher.getDepartment());

            existingTeacher.setPhone(
                    teacher.getPhone());

            return teacherRepository.save(existingTeacher);

        }

        return null;

    }

    // -----------------------------
    // Teacher Login
    // -----------------------------
    public Teacher loginTeacher(String email,
                                String password){

        return teacherRepository
                .findByEmailAndPassword(email,password);

    }

    public Teacher getTeacherById(Integer id){

        return teacherRepository.findById(id).orElse(null);

    }

}
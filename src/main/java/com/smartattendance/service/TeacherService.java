package com.smartattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.smartattendance.entity.Teacher;
import com.smartattendance.repository.TeacherRepository;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

        teacher.setPassword(
                passwordEncoder.encode(teacher.getPassword())
        );

        teacherRepository.save(teacher);

        return "Teacher Registered Successfully";

    }

    // -----------------------------
    // Save Teacher
    // -----------------------------
    public Teacher saveTeacher(Teacher teacher){

        teacher.setPassword(
                passwordEncoder.encode(teacher.getPassword())
        );

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
                    passwordEncoder.encode(teacher.getPassword())
            );

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

Teacher teacher = teacherRepository.findByEmail(email);

if(teacher != null &&
passwordEncoder.matches(password, teacher.getPassword())){

return teacher;

}

return null;

}
    public Teacher getTeacherById(Integer id){

        return teacherRepository.findById(id).orElse(null);

    }

}
package com.smartattendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartattendance.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    // Login
    Teacher findByEmailAndPassword(String email, String password);

    // Registration Validation
    boolean existsByEmail(String email);

    boolean existsByEmployeeId(String employeeId);

    // Dashboard
    long count();

}
package com.smartattendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartattendance.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    long count();

    Student findByEmailAndPassword(String email, String password);

    Student findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByRollNo(String rollNo);

}
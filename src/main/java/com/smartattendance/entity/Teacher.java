package com.smartattendance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String employeeId;
    private String name;
    private String email;
    private String password;
    private String subject;
    private String department;
    private String phone;

    // Default Constructor
    public Teacher() {

    }

    // Parameterized Constructor
    public Teacher(Integer id,
                   String employeeId,
                   String name,
                   String email,
                   String password,
                   String subject,
                   String department,
                   String phone) {

        this.id = id;
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.subject = subject;
        this.department = department;
        this.phone = phone;

    }

    // Getter and Setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
package com.smartattendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.smartattendance.dto.LoginRequest;
import com.smartattendance.entity.Admin;
import com.smartattendance.service.AdminService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;
    
    @PostMapping("/register")
    public String registerAdmin(@RequestBody Admin admin){

        return adminService.registerAdmin(admin);

    }

    // -----------------------------
    // Save Admin
    // -----------------------------
    @PostMapping
    public Admin saveAdmin(
            @RequestBody Admin admin){

        return adminService.saveAdmin(admin);

    }

    @PostMapping("/login")
    public Admin login(
            @RequestBody LoginRequest request,
            HttpSession session) {

        Admin admin = adminService.login(
                request.getEmail(),
                request.getPassword());

        if (admin != null) {

            session.setAttribute("userId", admin.getId());
            session.setAttribute("username", admin.getUsername());
            session.setAttribute("email", admin.getEmail());
            session.setAttribute("role", "Admin");

        }

        return admin;
    }

}
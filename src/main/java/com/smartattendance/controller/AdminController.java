package com.smartattendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.smartattendance.dto.LoginRequest;
import com.smartattendance.entity.Admin;
import com.smartattendance.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // -----------------------------
    // Save Admin
    // -----------------------------
    @PostMapping
    public Admin saveAdmin(
            @RequestBody Admin admin){

        return adminService.saveAdmin(admin);

    }

    @PostMapping("/login")
    public Admin login(@RequestBody LoginRequest request) {

        Admin admin = adminService.login(
                request.getEmail(),
                request.getPassword());

        System.out.println("Login Email : " + request.getEmail());
        System.out.println("Admin Found : " + admin);

        return admin;
    }

}
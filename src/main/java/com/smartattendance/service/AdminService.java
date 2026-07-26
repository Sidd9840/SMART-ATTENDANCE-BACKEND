package com.smartattendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartattendance.entity.Admin;
import com.smartattendance.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // -----------------------------
    // Save Admin
    // -----------------------------
    public Admin saveAdmin(Admin admin){

        return adminRepository.save(admin);

    }

    // -----------------------------
    // Admin Login
    // -----------------------------
    public Admin login(String email,
                       String password){

        return adminRepository.findByEmailAndPassword(
                email,
                password);

    }

}
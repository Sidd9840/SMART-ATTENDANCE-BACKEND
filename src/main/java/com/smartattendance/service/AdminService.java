package com.smartattendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartattendance.entity.Admin;
import com.smartattendance.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Save Admin
    public Admin saveAdmin(Admin admin){

        admin.setPassword(
                passwordEncoder.encode(admin.getPassword())
        );

        return adminRepository.save(admin);

    }

    // Register Admin
    public String registerAdmin(Admin admin){

        if(adminRepository.findByEmail(admin.getEmail()) != null){

            return "Email Already Exists";

        }

        admin.setPassword(
                passwordEncoder.encode(admin.getPassword())
        );

        adminRepository.save(admin);

        return "Admin Registered Successfully";

    }

    // Admin Login
    public Admin login(String email, String password){

        Admin admin = adminRepository.findByEmail(email);

        if(admin != null &&
           passwordEncoder.matches(password, admin.getPassword())){

            return admin;

        }

        return null;

    }
    public Admin updateAdmin(Integer id, Admin admin){

        Admin existingAdmin = adminRepository.findById(id).orElse(null);

        if(existingAdmin == null){
            return null;
        }

        existingAdmin.setUsername(admin.getUsername());
        existingAdmin.setEmail(admin.getEmail());

        existingAdmin.setPassword(
                passwordEncoder.encode(admin.getPassword())
        );

        return adminRepository.save(existingAdmin);
    }
}	
package com.smartattendance.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/me")
    public Map<String, Object> getCurrentUser(HttpSession session) {

        Map<String, Object> response = new HashMap<>();

        Object userId = session.getAttribute("userId");

        if (userId == null) {
            response.put("loggedIn", false);
            return response;
        }

        response.put("loggedIn", true);
        response.put("id", userId);
        response.put("username", session.getAttribute("username"));
        response.put("email", session.getAttribute("email"));
        response.put("role", session.getAttribute("role"));

        return response;
    }

    @PostMapping("/logout")
    public Map<String, String> logout(HttpSession session) {

        session.invalidate();

        Map<String, String> response = new HashMap<>();

        response.put("message", "Logout successful");

        return response;
    }
}
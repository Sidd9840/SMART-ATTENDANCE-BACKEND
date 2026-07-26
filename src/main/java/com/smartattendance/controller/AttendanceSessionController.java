package com.smartattendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.smartattendance.entity.AttendanceSession;
import com.smartattendance.service.AttendanceSessionService;

@RestController
@RequestMapping("/attendance-session")
@CrossOrigin("*")
public class AttendanceSessionController {

    @Autowired
    private AttendanceSessionService attendanceSessionService;

    // Start Attendance Session
    @PostMapping("/start")
    public AttendanceSession startAttendance(
            @RequestParam String subject) {

        return attendanceSessionService.startSession(subject);

    }

    // Close Attendance Session
    @PostMapping("/close")
    public AttendanceSession closeAttendance() {

        return attendanceSessionService.closeSession();

    }
    @GetMapping("/current")
    public AttendanceSession getCurrentSession() {

        return attendanceSessionService.getCurrentSession();

    }

}
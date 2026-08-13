package com.smartattendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.smartattendance.dto.AttendanceSessionRequest;
import com.smartattendance.entity.AttendanceSession;
import com.smartattendance.service.AttendanceSessionService;

@RestController
@RequestMapping("/attendance-session")
@CrossOrigin("*")
public class AttendanceSessionController {

    @Autowired
    private AttendanceSessionService attendanceSessionService;

    // -----------------------------
    // Start Attendance Session
    // -----------------------------
    @PostMapping("/start")
    public AttendanceSession startAttendance(
            @RequestBody AttendanceSessionRequest request) {

        return attendanceSessionService.startSession(request);

    }

    // -----------------------------
    // Close Attendance Session
    // -----------------------------
    @PostMapping("/close")
    public AttendanceSession closeAttendance(
            @RequestBody AttendanceSessionRequest request) {

        return attendanceSessionService.closeSession(request);

    }

    // -----------------------------
    // Current Attendance Session
    // -----------------------------
    @GetMapping("/current")
    public AttendanceSession getCurrentSession(

            @RequestParam Integer teacherId,

            @RequestParam String subject,

            @RequestParam String lecture,

            @RequestParam String classType) {

        return attendanceSessionService.getCurrentSession(

                teacherId,

                subject,

                lecture,

                classType);

    }

}
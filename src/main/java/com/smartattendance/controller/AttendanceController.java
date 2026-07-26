package com.smartattendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.smartattendance.dto.AttendancePercentageResponse;
import com.smartattendance.entity.Attendance;
import com.smartattendance.service.AttendanceService;

@RestController
@RequestMapping("/attendance")
@CrossOrigin("*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // Add Attendance
    @PostMapping
    public Object saveAttendance(@RequestBody Attendance attendance) {

        try {
            return attendanceService.saveAttendance(attendance);
        } catch (RuntimeException e) {
            return e.getMessage();
        }

    }

    // Get All Attendance
    @GetMapping
    public List<Attendance> getAllAttendance() {

        return attendanceService.getAllAttendance();

    }

    // Get Attendance By Student
    @GetMapping("/student/{studentId}")
    public List<Attendance> getAttendanceByStudent(
            @PathVariable Integer studentId) {

        return attendanceService.getAttendanceByStudent(studentId);

    }

    // Attendance Percentage
    @GetMapping("/percentage/{studentId}")
    public AttendancePercentageResponse getPercentage(
            @PathVariable Integer studentId) {

        return attendanceService.getAttendancePercentage(studentId);

    }

    // Update Attendance
    @PutMapping("/{id}")
    public Attendance updateAttendance(
            @PathVariable Integer id,
            @RequestBody Attendance attendance) {

        return attendanceService.updateAttendance(id, attendance);

    }

    // Delete Attendance
    @DeleteMapping("/{id}")
    public String deleteAttendance(@PathVariable Integer id) {

        attendanceService.deleteAttendance(id);

        return "Attendance Deleted Successfully";

    }

 // -----------------------------
 // Month & Year Report
 // -----------------------------
 @GetMapping("/month")
 public List<Attendance> getAttendanceByMonthAndYear(

         @RequestParam int month,
         @RequestParam int year){

     return attendanceService.getAttendanceByMonthAndYear(
             month,
             year);

 }
 @GetMapping("/search")
 public List<Attendance> searchAttendance(
         @RequestParam String keyword){

     return attendanceService.searchAttendance(keyword);

 }
}
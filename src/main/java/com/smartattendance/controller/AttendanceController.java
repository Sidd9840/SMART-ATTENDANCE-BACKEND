package com.smartattendance.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartattendance.dto.AttendancePercentageResponse;
import com.smartattendance.entity.Attendance;
import com.smartattendance.pdf.AttendancePdfService;
import com.smartattendance.service.AttendanceService;

@RestController
@RequestMapping("/attendance")
@CrossOrigin("*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private AttendancePdfService attendancePdfService;


    // -----------------------------------------
    // Add Attendance
    // -----------------------------------------

    @PostMapping
    public Object saveAttendance(
            @RequestBody Attendance attendance) {

        try {

            return attendanceService.saveAttendance(
                    attendance
            );

        } catch (RuntimeException e) {

            return e.getMessage();
        }
    }


    // -----------------------------------------
    // Get All Attendance
    // -----------------------------------------

    @GetMapping
    public List<Attendance> getAllAttendance() {

        return attendanceService.getAllAttendance();
    }


    // -----------------------------------------
    // Get Attendance By Student
    // -----------------------------------------

    @GetMapping("/student/{studentId}")
    public List<Attendance> getAttendanceByStudent(
            @PathVariable Integer studentId) {

        return attendanceService.getAttendanceByStudent(
                studentId
        );
    }


    // -----------------------------------------
    // Get Attendance By Teacher
    // -----------------------------------------

    @GetMapping("/teacher/{teacherId}")
    public List<Attendance> getAttendanceByTeacher(
            @PathVariable Integer teacherId) {

        return attendanceService.getAttendanceByTeacher(
                teacherId
        );
    }


    // -----------------------------------------
    // Attendance Percentage
    // -----------------------------------------

    @GetMapping("/percentage/{studentId}")
    public AttendancePercentageResponse getPercentage(
            @PathVariable Integer studentId) {

        return attendanceService.getAttendancePercentage(
                studentId
        );
    }


    // -----------------------------------------
    // Update Attendance
    // -----------------------------------------

    @PutMapping("/{id}")
    public Attendance updateAttendance(
            @PathVariable Integer id,
            @RequestBody Attendance attendance) {

        return attendanceService.updateAttendance(
                id,
                attendance
        );
    }


    // -----------------------------------------
    // Delete Attendance
    // -----------------------------------------

    @DeleteMapping("/{id}")
    public String deleteAttendance(
            @PathVariable Integer id) {

        attendanceService.deleteAttendance(id);

        return "Attendance Deleted Successfully";
    }


    // -----------------------------------------
    // Month & Year Report
    // -----------------------------------------

    @GetMapping("/month")
    public List<Attendance>
    getAttendanceByMonthAndYear(

            @RequestParam int month,

            @RequestParam int year) {

        return attendanceService
                .getAttendanceByMonthAndYear(
                        month,
                        year
                );
    }


    // -----------------------------------------
    // Teacher Wise Month & Year Report
    // -----------------------------------------

    @GetMapping("/teacher/month")
    public List<Attendance>
    getTeacherAttendanceByMonthAndYear(

            @RequestParam Integer teacherId,

            @RequestParam int month,

            @RequestParam int year) {

        return attendanceService
                .getTeacherAttendanceByMonthAndYear(
                        teacherId,
                        month,
                        year
                );
    }


    // -----------------------------------------
    // Search Attendance
    // Teacher + Student + Month + Year
    // -----------------------------------------

    @GetMapping("/search")
    public List<Attendance> search(

            @RequestParam Integer teacherId,

            @RequestParam String keyword,

            @RequestParam String subject,

            @RequestParam int month,

            @RequestParam int year) {

        return attendanceService.search(

                teacherId,

                keyword,

                subject,

                month,

                year
        );
    }


    // -----------------------------------------
    // Download Teacher Attendance PDF
    // -----------------------------------------

    @GetMapping("/report/pdf")
    public ResponseEntity<InputStreamResource>
    downloadPdf(

            @RequestParam Integer teacherId) {

        ByteArrayInputStream pdf =
                attendancePdfService.generatePdf(
                        teacherId
                );

        HttpHeaders headers =
                new HttpHeaders();

        headers.add(
                "Content-Disposition",
                "attachment; filename=Attendance_Report.pdf"
        );

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(
                        MediaType.APPLICATION_PDF
                )
                .body(
                        new InputStreamResource(pdf)
                );
    }

}
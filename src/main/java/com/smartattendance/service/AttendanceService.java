package com.smartattendance.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartattendance.dto.AttendancePercentageResponse;
import com.smartattendance.entity.Attendance;
import com.smartattendance.repository.AttendanceRepository;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    // -----------------------------
    // Save Attendance
    // -----------------------------
    public Attendance saveAttendance(Attendance attendance) {

        attendance.setAttendanceDate(LocalDate.now());
        attendance.setAttendanceTime(LocalTime.now());

        // -----------------------------
        // Holiday Check
        // -----------------------------
//        DayOfWeek day = LocalDate.now().getDayOfWeek();
//
//        if (day == DayOfWeek.SATURDAY ||
//            day == DayOfWeek.SUNDAY) {
//
//            throw new RuntimeException(
//                    "Today is Holiday. Attendance cannot be marked.");
//
//        }

        // -----------------------------
        // Attendance Time & Lecture
        // -----------------------------
       

//        if (!currentTime.isBefore(LocalTime.of(9, 0)) &&
//            !currentTime.isAfter(LocalTime.of(9, 15))) {
//
//            lecture = "Lecture 1";
//
//        }
//
//        else if (!currentTime.isBefore(LocalTime.of(11, 0)) &&
//                 !currentTime.isAfter(LocalTime.of(11, 15))) {
//
//            lecture = "Lecture 2";
//
//        }
//
//        else if (!currentTime.isBefore(LocalTime.of(14, 0)) &&
//                 !currentTime.isAfter(LocalTime.of(14, 15))) {
//
//            lecture = "Lecture 3";
//
//        }
//
//        else if (!currentTime.isBefore(LocalTime.of(16, 0)) &&
//                 !currentTime.isAfter(LocalTime.of(16, 15))) {
//
//            lecture = "Lecture 4";
//
//        }
//
//        else {
//
//            throw new RuntimeException(
//                    "Attendance can only be marked during the first 15 minutes of each lecture.");
//
//        }

       

        // -----------------------------
        // Duplicate Attendance Check
        // -----------------------------
        Attendance existingAttendance =
                attendanceRepository
                .findByStudentIdAndTeacherIdAndSubjectAndAttendanceDateAndLectureAndClassType(

                        attendance.getStudentId(),

                        attendance.getTeacherId(),

                        attendance.getSubject(),

                        attendance.getAttendanceDate(),

                        attendance.getLecture(),

                        attendance.getClassType()

                );

        if (existingAttendance != null) {

        	throw new RuntimeException(
        	        "Attendance already marked.");

        }

        attendance.setStatus("Present");

        return attendanceRepository.save(attendance);

    }
    // -----------------------------
    // Get All Attendance
    // -----------------------------
    public List<Attendance> getAllAttendance() {

        return attendanceRepository.findAll();

    }

    // -----------------------------
    // Get Attendance By Student
    // -----------------------------
    public List<Attendance> getAttendanceByStudent(Integer studentId) {

        return attendanceRepository.findByStudentId(studentId);

    }

    // -----------------------------
    // Delete Attendance
    // -----------------------------
    public void deleteAttendance(Integer id) {

        attendanceRepository.deleteById(id);

    }

    // -----------------------------
    // Update Attendance
    // -----------------------------
    public Attendance updateAttendance(Integer id,
                                       Attendance attendance) {

        Attendance existingAttendance =
                attendanceRepository.findById(id).orElse(null);

        if (existingAttendance == null) {

            return null;

        }

        existingAttendance.setStatus(attendance.getStatus());

        return attendanceRepository.save(existingAttendance);

    }

    // -----------------------------
    // Attendance Percentage
    // -----------------------------
    public AttendancePercentageResponse
    getAttendancePercentage(Integer studentId) {

        long total =
                attendanceRepository.countByStudentId(studentId);

        long present =
                attendanceRepository.countByStudentIdAndStatus(
                        studentId,
                        "Present");

        double percentage = 0;

        if (total > 0) {

            percentage = (present * 100.0) / total;

        }

        return new AttendancePercentageResponse(
                total,
                present,
                percentage);

    }

    // -----------------------------
    // Month & Year Report
    // -----------------------------
    public List<Attendance> getAttendanceByMonthAndYear(
            int month,
            int year) {

        return attendanceRepository.findByMonthAndYear(
                month,
                year);

    }

    // -----------------------------
    // Search Attendance
    // -----------------------------
    public List<Attendance> searchAttendance(String keyword) {

        return attendanceRepository
                .findByStudentNameContainingIgnoreCase(keyword);

    }
    public List<Attendance> search(String keyword, int month, int year) {

        return attendanceRepository.searchAttendance(
                keyword,
                month,
                year
        );

    }


}
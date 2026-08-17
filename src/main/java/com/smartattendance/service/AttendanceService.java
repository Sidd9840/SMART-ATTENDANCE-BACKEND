package com.smartattendance.service;

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


    // -----------------------------------------
    // Save Attendance
    // -----------------------------------------

    public Attendance saveAttendance(Attendance attendance) {

        attendance.setAttendanceDate(LocalDate.now());
        attendance.setAttendanceTime(LocalTime.now());

        // -----------------------------------------
        // Duplicate Attendance Check
        // -----------------------------------------

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
                    "Attendance already marked."
            );

        }

        // -----------------------------------------
        // Mark Present
        // -----------------------------------------

        attendance.setStatus("Present");

        return attendanceRepository.save(attendance);
    }


    // -----------------------------------------
    // Get All Attendance
    // -----------------------------------------

    public List<Attendance> getAllAttendance() {

        return attendanceRepository.findAll();
    }


    // -----------------------------------------
    // Get Attendance By Student
    // -----------------------------------------

    public List<Attendance> getAttendanceByStudent(
            Integer studentId) {

        return attendanceRepository.findByStudentId(
                studentId
        );
    }


    // -----------------------------------------
    // Get Attendance By Teacher
    // -----------------------------------------

    public List<Attendance> getAttendanceByTeacher(
            Integer teacherId) {

        return attendanceRepository.findByTeacherId(
                teacherId
        );
    }


    // -----------------------------------------
    // Delete Attendance
    // -----------------------------------------

    public void deleteAttendance(Integer id) {

        attendanceRepository.deleteById(id);
    }


    // -----------------------------------------
    // Update Attendance
    // -----------------------------------------

    public Attendance updateAttendance(
            Integer id,
            Attendance attendance) {

        Attendance existingAttendance =
                attendanceRepository
                .findById(id)
                .orElse(null);

        if (existingAttendance == null) {

            return null;
        }

        existingAttendance.setStatus(
                attendance.getStatus()
        );

        return attendanceRepository.save(
                existingAttendance
        );
    }


    // -----------------------------------------
    // Attendance Percentage
    // -----------------------------------------

    public AttendancePercentageResponse
    getAttendancePercentage(Integer studentId) {

        long total =
                attendanceRepository
                .countByStudentId(studentId);

        long present =
                attendanceRepository
                .countByStudentIdAndStatus(
                        studentId,
                        "Present"
                );

        double percentage = 0;

        if (total > 0) {

            percentage =
                    (present * 100.0) / total;
        }

        return new AttendancePercentageResponse(
                total,
                present,
                percentage
        );
    }


    // -----------------------------------------
    // Month & Year Report
    // -----------------------------------------

    public List<Attendance>
    getAttendanceByMonthAndYear(
            int month,
            int year) {

        return attendanceRepository
                .findByMonthAndYear(
                        month,
                        year
                );
    }


    // -----------------------------------------
    // Teacher + Month + Year Report
    // -----------------------------------------

    public List<Attendance>
    getTeacherAttendanceByMonthAndYear(
            Integer teacherId,
            int month,
            int year) {

        return attendanceRepository
                .findByTeacherAndMonthAndYear(
                        teacherId,
                        month,
                        year
                );
    }


    // -----------------------------------------
    // Search Attendance
    // -----------------------------------------

    public List<Attendance>
    searchAttendance(String keyword) {

        return attendanceRepository
                .findByStudentNameContainingIgnoreCase(
                        keyword
                );
    }


 // -----------------------------------------
 // Teacher + Student + Subject Search
 // + Month + Year
 // -----------------------------------------

 public List<Attendance> search(
         Integer teacherId,
         String keyword,
         String subject,
         int month,
         int year) {

     return attendanceRepository.searchAttendance(

             teacherId,

             keyword,

             subject,

             month,

             year
     );
 }

}
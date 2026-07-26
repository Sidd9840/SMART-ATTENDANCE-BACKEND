package com.smartattendance.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartattendance.entity.AttendanceSession;

@Repository
public interface AttendanceSessionRepository extends JpaRepository<AttendanceSession, Integer> {

    // Today's Attendance Session
    AttendanceSession findByAttendanceDate(LocalDate attendanceDate);

    // Open Session
    AttendanceSession findByAttendanceDateAndStatus(
            LocalDate attendanceDate,
            String status);

}
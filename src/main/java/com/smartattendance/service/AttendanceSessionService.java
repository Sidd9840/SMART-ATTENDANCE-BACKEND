package com.smartattendance.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartattendance.entity.Attendance;
import com.smartattendance.entity.AttendanceSession;
import com.smartattendance.entity.Student;
import com.smartattendance.repository.AttendanceRepository;
import com.smartattendance.repository.AttendanceSessionRepository;
import com.smartattendance.repository.StudentRepository;

@Service
public class AttendanceSessionService {

    @Autowired
    private AttendanceSessionRepository attendanceSessionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    // -----------------------------
    // Start Attendance Session
    // -----------------------------
    public AttendanceSession startSession(String subject) {

        AttendanceSession session = new AttendanceSession();

        session.setSubject(subject);
        session.setAttendanceDate(LocalDate.now());
        session.setStartTime(LocalTime.now());
        session.setStatus("OPEN");

        return attendanceSessionRepository.save(session);

    }

    // -----------------------------
    // Close Attendance Session
    // -----------------------------
    public AttendanceSession closeSession() {

        AttendanceSession session =
                attendanceSessionRepository.findByAttendanceDateAndStatus(
                        LocalDate.now(),
                        "OPEN");

        if (session == null) {

            throw new RuntimeException("No Open Attendance Session");

        }

        // Close Session
        session.setEndTime(LocalTime.now());
        session.setStatus("CLOSED");

        attendanceSessionRepository.save(session);

        // Get All Students
        List<Student> students = studentRepository.findAll();

        for (Student student : students) {

            Attendance attendance =
                    attendanceRepository.findByStudentIdAndAttendanceDate(
                            student.getId(),
                            LocalDate.now());

            // Attendance not marked → Mark Absent
            if (attendance == null) {

                Attendance absent = new Attendance();

                absent.setStudentId(student.getId());

                absent.setStudentName(student.getName());

                absent.setSubject(session.getSubject());

                absent.setAttendanceDate(LocalDate.now());

                absent.setAttendanceTime(LocalTime.now());

                absent.setStatus("Absent");

                absent.setTeacherId(null);

                absent.setLatitude(null);

                absent.setLongitude(null);

                absent.setDistance(null);

                attendanceRepository.save(absent);

            }

        }

        return session;

    }

    public AttendanceSession getCurrentSession() {

        return attendanceSessionRepository
                .findByAttendanceDateAndStatus(
                        LocalDate.now(),
                        "OPEN");

    }

}
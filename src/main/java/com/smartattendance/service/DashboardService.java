package com.smartattendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartattendance.dto.DashboardResponse;
import com.smartattendance.repository.AttendanceRepository;
import com.smartattendance.repository.StudentRepository;
import com.smartattendance.repository.TeacherRepository;

@Service
public class DashboardService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    public DashboardResponse getDashboardData(Integer teacherId) {

        DashboardResponse response = new DashboardResponse();

        response.setTotalStudents(studentRepository.count());

        response.setTotalTeachers(teacherRepository.count());

        response.setTotalAttendance(
                attendanceRepository.countByTeacherId(teacherId));

        response.setPresent(
                attendanceRepository.countByTeacherIdAndStatus(
                        teacherId,
                        "Present"));

        response.setAbsent(
                attendanceRepository.countByTeacherIdAndStatus(
                        teacherId,
                        "Absent"));

        return response;
    }

    public DashboardResponse getAdminDashboard() {

        DashboardResponse response = new DashboardResponse();

        response.setTotalStudents(studentRepository.count());

        response.setTotalTeachers(teacherRepository.count());

        return response;
    }
}
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

    public DashboardResponse getDashboardData() {

        DashboardResponse response = new DashboardResponse();

        response.setTotalStudents(studentRepository.count());

        response.setTotalTeachers(teacherRepository.count());

        response.setTotalAttendance(attendanceRepository.count());

        response.setPresent(
                attendanceRepository.countByStatus("Present"));

        response.setAbsent(
                attendanceRepository.countByStatus("Absent"));

        return response;
    }

}
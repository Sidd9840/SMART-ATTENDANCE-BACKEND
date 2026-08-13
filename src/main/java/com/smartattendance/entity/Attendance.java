package com.smartattendance.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer studentId;

    private Integer teacherId;

    private String studentName;

    private String subject;
    

    private LocalDate attendanceDate;

    private LocalTime attendanceTime;

    private String status;

    // Lecture
    private String lecture;

    private String classType;

    // Geo-Fencing Fields
    private Double latitude;

    private Double longitude;

    private Double distance;

    // Default Constructor
    public Attendance() {

    }

    // Parameterized Constructor
    public Attendance(
            Integer id,
            Integer studentId,
            Integer teacherId,
            String studentName,
            String subject,
            LocalDate attendanceDate,
            LocalTime attendanceTime,
            String status,
            String lecture,
            String classType,
            Double latitude,
            Double longitude,
            Double distance) {

        this.id = id;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.studentName = studentName;
        this.subject = subject;
        this.attendanceDate = attendanceDate;
        this.attendanceTime = attendanceTime;
        this.status = status;
        this.lecture = lecture;
        this.classType = classType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;

    }

    public Integer getId() {

        return id;

    }

    public void setId(Integer id) {

        this.id = id;

    }

    public Integer getStudentId() {

        return studentId;

    }

    public void setStudentId(Integer studentId) {

        this.studentId = studentId;

    }

    public Integer getTeacherId() {

        return teacherId;

    }

    public void setTeacherId(Integer teacherId) {

        this.teacherId = teacherId;

    }

    public String getStudentName() {

        return studentName;

    }

    public void setStudentName(String studentName) {

        this.studentName = studentName;

    }

    public String getSubject() {

        return subject;

    }

    public void setSubject(String subject) {

        this.subject = subject;

    }

    public LocalDate getAttendanceDate() {

        return attendanceDate;

    }

    public void setAttendanceDate(LocalDate attendanceDate) {

        this.attendanceDate = attendanceDate;

    }

    public LocalTime getAttendanceTime() {

        return attendanceTime;

    }

    public void setAttendanceTime(LocalTime attendanceTime) {

        this.attendanceTime = attendanceTime;

    }

    public String getStatus() {

        return status;

    }

    public void setStatus(String status) {

        this.status = status;

    }

    public String getLecture() {

        return lecture;

    }

    public void setLecture(String lecture) {

        this.lecture = lecture;

    }
    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }
    public Double getLatitude() {

        return latitude;

    }

    public void setLatitude(Double latitude) {

        this.latitude = latitude;

    }

    public Double getLongitude() {

        return longitude;

    }

    public void setLongitude(Double longitude) {

        this.longitude = longitude;

    }

    public Double getDistance() {

        return distance;

    }

    public void setDistance(Double distance) {

        this.distance = distance;

    }

}
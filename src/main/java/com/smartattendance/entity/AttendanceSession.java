package com.smartattendance.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AttendanceSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String subject;
    

    private LocalDate attendanceDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private String status;

    private Integer teacherId;
    private String lecture;

    private String classType;
    // -----------------------------
    // Teacher Live Location
    // -----------------------------
    private Double teacherLatitude;

    private Double teacherLongitude;

    private Double allowedDistance;

    public AttendanceSession() {
    }

    public AttendanceSession(
            Integer id,
            String subject,
            LocalDate attendanceDate,
            LocalTime startTime,
            LocalTime endTime,
            String status,
            Integer teacherId,
            Double teacherLatitude,
            Double teacherLongitude,
            Double allowedDistance) {

        this.id = id;
        this.subject = subject;
        this.attendanceDate = attendanceDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.teacherId = teacherId;
        this.teacherLatitude = teacherLatitude;
        this.teacherLongitude = teacherLongitude;
        this.allowedDistance = allowedDistance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTeacherLatitude() {
        return teacherLatitude;
    }

    public void setTeacherLatitude(Double teacherLatitude) {
        this.teacherLatitude = teacherLatitude;
    }

    public Double getTeacherLongitude() {
        return teacherLongitude;
    }

    public void setTeacherLongitude(Double teacherLongitude) {
        this.teacherLongitude = teacherLongitude;
    }

    public Double getAllowedDistance() {
        return allowedDistance;
    }

    public void setAllowedDistance(Double allowedDistance) {
        this.allowedDistance = allowedDistance;
    }

	

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
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
    
}
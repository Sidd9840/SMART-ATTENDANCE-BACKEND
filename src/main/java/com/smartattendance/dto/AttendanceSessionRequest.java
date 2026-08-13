package com.smartattendance.dto;

public class AttendanceSessionRequest {

    private String subject;
    private Integer teacherId;
    private String lecture;

    private String classType;

    private Double teacherLatitude;

    private Double teacherLongitude;

    private Double allowedDistance;

    public AttendanceSessionRequest() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
}
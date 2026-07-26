package com.smartattendance.dto;

public class DashboardResponse {

    private Long totalStudents;
    private Long totalTeachers;
    private Long totalAttendance;
    private Long present;
    private Long absent;

    public DashboardResponse() {
    }

    public DashboardResponse(Long totalStudents, Long totalTeachers,
            Long totalAttendance, Long present, Long absent) {
        this.totalStudents = totalStudents;
        this.totalTeachers = totalTeachers;
        this.totalAttendance = totalAttendance;
        this.present = present;
        this.absent = absent;
    }

    public Long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Long getTotalTeachers() {
        return totalTeachers;
    }

    public void setTotalTeachers(Long totalTeachers) {
        this.totalTeachers = totalTeachers;
    }

    public Long getTotalAttendance() {
        return totalAttendance;
    }

    public void setTotalAttendance(Long totalAttendance) {
        this.totalAttendance = totalAttendance;
    }

    public Long getPresent() {
        return present;
    }

    public void setPresent(Long present) {
        this.present = present;
    }

    public Long getAbsent() {
        return absent;
    }

    public void setAbsent(Long absent) {
        this.absent = absent;
    }
}
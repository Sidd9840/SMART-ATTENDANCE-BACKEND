package com.smartattendance.dto;

public class AttendancePercentageResponse {

    private long totalClasses;
    private long presentClasses;
    private double percentage;

    public AttendancePercentageResponse() {
    }

    public AttendancePercentageResponse(long totalClasses,
            long presentClasses,
            double percentage) {
        this.totalClasses = totalClasses;
        this.presentClasses = presentClasses;
        this.percentage = percentage;
    }

    public long getTotalClasses() {
        return totalClasses;
    }

    public void setTotalClasses(long totalClasses) {
        this.totalClasses = totalClasses;
    }

    public long getPresentClasses() {
        return presentClasses;
    }

    public void setPresentClasses(long presentClasses) {
        this.presentClasses = presentClasses;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
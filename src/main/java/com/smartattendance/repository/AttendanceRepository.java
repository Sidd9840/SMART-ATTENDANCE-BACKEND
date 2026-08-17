package com.smartattendance.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smartattendance.entity.Attendance;

@Repository
public interface AttendanceRepository
        extends JpaRepository<Attendance, Integer> {

    // -----------------------------------------
    // Dashboard
    // -----------------------------------------

    long countByStatus(String status);

    // -----------------------------------------
    // Teacher Dashboard
    // -----------------------------------------

    long countByTeacherId(Integer teacherId);

    long countByTeacherIdAndStatus(
            Integer teacherId,
            String status);


    // -----------------------------------------
    // Duplicate Attendance Check
    // -----------------------------------------

    Attendance findByStudentIdAndSubjectAndAttendanceDateAndLecture(
            Integer studentId,
            String subject,
            LocalDate attendanceDate,
            String lecture);


    Attendance findByStudentIdAndTeacherIdAndSubjectAndAttendanceDateAndLectureAndClassType(

            Integer studentId,

            Integer teacherId,

            String subject,

            LocalDate attendanceDate,

            String lecture,

            String classType
    );


    // -----------------------------------------
    // Today's Attendance
    // -----------------------------------------

    Attendance findByStudentIdAndAttendanceDate(

            Integer studentId,

            LocalDate attendanceDate
    );


    // -----------------------------------------
    // Attendance Percentage
    // -----------------------------------------

    long countByStudentId(Integer studentId);


    long countByStudentIdAndStatus(

            Integer studentId,

            String status
    );


    // -----------------------------------------
    // Student Attendance
    // -----------------------------------------

    List<Attendance> findByStudentId(Integer studentId);


    // -----------------------------------------
    // Date Wise Report
    // -----------------------------------------

    List<Attendance> findByAttendanceDate(
            LocalDate attendanceDate
    );


    // -----------------------------------------
    // Month & Year Report
    // -----------------------------------------

    @Query("""
           SELECT a
           FROM Attendance a
           WHERE MONTH(a.attendanceDate) = :month
           AND YEAR(a.attendanceDate) = :year
           ORDER BY a.attendanceDate DESC,
                    a.attendanceTime DESC
           """)
    List<Attendance> findByMonthAndYear(

            @Param("month") int month,

            @Param("year") int year
    );


    // -----------------------------------------
    // Teacher + Month + Year Report
    // -----------------------------------------

    @Query("""
           SELECT a
           FROM Attendance a
           WHERE a.teacherId = :teacherId
           AND MONTH(a.attendanceDate) = :month
           AND YEAR(a.attendanceDate) = :year
           ORDER BY a.attendanceDate DESC,
                    a.attendanceTime DESC
           """)
    List<Attendance> findByTeacherAndMonthAndYear(

            @Param("teacherId") Integer teacherId,

            @Param("month") int month,

            @Param("year") int year
    );


    // -----------------------------------------
    // Search By Student Name
    // -----------------------------------------

    List<Attendance> findByStudentNameContainingIgnoreCase(
            String studentName
    );


    // -----------------------------------------
    // Search By Subject
    // -----------------------------------------

    List<Attendance> findBySubjectContainingIgnoreCase(
            String subject
    );


 // -----------------------------------------
 // Teacher + Student + Subject Search
 // + Month + Year
 // -----------------------------------------

 @Query("""
        SELECT a
        FROM Attendance a
        WHERE a.teacherId = :teacherId
        AND LOWER(a.studentName)
        LIKE LOWER(CONCAT('%', :keyword, '%'))
        AND LOWER(a.subject)
        LIKE LOWER(CONCAT('%', :subject, '%'))
        AND MONTH(a.attendanceDate) = :month
        AND YEAR(a.attendanceDate) = :year
        ORDER BY a.attendanceDate DESC,
                 a.attendanceTime DESC
        """)
 List<Attendance> searchAttendance(

         @Param("teacherId") Integer teacherId,

         @Param("keyword") String keyword,

         @Param("subject") String subject,

         @Param("month") int month,

         @Param("year") int year
 );


    // -----------------------------------------
    // Close Attendance Session
    // Find Student Attendance
    // -----------------------------------------

    Attendance findByStudentIdAndAttendanceDateAndSubjectAndLectureAndClassTypeAndTeacherId(

            Integer studentId,

            LocalDate attendanceDate,

            String subject,

            String lecture,

            String classType,

            Integer teacherId
    );


    // -----------------------------------------
    // PDF - Teacher Wise Attendance
    // -----------------------------------------

    List<Attendance> findByTeacherId(Integer teacherId);

}
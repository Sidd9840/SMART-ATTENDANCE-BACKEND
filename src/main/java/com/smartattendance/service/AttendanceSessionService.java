package com.smartattendance.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartattendance.entity.Attendance;
import com.smartattendance.entity.CampusLocation;
import com.smartattendance.entity.AttendanceSession;
import com.smartattendance.entity.Student;
import com.smartattendance.repository.AttendanceRepository;
import com.smartattendance.repository.AttendanceSessionRepository;
import com.smartattendance.repository.CampusLocationRepository;
import com.smartattendance.repository.StudentRepository;
import com.smartattendance.dto.AttendanceSessionRequest;
@Service
public class AttendanceSessionService {

    @Autowired
    private AttendanceSessionRepository attendanceSessionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private CampusLocationRepository campusLocationRepository;
    
    // -----------------------------
    // Start Attendance Session
    // -----------------------------
    public AttendanceSession startSession(AttendanceSessionRequest request) {

        LocalTime now = LocalTime.now();

        boolean allowed = true;
//                (now.isAfter(LocalTime.of(9,0)) && now.isBefore(LocalTime.of(9,15)))
//             || (now.isAfter(LocalTime.of(11,0)) && now.isBefore(LocalTime.of(11,15)))
//             || (now.isAfter(LocalTime.of(14,0)) && now.isBefore(LocalTime.of(14,15)))
//             || (now.isAfter(LocalTime.of(16,50)) && now.isBefore(LocalTime.of(17,20)));
//
//        if (!allowed) {
//            throw new RuntimeException("Attendance can only be started during scheduled time.");
//        }
        AttendanceSession existing =
        		attendanceSessionRepository
        		.findByTeacherIdAndSubjectAndLectureAndClassTypeAndAttendanceDateAndStatus(

        		        request.getTeacherId(),

        		        request.getSubject(),

        		        request.getLecture(),

        		        request.getClassType(),

        		        LocalDate.now(),

        		        "OPEN"

        		);

        if(existing != null){
            throw new RuntimeException("Attendance Session Already Started");
        }

        AttendanceSession session = new AttendanceSession();

        session.setSubject(request.getSubject());
        session.setLecture(request.getLecture());

        session.setClassType(request.getClassType());

        session.setAttendanceDate(LocalDate.now());

        session.setStartTime(LocalTime.now());

        session.setStatus("OPEN");

        session.setTeacherId(request.getTeacherId());
        // Teacher Live Location
     // -------------------------------------
     // Get Admin Campus Geo-Fence
     // -------------------------------------

     CampusLocation campusLocation =
             campusLocationRepository.findById(1).orElse(null);

     if (campusLocation == null) {
         throw new RuntimeException("Campus location is not configured.");
     }

     // Use Admin configured location and radius
     session.setTeacherLatitude(campusLocation.getLatitude());

     session.setTeacherLongitude(campusLocation.getLongitude());

     session.setAllowedDistance(campusLocation.getAllowedDistance());

        return attendanceSessionRepository.save(session);

    }

    // -----------------------------
    // Close Attendance Session
    // -----------------------------
    public AttendanceSession closeSession(
            AttendanceSessionRequest request){
    	
    	AttendanceSession session =
    			attendanceSessionRepository
    			.findByTeacherIdAndSubjectAndLectureAndClassTypeAndAttendanceDateAndStatus(

    			        request.getTeacherId(),
    			        request.getSubject(),
    			        request.getLecture(),
    			        request.getClassType(),
    			        LocalDate.now(),
    			        "OPEN"
    			);

    	if (session == null) {

    	    throw new RuntimeException("No Open Attendance Session Found");

    	}
        // Close Session
        session.setEndTime(LocalTime.now());
        session.setStatus("CLOSED");

        attendanceSessionRepository.save(session);

        // Get All Students
        List<Student> students = studentRepository.findAll();

        for (Student student : students) {

        	Attendance attendance =
        	        attendanceRepository
        	        .findByStudentIdAndAttendanceDateAndSubjectAndLectureAndClassTypeAndTeacherId(

        	                student.getId(),

        	                LocalDate.now(),

        	                session.getSubject(),

        	                session.getLecture(),

        	                session.getClassType(),

        	                session.getTeacherId()

        	        ); 	

            // Attendance not marked → Mark Absent
            if (attendance == null) {

                Attendance absent = new Attendance();

                absent.setStudentId(student.getId());

                absent.setStudentName(student.getName());

                absent.setSubject(session.getSubject());

                absent.setAttendanceDate(LocalDate.now());

                absent.setAttendanceTime(LocalTime.now());

                absent.setStatus("Absent");

                absent.setTeacherId(session.getTeacherId());

                absent.setLatitude(null);

                absent.setLongitude(null);

                absent.setDistance(null);

                attendanceRepository.save(absent);

            }

        }

        return session;

    }

    public AttendanceSession getCurrentSession(

            Integer teacherId,

            String subject,

            String lecture,

            String classType) {

        return attendanceSessionRepository
                .findByTeacherIdAndSubjectAndLectureAndClassTypeAndAttendanceDateAndStatus(

                        teacherId,

                        subject,

                        lecture,

                        classType,

                        LocalDate.now(),

                        "OPEN");

    } 
}

        

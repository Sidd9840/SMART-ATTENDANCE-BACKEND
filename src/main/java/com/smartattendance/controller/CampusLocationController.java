package com.smartattendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.smartattendance.entity.CampusLocation;
import com.smartattendance.service.CampusLocationService;

@RestController
@RequestMapping("/campus")
@CrossOrigin("*")
public class CampusLocationController {

    @Autowired
    private CampusLocationService campusLocationService;

    // -----------------------------
    // Save Campus
    // -----------------------------
    @PostMapping
    public CampusLocation saveCampus(
            @RequestBody CampusLocation campusLocation){

        return campusLocationService.saveCampus(campusLocation);

    }

    // -----------------------------
    // Get Campus
    // -----------------------------
    @GetMapping
    public CampusLocation getCampus(){

        return campusLocationService.getCampus();

    }

    // -----------------------------
    // Update Campus
    // -----------------------------
    @PutMapping
    public CampusLocation updateCampus(
            @RequestBody CampusLocation campusLocation){

        return campusLocationService.updateCampus(campusLocation);

    }

}
package com.smartattendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartattendance.entity.CampusLocation;
import com.smartattendance.repository.CampusLocationRepository;

@Service
public class CampusLocationService {
    @Autowired
    private CampusLocationRepository campusLocationRepository;
    
    	

    // -----------------------------
    // Save Campus Location
    // -----------------------------
    public CampusLocation saveCampus(CampusLocation campusLocation) {

        return campusLocationRepository.save(campusLocation);
    }
    // -----------------------------
    // Get Campus Location
    // -----------------------------
    public CampusLocation getCampus() {

        return campusLocationRepository.findById(1).orElse(null);
    }

    // -----------------------------
    // Update Campus Location
    // -----------------------------
    public CampusLocation updateCampus(CampusLocation campusLocation) {

        CampusLocation existing =
                campusLocationRepository.findById(1).orElse(null);

        if (existing == null) {

            return null;

        }
        existing.setCampusName(campusLocation.getCampusName());
        existing.setLatitude(campusLocation.getLatitude());
        existing.setLongitude(campusLocation.getLongitude());
        existing.setAllowedDistance(campusLocation.getAllowedDistance());
        return campusLocationRepository.save(existing);
    }
}
package com.smartattendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartattendance.entity.CampusLocation;

@Repository
public interface CampusLocationRepository
        extends JpaRepository<CampusLocation, Integer> {

}
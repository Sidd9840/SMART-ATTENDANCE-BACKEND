package com.smartattendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartattendance.dto.DashboardResponse;
import com.smartattendance.service.DashboardService;

@RestController
@CrossOrigin("*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard")

    public DashboardResponse getDashboard() {

        return dashboardService.getDashboardData();

    }

}
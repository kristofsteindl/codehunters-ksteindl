package com.ksteindl.jpademo.service;

import org.springframework.stereotype.Service;

@Service
public class CarReportService {

    private final CarService carService;

    public CarReportService(CarService carService) {
        this.carService = carService;
    }
}

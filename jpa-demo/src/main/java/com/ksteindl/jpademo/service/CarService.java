package com.ksteindl.jpademo.service;

import com.ksteindl.jpademo.repository.CarRepositoryV1;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepositoryV1 carRepositoryV1;

    public CarService(CarRepositoryV1 carRepositoryV1) {
        this.carRepositoryV1 = carRepositoryV1;
    }
}

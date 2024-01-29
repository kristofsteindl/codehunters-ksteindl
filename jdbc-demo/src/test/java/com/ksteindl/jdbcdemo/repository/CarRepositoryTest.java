package com.ksteindl.jdbcdemo.repository;

import com.ksteindl.jdbcdemo.service.model.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @BeforeEach
    @AfterEach
    public void init() {
        carRepository.deleteAll();
    }

    @Test
    public void testFindAllCars() {
        // given, when
        List<Car> foundCars = carRepository.findAllCars();
        // then
        assertEquals(0, foundCars.size());
    }

    @Test
    public void testsaveCars() {
        // given
        Car car = new Car(2, "abc");
        // when
        carRepository.save(car);
        // then
        assertEquals(1, carRepository.findAllCars().size());
        Car found = carRepository.findAllCars().get(0);
        System.out.println(found);

        assertAll(() -> assertEquals(2, found.getId()), 
                () -> assertEquals("abc", found.getPlate()));
    }

}
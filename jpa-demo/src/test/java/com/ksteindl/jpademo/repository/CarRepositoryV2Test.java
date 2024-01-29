package com.ksteindl.jpademo.repository;

import com.ksteindl.jpademo.repository.entity.CarEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CarRepositoryV2Test {

    @Autowired
    private CarRepositoryV2 carRepositoryV2;

    @Test
    void testFindAll() {
        // given
        // when
        var cars = carRepositoryV2.findAll();
        // then
        assertEquals(1, cars.size());
    }

    @Test
    void testSave() {
        // given
        CarEntity carEntity = new CarEntity();
        carEntity.setPlate("abc");
        // when
        var persisted = carRepositoryV2.saveCar(carEntity);
        // then
        assertAll(
                () -> assertEquals("abc", persisted.getPlate()), 
                () -> assertNotNull(persisted.getId()));
    }

}
package com.ksteindl.jpademo.repository;

import com.ksteindl.jpademo.repository.entity.CarEntity;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(CarEntity.class)
public class CarEntityRestDataHandler {

    @HandleBeforeCreate
    public void validatePlateBeforeCreate(CarEntity carEntity) {
        if (carEntity.getPlate() == null || carEntity.getPlate().isEmpty()) {
            throw new RuntimeException("Plate is empty");
        }
        
    }
    
}

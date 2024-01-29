package com.ksteindl.jpademo.repository;

import com.ksteindl.jpademo.repository.entity.CarEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepositoryV2 {
    // HOMEWORK - save method, Transactional, ::persist method (update = ::merge)
    private final EntityManager entityManager;
    
    public CarRepositoryV2(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    
    public List<CarEntity> findAll() {
        return entityManager.createQuery("select c from CarEntity c").getResultList();
    }
    
    @Transactional
    public void deleteAll() {
        entityManager.createQuery("delete from CarEntity").executeUpdate();
    }
    
    @Transactional
    public CarEntity saveCar(CarEntity carEntity) {
        if (carEntity.getId() != null) {
            throw new RuntimeException("Id is not null");
        }
        entityManager.persist(carEntity);
        return carEntity;
    }
    
}

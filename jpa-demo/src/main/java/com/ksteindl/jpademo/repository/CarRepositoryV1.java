package com.ksteindl.jpademo.repository;

import com.ksteindl.jpademo.repository.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@RepositoryRestResource(path = "cars")
public interface CarRepositoryV1 extends JpaRepository<CarEntity, Integer> {
    
    @Query("select c from CarEntity c where c.plate in :plates")
    List<CarEntity> findCarsByPlateNumber(@Param("plates") Set<String> plateNumbers);

    @RestResource(path = "findByPlateIn")
    List<CarEntity> findByPlateIn(Collection<String> plateNumbers);
    
    @Modifying // If we modify something: delete, insert, update
    @Query("delete from CarEntity c where c.id = :id")
    void deleteCarById(Integer id);

    @RestResource(path = "plate", rel = "plate")
    List<CarEntity> findByPlate(String plate);
    
}

package com.ksteindl.jpademo.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
public class CarEntity {
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String plate;
    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String modifiedBy;
}

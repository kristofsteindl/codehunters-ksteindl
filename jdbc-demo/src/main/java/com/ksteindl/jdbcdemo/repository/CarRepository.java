package com.ksteindl.jdbcdemo.repository;

import com.ksteindl.jdbcdemo.service.model.Car;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository {

    private final JdbcTemplate jdbcTemplate;

    public CarRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Car> findAllCars() {
        return jdbcTemplate.query(
                "select * from cars",
                (record, index) -> new Car(record.getInt("id"), record.getString("plate")));
    }

    public void save(Car car) {
        var sql = "insert into cars (id, plate) values (?, ?);";
        jdbcTemplate.update(sql, car.getId(), car.getPlate());
    }

    public void deleteAll() {
        jdbcTemplate.execute("delete from cars");
    }
}

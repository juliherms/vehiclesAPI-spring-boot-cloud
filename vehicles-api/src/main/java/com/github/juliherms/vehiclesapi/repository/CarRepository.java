package com.github.juliherms.vehiclesapi.repository;

import com.github.juliherms.vehiclesapi.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This class responsible to access entity car
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}

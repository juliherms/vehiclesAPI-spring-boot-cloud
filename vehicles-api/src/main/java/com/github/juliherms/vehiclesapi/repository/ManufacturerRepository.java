package com.github.juliherms.vehiclesapi.repository;

import com.github.juliherms.vehiclesapi.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This class responsible to access entity Manufacturer
 */
@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {

}

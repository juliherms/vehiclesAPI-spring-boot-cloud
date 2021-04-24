package com.github.juliherms.pricingservice.repository;

import com.github.juliherms.pricingservice.model.Price;
import org.springframework.data.repository.CrudRepository;

/**
 * This class responsible to access price
 */
public interface PriceRepository extends CrudRepository <Price, Long> {

}

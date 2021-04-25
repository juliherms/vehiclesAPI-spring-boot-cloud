package com.github.juliherms.pricingservice.repository;

import com.github.juliherms.pricingservice.model.Price;
import org.springframework.data.repository.CrudRepository;

/**
 * This class responsible to access price
 * In this example was used spring data rest, this feature implements automatic PriceResource
 */
public interface PriceRepository extends CrudRepository <Price, Long> {

}

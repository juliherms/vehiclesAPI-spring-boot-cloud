package com.github.juliherms.booglemaps.controller;

import com.github.juliherms.booglemaps.model.Address;
import com.github.juliherms.booglemaps.repository.MockAddressRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class responsible to provide maps. Simulate external service
 */
@RestController
@RequestMapping("/maps")
public class MapsController {

    /**
     * Provide address by location
     * @param lat latitude
     * @param lon longitude
     * @return Address
     */
    @GetMapping
    public Address get(@RequestParam Double lat, @RequestParam Double lon) {

        return MockAddressRepository.getRandom();
    }
}


package com.github.juliherms.vehiclesapi.clients.maps;

import com.github.juliherms.vehiclesapi.model.Location;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Component
public class MapsClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapsClient.class);

    private final WebClient client;
    private final ModelMapper mapper;

    /**
     * Dependency injection by constructor
     * @param maps
     * @param mapper
     */
    public MapsClient(WebClient maps,
                      ModelMapper mapper) {

        this.client = maps;
        this.mapper = mapper;
    }

    /**
     * Method responsiblet o get address from location by external service
     * @param location
     * @return
     */
    public Location getAddress(Location location) {

        try {
            //call external service
            Address address = client
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/maps/")
                            .queryParam("lat", location.getLat())
                            .queryParam("lon", location.getLon())
                            .build()
                    )
                    .retrieve().bodyToMono(Address.class).block();

            //convert objects
            mapper.map(Objects.requireNonNull(address), location);

            return location;

        } catch (Exception e) {
            LOGGER.warn("Map service is down");
            return location;
        }
    }

}

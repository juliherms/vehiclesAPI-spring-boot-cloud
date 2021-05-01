package com.github.juliherms.vehiclesapi.clients.prices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Class responsible to implement call external service about price
 */
@Component
public class PriceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(PriceClient.class);
    private final WebClient client;

    /**
     * Dependency injection with constructor
     * @param pricing
     */
    public PriceClient(WebClient pricing) {
        this.client = pricing;
    }

    /**
     * Method responsible to call prices by car id
     * @param vehicleId
     * @return
     */
    public String getPrice(Long vehicleId) {

        try {
            //call get method - sync method
            Price price = client
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("prices/".concat(String.valueOf(vehicleId)))
                            .build()
                    )
                    .retrieve().bodyToMono(Price.class).block();

            return String.format("%s %s", price.getCurrency(), price.getPrice());

        } catch (Exception e) {
            LOGGER.error("Unexpected error retrieving price for vehicle {}", vehicleId, e);
        }
        return "(consult price)";
    }
}

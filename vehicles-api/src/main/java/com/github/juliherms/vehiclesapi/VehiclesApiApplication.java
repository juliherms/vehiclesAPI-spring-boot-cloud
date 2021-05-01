package com.github.juliherms.vehiclesapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;

/**
 * This class responsible to represents main application
 */
@SpringBootApplication
@EnableJpaAuditing //audit
public class VehiclesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehiclesApiApplication.class, args);
	}

	/**
	 * Create modelMapper in the context
	 * @return
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/**
	 * Method responsible to setup maps endpoint(external service)
	 * @param endpoint
	 * @return
	 */
	@Bean(name="maps")
	public WebClient webClientMaps(@Value("${maps.endpoint}") String endpoint) {
		return WebClient.create(endpoint);
	}

	/**
	 * Method responsible to setup pricing endpoint(external service)
	 * @param endpoint
	 * @return
	 */
	@Bean(name="pricing")
	public WebClient webClientPricing(@Value("${pricing.endpoint}") String endpoint) {
		return WebClient.create(endpoint);
	}
}

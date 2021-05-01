package com.github.juliherms.vehiclesapi;

import com.github.juliherms.vehiclesapi.model.Manufacturer;
import com.github.juliherms.vehiclesapi.repository.ManufacturerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
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

	@Bean
	CommandLineRunner initDatabase(ManufacturerRepository repo) {
		return args -> {
			repo.save(new Manufacturer(100, "Chevrolet"));
			repo.save(new Manufacturer(101, "Fiat"));
			repo.save(new Manufacturer(102, "Ford"));
			repo.save(new Manufacturer(103, "Wolkswagen"));
			repo.save(new Manufacturer(104, "Renault"));
		};
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

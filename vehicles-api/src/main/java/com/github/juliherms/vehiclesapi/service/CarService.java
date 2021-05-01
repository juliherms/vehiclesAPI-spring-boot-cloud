package com.github.juliherms.vehiclesapi.service;

import com.github.juliherms.vehiclesapi.clients.maps.MapsClient;
import com.github.juliherms.vehiclesapi.clients.prices.PriceClient;
import com.github.juliherms.vehiclesapi.exceptions.CarNotFoundException;
import com.github.juliherms.vehiclesapi.model.Car;
import com.github.juliherms.vehiclesapi.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class responsible to implement business logic about cars
 */
@Service
public class CarService {

    private CarRepository repository;
    private PriceClient priceClient;
    private MapsClient mapsClient;

    /**
     * Injection by contructor
     * @param repository
     * @param priceClient
     * @param mapsClient
     */
    public CarService(CarRepository repository, PriceClient priceClient, MapsClient mapsClient) {
        this.repository = repository;
        this.priceClient = priceClient;
        this.mapsClient = mapsClient;
    }

    /**
     * Method responsible to list all cars
     * @return
     */
    public List<Car> list() {

        //find all cars
        List<Car> carList = repository.findAll();

        carList.forEach(car -> {
            //call external service
            car.setPrice(priceClient.getPrice(car.getId()));
            car.setLocation(mapsClient.getAddress(car.getLocation()));
        });

        return carList;
    }

    /**
     * Method responsible to find car by id
     * @param id
     * @return
     */
    public Car findById(Long id) {

        //check exist car
        Car car = repository.findById(id)
                .orElseThrow(CarNotFoundException::new);

        //setPrice
        car.setPrice(priceClient.getPrice(id));

        //setLocation
        car.setLocation(mapsClient.getAddress(car.getLocation()));

        return car;
    }

    /**
     * Method responsible to save and update car
     * @param car
     * @return
     */
    public Car save(Car car) {

        if (car.getId() != null) {
            //find and update
            return repository.findById(car.getId())
                    .map(carToBeUpdated -> {
                        carToBeUpdated.setDetails(car.getDetails());
                        carToBeUpdated.setLocation(car.getLocation());
                        return repository.save(carToBeUpdated);
                    }).orElseThrow(CarNotFoundException::new);
        }
        return repository.save(car);
    }

    /**
     * Method responsible to delete car by id
     * @param id
     */
    public void delete(Long id) {

        Car car = repository.findById(id)
                .orElseThrow(CarNotFoundException::new);
        repository.deleteById(id);
    }
}

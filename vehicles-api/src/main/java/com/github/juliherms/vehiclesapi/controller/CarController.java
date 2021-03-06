package com.github.juliherms.vehiclesapi.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.github.juliherms.vehiclesapi.controller.assembler.CarResourceAssembler;
import com.github.juliherms.vehiclesapi.model.Car;
import com.github.juliherms.vehiclesapi.service.CarService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class responsible to represent cars resource
 */
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService service;
    private final CarResourceAssembler assembler;

    /**
     * Dependency injction by constructor
     * @param carService
     * @param assembler
     */
    CarController(CarService carService, CarResourceAssembler assembler) {
        this.service = carService;
        this.assembler = assembler;
    }


    /**
     * Methdo responsible to list cars
     * @return
     */
    @GetMapping
    public Resources<Resource<Car>> list() {

        List<Resource<Car>> resources = service.list().stream().map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(resources,
                linkTo(methodOn(CarController.class).list()).withSelfRel());
    }

    /**
     * Method responsible to create a car
     * @param car
     * @return
     * @throws URISyntaxException
     */
    @PostMapping
    ResponseEntity<?> post(@Valid @RequestBody Car car) throws URISyntaxException {

        Resource<Car> resource = assembler.toResource(service.save(car));
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    /**
     * Method responsible to update car
     * @param id
     * @param car
     * @return
     */
    @PutMapping("/{id}")
    ResponseEntity<?> put(@PathVariable Long id, @Valid @RequestBody Car car) {
        car.setId(id);
        Resource<Car> resource = assembler.toResource(service.save(car));
        return ResponseEntity.ok(resource);
    }

    /**
     * Method responsbile to find car by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Resource<Car> get(@PathVariable Long id) {
        return assembler.toResource(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
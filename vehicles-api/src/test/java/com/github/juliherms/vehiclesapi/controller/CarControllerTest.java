package com.github.juliherms.vehiclesapi.controller;

import com.github.juliherms.vehiclesapi.clients.maps.MapsClient;
import com.github.juliherms.vehiclesapi.clients.prices.PriceClient;
import com.github.juliherms.vehiclesapi.model.Car;
import com.github.juliherms.vehiclesapi.model.Details;
import com.github.juliherms.vehiclesapi.model.Location;
import com.github.juliherms.vehiclesapi.model.Manufacturer;
import com.github.juliherms.vehiclesapi.model.enums.Condition;
import com.github.juliherms.vehiclesapi.service.CarService;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.xml.soap.Detail;
import java.net.URI;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<Car> json;

    @MockBean
    private CarService carService;

    @MockBean
    private PriceClient priceClient;

    @MockBean
    private MapsClient mapsClient;

    /**
     * Method responsible to set pre requisite to test
     */
    @Before
    public void setup() {
        Car car = getCar();
        car.setId(1L);

        //mocks
        given(carService.save(any())).willReturn(car); //simulate save car
        given(carService.findById(any())).willReturn(car); //simulate find by id
        given(carService.list()).willReturn(Collections.singletonList(car)); //simulate to list car
    }

    /**
     * Method responsible to create a car
     * @throws Exception
     */
    @Test
    public void testCreateCarSuccess() throws Exception {

        // step 1 - get car model
        Car car = getCar();
        // step 2 - call resource endpoints
        mvc.perform(
                post(new URI("/cars"))
                        .content(json.write(car).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }

    /**
     * Method responsible to test list of cars
     * @throws Exception
     */
    @Test
    public void testListCarsSuccess() throws Exception {

        //step 1 - model car for validation
        Car car = getCar();
        //step 2 - call car resources
        mvc.perform(get("/cars")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.carList", hasSize(1))) //check lenght
                .andExpect(jsonPath("$._embedded.carList[0].id").value(1)); //check value
    }

    @Test
    public void testDeleteCarSuccess() throws Exception {

        mvc.perform(
                delete(new URI("/cars/1"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent());
    }

    /**
     * Method responsible to create model car for validation
     * @return
     */
    private Car getCar() {

        Car car = new Car();
        Manufacturer manufacturer = new Manufacturer(101, "Fiat");

        car.setLocation(new Location(40.730610, -73.935242));

        Details details = new Details();
        details.setManufacturer(manufacturer);
        details.setModel("Cronos");
        details.setMileage(26000);
        details.setExternalColor("White");
        details.setBody("Sedan");
        details.setEngine("1.8L V16");
        details.setFuelType("Gasoline");
        details.setModelYear(2018);
        details.setProductionYear(2018);
        details.setNumberOfDoors(4);

        car.setDetails(details);
        car.setCondition(Condition.USED);

        return car;
    }
}

package com.github.juliherms.pricingservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.juliherms.pricingservice.model.Price;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.net.URI;

import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * This class responsible to test pricing endpoints
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PricingServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private MockMvc mvc;

	@Test
	public void contextLoads() {
	}

	/**
	 * Method responsible to test prices endpoint
	 * Expected http status ok
	 * @throws Exception
	 */
	@Test
	public void testListPricesSuccess() throws Exception {

		//call prices endpoint
		mvc.perform(get("/prices/")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());

	}

	/**
	 * Method responsible to test find price by id
	 * @throws Exception
	 */
	@Test
	public void testGetPriceSuccess() throws Exception {

		mvc.perform(get("/prices/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
	}

	/**
	 * This method responsible to test create price
	 * @throws Exception
	 */
	@Test
	public void testCreatePriceSuccess() throws Exception {

		//step 1 - prepare information
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Price price = getPrice();
		price.setId(1L);

		//step 2 - convert to json
		String json = ow.writeValueAsString(price);

		//step 3 - call method POST
		mvc.perform(
				post(new URI("/prices"))
						.content(json)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isCreated());
	}

	private Price getPrice() {
		Price price = new Price();
		price.setCurrency("BRL");
		price.setPrice(new BigDecimal(3000));
		return price;
	}
}

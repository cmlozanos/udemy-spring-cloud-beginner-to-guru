package cmls.spring.msscbrewery.web.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import cmls.spring.msscbrewery.web.model.Beer;
import cmls.spring.msscbrewery.web.model.Customer;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ContextConfiguration(classes = { BreweryClient.class, RestTemplate.class })
@TestPropertySource("/application.properties")
@Slf4j
public class BreweryClientTests {

	@Autowired
	BreweryClient client;

	@Test
	public void testFindBeerById() throws Exception {
		// given
		UUID id = UUID.fromString("3166f218-afe7-4a3e-9da7-042349927f7e");

		// when
		Beer beer = client.findBeerById(id);

		// then
		assertNotNull(beer);
	}

	@Test
	public void testSaveBeer() throws Exception {
		// given
		Beer beer = Beer.builder().id(UUID.randomUUID()).name("new beer").build();

		// when
		URI uri = client.saveBeer(beer);

		// then
		assertNotNull(uri);
		log.info(uri.toString());
	}

	@Test
	public void testUpdateBeer() throws Exception {
		// given
		UUID id = UUID.fromString("3166f218-afe7-4a3e-9da7-042349927f7e");
		Beer beer = Beer.builder().id(id).name("updated beer").build();

		// when
		client.updateBeer(id, beer);

		// then
		Beer beerUpdated = client.findBeerById(id);
		assertNotNull(beerUpdated);
		assertEquals(beer.getId(), beerUpdated.getId());
		assertEquals(beer.getName(), beerUpdated.getName());
	}

	@Test
	public void testDeleteBeer() throws Exception {
		// given
		UUID id = UUID.fromString("3166f218-afe7-4a3e-9da7-042349927f7e");

		// when
		client.deleteBeer(id);

		// then
		try {
			client.findBeerById(id);
		} catch (HttpClientErrorException hcee) {
			assertEquals(hcee.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
	
	@Test
	public void testFindCustomerById() throws Exception {
		// given
		UUID id = UUID.fromString("3166f218-afe7-4a3e-9da7-042349927f7e");
		
		// when
		Customer customer = client.findCustomerById(id);
		
		// then
		assertNotNull(customer);
	}
	
	@Test
	public void testSaveCustomer() throws Exception {
		// given
		Customer customer = Customer.builder().id(UUID.randomUUID()).name("new customer").build();
		
		// when
		URI uri = client.saveCustomer(customer);
		
		// then
		assertNotNull(uri);
		log.info(uri.toString());
	}
	
	@Test
	public void testUpdateCustomer() throws Exception {
		// given
		UUID id = UUID.fromString("3166f218-afe7-4a3e-9da7-042349927f7e");
		Customer customer = Customer.builder().id(id).name("updated beer").build();
		
		// when
		client.updateCustomer(id, customer);
		
		// then
		Customer customerUpdated = client.findCustomerById(id);
		assertNotNull(customerUpdated);
		assertEquals(customer.getId(), customerUpdated.getId());
		assertEquals(customer.getName(), customerUpdated.getName());
	}
	
	@Test
	public void testDeleteCustomer() throws Exception {
		// given
		UUID id = UUID.fromString("3166f218-afe7-4a3e-9da7-042349927f7e");
		
		// when
		client.deleteCustomer(id);
		
		// then
		try {
			client.findCustomerById(id);
		} catch (HttpClientErrorException hcee) {
			assertEquals(hcee.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

}

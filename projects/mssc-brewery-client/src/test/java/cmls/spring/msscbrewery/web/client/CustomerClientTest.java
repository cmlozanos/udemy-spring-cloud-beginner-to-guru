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

import cmls.spring.msscbrewery.web.model.Customer;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ContextConfiguration(classes = { CustomerClient.class, RestTemplate.class })
@TestPropertySource("/application.properties")
@Slf4j
public class CustomerClientTest {

	@Autowired
	CustomerClient client;

	@Test
	public void testFindCustomerById() throws Exception {
		// given
		UUID id = UUID.fromString("3166f218-afe7-4a3e-9da7-042349927f7e");
		
		// when
		Customer customer = client.findById(id);
		
		// then
		assertNotNull(customer);
	}
	
	@Test
	public void testSaveCustomer() throws Exception {
		// given
		Customer customer = Customer.builder().id(UUID.randomUUID()).name("new customer").build();
		
		// when
		URI uri = client.save(customer);
		
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
		client.update(id, customer);
		
		// then
		Customer customerUpdated = client.findById(id);
		assertNotNull(customerUpdated);
		assertEquals(customer.getId(), customerUpdated.getId());
		assertEquals(customer.getName(), customerUpdated.getName());
	}
	
	@Test
	public void testDeleteCustomer() throws Exception {
		// given
		UUID id = UUID.fromString("3166f218-afe7-4a3e-9da7-042349927f7e");
		
		// when
		client.delete(id);
		
		// then
		try {
			client.findById(id);
		} catch (HttpClientErrorException hcee) {
			assertEquals(hcee.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

}

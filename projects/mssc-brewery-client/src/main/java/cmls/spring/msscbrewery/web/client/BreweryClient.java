package cmls.spring.msscbrewery.web.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import cmls.spring.msscbrewery.web.model.Beer;
import cmls.spring.msscbrewery.web.model.Customer;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BreweryClient {
	public final String BEERS_PATH_V1 = "/api/v1/beers/";
	public final String CUSTOMERS_PATH_V1 = "/api/v1/customers/";
	
	@Value("${sfg.brewery.apihost}")
	private String apiHost;
	
	@Autowired
	private RestTemplate restTemplate;

	public Beer findBeerById(UUID id) {
		String url = apiHost + BEERS_PATH_V1 + id.toString();
		log.info("url: " + url);
		return restTemplate.getForObject(url, Beer.class);
	}

	public URI saveBeer(Beer beer) {
		String url = apiHost + BEERS_PATH_V1;
		log.info("url: " + url);
		return restTemplate.postForLocation(url, beer);
	}
	
	public void updateBeer(UUID id, Beer beer) {
		String url = apiHost + BEERS_PATH_V1 + id.toString();
		log.info("url: " + url);
		restTemplate.put(url, beer);
	}

	public void deleteBeer(UUID id) {
		String url = apiHost + BEERS_PATH_V1 + id.toString();
		log.info("url: " + url);
		restTemplate.delete(url);
	}

	public Customer findCustomerById(UUID id) {
		String url = apiHost + CUSTOMERS_PATH_V1 + id.toString();
		log.info("url: " + url);
		return restTemplate.getForObject(url, Customer.class);
	}

	public URI saveCustomer(Customer customer) {
		String url = apiHost + CUSTOMERS_PATH_V1;
		log.info("url: " + url);
		return restTemplate.postForLocation(url, customer);
	}

	public void updateCustomer(UUID id, Customer customer) {
		String url = apiHost + CUSTOMERS_PATH_V1 + id.toString();
		log.info("url: " + url);
		restTemplate.put(url, customer);
	}

	public void deleteCustomer(UUID id) {
		String url = apiHost + CUSTOMERS_PATH_V1 + id.toString();
		log.info("url: " + url);
		restTemplate.delete(url);
	}

}

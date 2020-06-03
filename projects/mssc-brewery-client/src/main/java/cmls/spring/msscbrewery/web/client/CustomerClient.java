package cmls.spring.msscbrewery.web.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import cmls.spring.msscbrewery.web.model.Customer;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomerClient {
	public final String CUSTOMERS_PATH_V1 = "/api/v1/customers/";
	
	@Value("${sfg.brewery.apihost}")
	private String apiHost;
	
	@Autowired
	private RestTemplate restTemplate;

	public Customer findById(UUID id) {
		String url = apiHost + CUSTOMERS_PATH_V1 + id.toString();
		log.info("url: " + url);
		return restTemplate.getForObject(url, Customer.class);
	}

	public URI save(Customer customer) {
		String url = apiHost + CUSTOMERS_PATH_V1;
		log.info("url: " + url);
		return restTemplate.postForLocation(url, customer);
	}

	public void update(UUID id, Customer customer) {
		String url = apiHost + CUSTOMERS_PATH_V1 + id.toString();
		log.info("url: " + url);
		restTemplate.put(url, customer);
	}

	public void delete(UUID id) {
		String url = apiHost + CUSTOMERS_PATH_V1 + id.toString();
		log.info("url: " + url);
		restTemplate.delete(url);
	}

}

package cmls.spring.msscbrewery.web.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import cmls.spring.msscbrewery.web.model.Beer;

@Component
public class BeerClient {
	public final String BEERS_PATH_V1 = "/api/v1/beers/";
	
	@Value("${sfg.brewery.apihost}")
	private String apiHost;
	
	@Autowired
	private RestTemplate restTemplate;

	public Beer findById(UUID id) {
		String url = apiHost + BEERS_PATH_V1 + id.toString();
		return restTemplate.getForObject(url, Beer.class);
	}

	public URI save(Beer beer) {
		String url = apiHost + BEERS_PATH_V1;
		return restTemplate.postForLocation(url, beer);
	}
	
	public void update(UUID id, Beer beer) {
		String url = apiHost + BEERS_PATH_V1 + id.toString();
		restTemplate.put(url, beer);
	}

	public void delete(UUID id) {
		String url = apiHost + BEERS_PATH_V1 + id.toString();
		restTemplate.delete(url);
	}
}

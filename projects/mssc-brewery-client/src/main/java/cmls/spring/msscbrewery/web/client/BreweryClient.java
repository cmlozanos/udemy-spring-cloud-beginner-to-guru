package cmls.spring.msscbrewery.web.client;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import cmls.spring.msscbrewery.web.model.Beer;

@ConfigurationProperties(prefix = "sfg.brewery", ignoreInvalidFields = false)
@Component
public class BreweryClient {
	public final String BEER_PATH_V1 = "/api/v1/beers/";
	
	@Value("${sfg.brewery.apihost}")
	private String apiHost;
	
	private RestTemplate restTemplate;

	public BreweryClient(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	public void setApiHost(String apiHost) {
		this.apiHost = apiHost;
	}

	public Beer findById(UUID id) {
		String url = apiHost + BEER_PATH_V1 + id.toString();
		return restTemplate.getForObject(url, Beer.class);
	}

}

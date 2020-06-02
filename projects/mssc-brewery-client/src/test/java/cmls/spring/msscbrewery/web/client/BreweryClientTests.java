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
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ContextConfiguration(classes = { BreweryClient.class, RestTemplate.class })
@TestPropertySource("/application.properties")
@Slf4j
public class BreweryClientTests {

	@Autowired
	BreweryClient client;

	@Test
	public void testFindById() throws Exception {
		// given
		UUID id = UUID.fromString("3166f218-afe7-4a3e-9da7-042349927f7e");

		// when
		Beer beer = client.findById(id);

		// then
		assertNotNull(beer);
	}

	@Test
	public void testSave() throws Exception {
		// given
		Beer beer = Beer.builder().id(UUID.randomUUID()).name("new beer").build();

		// when
		URI uri = client.save(beer);

		// then
		assertNotNull(uri);
		log.info(uri.toString());
	}

	@Test
	public void testUpdate() throws Exception {
		// given
		UUID id = UUID.fromString("3166f218-afe7-4a3e-9da7-042349927f7e");
		Beer beer = Beer.builder().id(id).name("updated beer").build();

		// when
		client.update(id, beer);

		// then
		Beer beerUpdated = client.findById(id);
		assertNotNull(beerUpdated);
		assertEquals(beer.getId(), beerUpdated.getId());
		assertEquals(beer.getName(), beerUpdated.getName());
	}

	@Test
	public void testDelete() throws Exception {
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

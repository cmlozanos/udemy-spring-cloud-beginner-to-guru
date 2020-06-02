package cmls.spring.msscbrewery.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import cmls.spring.msscbrewery.web.model.Beer;

@SpringBootTest
@ContextConfiguration(classes = {BreweryClient.class, RestTemplate.class})
@TestPropertySource("/application.properties")
public class BreweryClientTests {

	@Autowired
	BreweryClient client;
	
	@Test
	public void testFindById() throws Exception {
		//given
		UUID id = UUID.fromString("3166f218-afe7-4a3e-9da7-042349927f7e");
		
		
		//when
		Beer beer = client.findById(id);
		
		//then
		assertNotNull(beer);
	}

}

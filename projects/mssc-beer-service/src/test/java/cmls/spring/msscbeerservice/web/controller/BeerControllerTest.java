package cmls.spring.msscbeerservice.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cmls.spring.msscbeerservice.web.model.Beer;
import cmls.spring.msscbeerservice.web.model.BeerStyleEnum;

@WebMvcTest(BeerController.class)
public class BeerControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void givenAnIdWhenFindByIdThenShouldReturnOkAndEntity() throws Exception {
		// given
		String id = UUID.randomUUID().toString();

		// when
		mockMvc.perform(get("/api/v1/beers/" + id))

				// then
				.andExpect(status().isOk());
	}

	@Test
	public void givenAnEntityWhenSaveThenShouldReturnCreatedAndLocation() throws Exception {
		// given
		Beer beer = Beer.builder().name("random name").style(BeerStyleEnum.GOSE).created(OffsetDateTime.now()).upc(1L)
				.build();
		String beerJSON = objectMapper.writeValueAsString(beer);

		// when
		mockMvc.perform(post("/api/v1/beers").content(beerJSON).contentType(MediaType.APPLICATION_JSON))

				// then
				.andExpect(status().isCreated()).andExpect(header().exists("Location"));
	}

	@Test
	public void givenAnIdAndAnEntityWhenUpdateThenShouldReturnNoContent() throws Exception {
		// given
		String id = UUID.randomUUID().toString();
		Beer beer = Beer.builder().name("random name").style(BeerStyleEnum.GOSE).created(OffsetDateTime.now()).upc(1L)
				.build();
		String beerJSON = objectMapper.writeValueAsString(beer);

		// when
		mockMvc.perform(put("/api/v1/beers/" + id).content(beerJSON).contentType(MediaType.APPLICATION_JSON))

				// then
				.andExpect(status().isNoContent());
	}
}

package cmls.spring.msscbrewery.web.controller;

import static org.hamcrest.CoreMatchers.is;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cmls.spring.msscbrewery.services.CustomerService;
import cmls.spring.msscbrewery.web.model.Customer;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
	
	@MockBean
	CustomerService service;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;
	
	Customer validEntity;
	String validEntityJson;
	
	@BeforeEach
	public void setup() throws JsonProcessingException {
		validEntity = Customer.builder().id(UUID.randomUUID()).name("valid beer").build();
		validEntityJson = mapper.writeValueAsString(validEntity);
	}
	
	@Test
	public void givenGetRequestToFindAllWhenGetResourcesThenShouldReturnAList() throws Exception {
		//given
		BDDMockito.given(service.findAll()).willReturn(Stream.of(validEntity).collect(Collectors.toList()));
		String url = "/api/v1/customers";
		
		//when
		ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get(url));

		//then
		perform.andExpect(MockMvcResultMatchers.status().isOk());
		perform.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
		perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", is(validEntity.getId().toString())));
		perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].name", is(validEntity.getName())));
		perform.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void givenGetRequestToFindByIdWhenGetResourceThenShouldReturnAEntity() throws Exception {
		//given
		BDDMockito.given(service.findById(validEntity.getId())).willReturn(validEntity);
		String url = "/api/v1/customers/"+validEntity.getId().toString();
		
		//when
		ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get(url));
		
		//then
		perform.andExpect(MockMvcResultMatchers.status().isOk());
		perform.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
		perform.andExpect(MockMvcResultMatchers.jsonPath("$.id", is(validEntity.getId().toString())));
		perform.andExpect(MockMvcResultMatchers.jsonPath("$.name", is(validEntity.getName())));
		perform.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void givenPostRequestToSaveEntityWhenSaveResourceThenShouldReturnAHeaderWithNewLocationAndANewResource() throws Exception {
		//given
		BDDMockito.given(service.save(BDDMockito.any())).willReturn(validEntity);
		String url = "/api/v1/customers";
		
		//when
		ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON).content(validEntityJson));
		
		//then
		perform.andExpect(MockMvcResultMatchers.status().isCreated());
		perform.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
		perform.andExpect(MockMvcResultMatchers.content().json(validEntityJson));
		perform.andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost:8080/api/v1/customers/"+validEntity.getId().toString()));
		perform.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void givenPutRequestToUpdateEntityWhenUpdateResourceThenShouldReturnNoContentResponseStatus() throws Exception {
		//given
		BDDMockito.given(service.update(BDDMockito.any(UUID.class), BDDMockito.any())).willReturn(validEntity);
		String url = "/api/v1/beers/" + validEntity.getId().toString();
		
		//when
		ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.put(url).contentType(MediaType.APPLICATION_JSON).content(validEntityJson));
		
		//then
		perform.andExpect(MockMvcResultMatchers.status().isNoContent());
		perform.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void givenDeleteRequestToDeleteEntityWhenDeleteResourceThenShouldReturnADeleteResponseStatus() throws Exception {
		//given
		String url = "/api/v1/customers/" + validEntity.getId().toString();
		
		//when
		ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.delete(url));
		
		//then
		perform.andExpect(MockMvcResultMatchers.status().isNoContent());
		perform.andDo(MockMvcResultHandlers.print());
	}
}

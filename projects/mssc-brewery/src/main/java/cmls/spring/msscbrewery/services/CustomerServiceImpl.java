package cmls.spring.msscbrewery.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import cmls.spring.msscbrewery.web.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	private List<Customer> entities = Arrays.asList(Customer.builder().id(UUID.randomUUID()).name("Carlos").build(), Customer.builder().id(UUID.randomUUID()).name("Manuel").build()); 
	
	@Override
	public Customer findById(UUID id) {
		Optional<Customer> optionalEntity = entities.stream().filter(entity -> entity.getId().equals(id)).findFirst();
		if (optionalEntity.isPresent())
			return optionalEntity.get();
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@Override
	public List<Customer> findAll() {
		return entities;
	}

}

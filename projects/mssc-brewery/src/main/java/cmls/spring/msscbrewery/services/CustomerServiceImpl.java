package cmls.spring.msscbrewery.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import cmls.spring.msscbrewery.web.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	private List<Customer> entities = Stream.of(Customer.builder().id(UUID.randomUUID()).name("Carlos").build(), Customer.builder().id(UUID.randomUUID()).name("Manuel").build()).collect(Collectors.toList()); 
	
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

	@Override
	public Customer save(Customer entity) {
		Customer entitySaved = Customer.builder().id(UUID.randomUUID()).name(entity.getName()).build();
		entities.add(entitySaved);
		return entitySaved;
	}

	@Override
	public Customer update(UUID id, Customer entity) {
		Optional<Customer> optionalEntity = entities.stream().filter(innerEntity -> innerEntity.getId().equals(id)).findFirst();
		if (optionalEntity.isPresent())
			return optionalEntity.get();
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@Override
	public void delete(UUID id) {
		Optional<Customer> optionalEntity = entities.stream().filter(innerEntity -> innerEntity.getId().equals(id)).findFirst();
		if (optionalEntity.isPresent()) {
			entities.remove(optionalEntity.get());
		}
	}
}

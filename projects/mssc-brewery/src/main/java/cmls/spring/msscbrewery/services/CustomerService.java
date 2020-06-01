package cmls.spring.msscbrewery.services;

import java.util.List;
import java.util.UUID;

import cmls.spring.msscbrewery.web.model.Customer;

public interface CustomerService {
	Customer findById(UUID id);

	List<Customer> findAll();

	Customer save(Customer customer);

	Customer update(UUID id, Customer entity);

	void delete(UUID id);
}

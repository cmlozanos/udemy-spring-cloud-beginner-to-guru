package cmls.spring.msscbrewery.web.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cmls.spring.msscbrewery.services.CustomerService;
import cmls.spring.msscbrewery.web.model.Customer;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
	
	private CustomerService service;
	
	public CustomerController(CustomerService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<Customer>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> findById(@PathVariable UUID id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<List<Customer>> save(Customer entity) {
		Customer entitySaved = service.save(entity);
		return ResponseEntity.created(URI.create("http://localhost:8080/api/v1/beers/" + entitySaved.getId().toString())).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable UUID id, @RequestBody Customer entity){
		service.update(id, entity);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable UUID id){
		service.delete(id);
	}
}

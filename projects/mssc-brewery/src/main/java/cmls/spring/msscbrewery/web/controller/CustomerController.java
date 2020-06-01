package cmls.spring.msscbrewery.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
}

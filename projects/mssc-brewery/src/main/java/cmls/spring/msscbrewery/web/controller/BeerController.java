package cmls.spring.msscbrewery.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cmls.spring.msscbrewery.services.BeerService;
import cmls.spring.msscbrewery.web.model.Beer;

@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {
	
	private BeerService service;
	
	public BeerController(BeerService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<Beer>> findAll() {
		return ResponseEntity.ok(Arrays.asList(Beer.builder().name("cruzcampo").build(), Beer.builder().name("mahou").build()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Beer> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}
}

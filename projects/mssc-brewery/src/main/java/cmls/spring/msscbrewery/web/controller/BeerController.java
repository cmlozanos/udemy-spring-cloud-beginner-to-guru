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
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Beer> findById(@PathVariable UUID id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Beer> save(@RequestBody Beer entity) {
		Beer entitySaved = service.save(entity);
		return ResponseEntity.created(URI.create("http://localhost:8080/api/v1/beers/" + entitySaved.getId().toString())).body(entitySaved);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Beer entity){
		service.update(id, entity);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable UUID id){
		service.delete(id);
	}
}

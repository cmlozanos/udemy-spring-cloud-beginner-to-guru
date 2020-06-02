package cmls.spring.msscbeerservice.web.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cmls.spring.msscbeerservice.web.model.Beer;

@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {
	
	@GetMapping("/{id}")
	public ResponseEntity<Beer> findById(@PathVariable UUID id) {
		return ResponseEntity.ok(Beer.builder().id(UUID.randomUUID()).build());
	}

	@PostMapping("/{id}")
	public ResponseEntity<Beer> save(@RequestBody Beer beer) {
		return ResponseEntity.created(URI.create("/api/v1/beers/" + UUID.randomUUID())).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Beer beer) {
		return ResponseEntity.noContent().build();
	}
	
	
}


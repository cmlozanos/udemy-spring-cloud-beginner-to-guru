package cmls.spring.msscbeerservice.web.controller;

import java.net.URI;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cmls.spring.msscbeerservice.web.model.Beer;

@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/{id}")
	public ResponseEntity<Beer> findById(@PathVariable UUID id) {
		logger.info("findById");
		return ResponseEntity.ok(Beer.builder().id(UUID.randomUUID()).build());
	}

	@PostMapping
	public ResponseEntity<Beer> save(@RequestBody Beer beer) {
		logger.info("save");
		return ResponseEntity.created(URI.create("/api/v1/beers/" + UUID.randomUUID())).build();
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable UUID id, @RequestBody Beer beer) {
		logger.info("update");
	}
	
	
}


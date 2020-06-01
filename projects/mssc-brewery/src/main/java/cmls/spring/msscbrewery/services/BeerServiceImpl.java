package cmls.spring.msscbrewery.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import cmls.spring.msscbrewery.web.model.Beer;

@Service
public class BeerServiceImpl implements BeerService {

	private List<Beer> entities = Arrays.asList(Beer.builder().id(UUID.randomUUID()).name("Cruzcampo").build(), Beer.builder().id(UUID.randomUUID()).name("Mahou").build()); 
	
	@Override
	public Beer findById(UUID id) {
		Optional<Beer> optionalEntity = entities.stream().filter(entity -> entity.getId().equals(id)).findFirst();
		if (optionalEntity.isPresent())
			return optionalEntity.get();
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@Override
	public List<Beer> findAll() {
		return entities;
	}

}

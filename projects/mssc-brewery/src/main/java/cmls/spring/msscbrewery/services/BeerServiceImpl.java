package cmls.spring.msscbrewery.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import cmls.spring.msscbrewery.web.model.Beer;

@Service
public class BeerServiceImpl implements BeerService {

	private static final UUID FIRST_UUID = UUID.fromString("3166f218-afe7-4a3e-9da7-042349927f7e");
	private List<Beer> entities = Stream.of(Beer.builder().id(FIRST_UUID).name("Cruzcampo").build(), Beer.builder().id(UUID.randomUUID()).name("Mahou").build()).collect(Collectors.toList()); 
	
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

	@Override
	public Beer save(Beer entity) {
		Beer entitySaved = Beer.builder().id(UUID.randomUUID()).name(entity.getName()).style(entity.getStyle()).upc(entity.getUpc()).build();
		entities.add(entitySaved);
		return entitySaved;
	}

	@Override
	public Beer update(UUID id, Beer entity) {
		Optional<Beer> optionalEntity = entities.stream().filter(innerEntity -> innerEntity.getId().equals(id)).findFirst();
		if (optionalEntity.isPresent()) {
			Beer beer = optionalEntity.get();
			beer.setName(entity.getName());
			beer.setStyle(entity.getStyle());
			beer.setUpc(entity.getUpc());
			return beer;
		}
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@Override
	public void delete(UUID id) {
		Optional<Beer> optionalEntity = entities.stream().filter(innerEntity -> innerEntity.getId().equals(id)).findFirst();
		if (optionalEntity.isPresent()) {
			entities.remove(optionalEntity.get());
		}
	}

}

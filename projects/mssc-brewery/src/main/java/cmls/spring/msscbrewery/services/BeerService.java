package cmls.spring.msscbrewery.services;

import java.util.List;
import java.util.UUID;

import cmls.spring.msscbrewery.web.model.Beer;

public interface BeerService {
	Beer findById(UUID id);

	List<Beer> findAll();
}

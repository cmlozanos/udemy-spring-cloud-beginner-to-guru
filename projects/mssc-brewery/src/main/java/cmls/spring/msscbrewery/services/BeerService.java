package cmls.spring.msscbrewery.services;

import cmls.spring.msscbrewery.web.model.Beer;

public interface BeerService {
	public Beer findById(Long id);
}

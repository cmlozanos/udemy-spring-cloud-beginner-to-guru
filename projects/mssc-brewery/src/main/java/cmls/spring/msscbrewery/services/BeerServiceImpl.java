package cmls.spring.msscbrewery.services;

import org.springframework.stereotype.Service;

import cmls.spring.msscbrewery.web.model.Beer;

@Service
public class BeerServiceImpl implements BeerService {

	public Beer findById(Long id) {
		return Beer.builder().id(id).name("cruzcampo").build();
	}

}

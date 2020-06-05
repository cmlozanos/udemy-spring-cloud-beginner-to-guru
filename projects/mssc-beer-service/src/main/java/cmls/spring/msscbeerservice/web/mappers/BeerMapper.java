package cmls.spring.msscbeerservice.web.mappers;

import org.mapstruct.Mapper;


@Mapper
public interface BeerMapper {
	cmls.spring.msscbeerservice.web.model.Beer beerIntoBeer (cmls.spring.msscbeerservice.domain.Beer beer);
	cmls.spring.msscbeerservice.domain.Beer beerIntoBeer (cmls.spring.msscbeerservice.web.model.Beer beer);
}

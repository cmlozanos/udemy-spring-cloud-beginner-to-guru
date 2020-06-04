package cmls.spring.msscbeerservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import cmls.spring.msscbeerservice.web.model.Beer;

public interface BeerRepository extends JpaRepository<Beer, UUID> {

}

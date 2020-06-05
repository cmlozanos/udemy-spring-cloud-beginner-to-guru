package cmls.spring.msscbeerservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import cmls.spring.msscbeerservice.domain.Beer;


public interface BeerRepository extends JpaRepository<Beer, UUID> {

}

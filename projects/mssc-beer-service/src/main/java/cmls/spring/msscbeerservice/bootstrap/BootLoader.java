package cmls.spring.msscbeerservice.bootstrap;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cmls.spring.msscbeerservice.domain.Beer;
import cmls.spring.msscbeerservice.repositories.BeerRepository;

@Component
public class BootLoader implements CommandLineRunner {

	@Autowired
	private BeerRepository beerRepository;

	@Override
	public void run(String... args) throws Exception {
		loadBeerObjects();
	}

	private void loadBeerObjects() {
		if (beerRepository.count() == 0) {
			beerRepository.save(Beer.builder().name("Mango bobs").style("IPA").quantityToBrew(200).minOnHand(12)
					.upc(3370100000001L).price(BigDecimal.valueOf(12.95)).build());

			beerRepository.save(Beer.builder().name("Galaxy Cat").style("PALE_ALE").quantityToBrew(200).minOnHand(12)
					.upc(3370100000002L).price(BigDecimal.valueOf(11.95)).build());
		}
		
		System.out.println("Loaded Beers" + beerRepository.count());
	}

}

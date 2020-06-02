package cmls.spring.msscbeerservice.web.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beer {

	private UUID id;
	private Integer version;
	
	private OffsetDateTime created;
	private OffsetDateTime modified;
	
	private String name;
	private BeerStyleEnum style;
	private Long upc;
	private Integer qualityOnHand;
}

package cmls.spring.msscbeerservice.web.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beer {

	@Null
	private UUID id;
	private Integer version;
	
	private OffsetDateTime created;
	private OffsetDateTime modified;
	
	@NotBlank
	private String name;
	
	@NotNull
	private BeerStyleEnum style;
	
	@Positive
	private Long upc;
	private Integer qualityOnHand;
}

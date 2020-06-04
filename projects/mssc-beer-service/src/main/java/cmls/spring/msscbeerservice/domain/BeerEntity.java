package cmls.spring.msscbeerservice.domain;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class BeerEntity {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(length = 36, columnDefinition = "varchar", updatable= false, nullable=false)
	private UUID id;
	
	@Version
	private Integer version;
	
	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp created;
	
	@UpdateTimestamp
	private Timestamp modified;
	
	private String name;
	private String style;
	
	@Column(unique = true)
	private Long upc;
	private Integer qualityOnHand;
	private Integer quantityToBrew;
}

package in.mk.co.entity
;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Co_Trigger")
@Data
public class CoTrigger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer trgId;
	private Long caseId;
	
	@Lob
	private byte[] pdf;
	private String trgStatus;
	
}

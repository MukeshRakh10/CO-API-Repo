package in.mk.co.dto;

import lombok.Data;

@Data
public class KidsDetailsDTO {
	private Long caseId;
	private Long kidId;
	private String  kidName;
	private Integer kidAge;
	private Integer kidSSN;
	
}

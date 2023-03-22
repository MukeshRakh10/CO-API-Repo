package in.mk.co.dto;

import lombok.Data;

@Data
public class CoTriggerDTO {

	private Integer trgId;
	private Long caseId;
	private byte[] coPdf;
	private String trgStatus;
	private Long totalTriggers;
	private Long successTrigger;
	private Long failedTrigger;
	
	

}

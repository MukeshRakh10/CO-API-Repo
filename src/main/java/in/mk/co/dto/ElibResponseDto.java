package in.mk.co.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ElibResponseDto {

	private Long edTraceId;
	private Long appId;
	private Long caseId;
	private String holderName;
	private Long holderSsn;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private 	Double benefitAmt;
	private String denialReason;

}

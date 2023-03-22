package in.mk.co.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ELIG_DETAILS")
@Data
public class EdEligDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long edTraceId;
	private Long appId;
	private Long caseId;
	private String holderName;
	private Long holderSsn;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Double benefitAmt;
	private String denialReason;

}

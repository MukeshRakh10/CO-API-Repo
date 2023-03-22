package in.mk.co.dto;

import java.util.Map;

import lombok.Data;

@Data
public class PlanSelectionDTO {
	private Long caseId;
	private Long planId;
	private String planName;
	private  Map<Long,String> plansInfo;
}

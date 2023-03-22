package in.mk.co.dto;

import java.util.List;

import lombok.Data;

@Data
public class SummaryDTO {
	
	private String fname;
	private Long ssn;
	private  String planNmae;
		
	
	
	private PlanSelectionDTO planInfo;
	private IncomeDetailsDTO incomeDetails;
	private EductionDetailsDTO educationDetails;
	private List<KidsDetailsDTO> kindsDetails;
}

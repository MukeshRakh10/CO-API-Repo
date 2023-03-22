package in.mk.co.dto;

import lombok.Data;

@Data
public class IncomeDetailsDTO {

	private Long caseId;
	private Long incomeId;
	private Long monthlySalIncome;
	private Long rentIncome;
	private Long propertyIncome;
}

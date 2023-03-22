package in.mk.co.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table
@Entity(name = "INCOME_DETAILS")
@Data
public class IncomeDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long incomeId;
	private Long caseId;
	private Long monthlySalIncome;
	private Long rentIncome;
	private Long propertyIncome;
}

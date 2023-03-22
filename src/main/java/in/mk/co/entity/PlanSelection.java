package in.mk.co.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table
@Entity(name = "PLAN_SELECTION")
@Data
public class PlanSelection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long planId;
	private Long caseId;
	private String  planName;
}
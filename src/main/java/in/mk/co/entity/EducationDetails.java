package in.mk.co.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table
@Entity(name = "EDUCTATION_DETAILS")
@Data
public class EducationDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long educationId;
	private Long caseId;
	private String highestDegree;
	private Integer educationYear;
	private String universityName;

}

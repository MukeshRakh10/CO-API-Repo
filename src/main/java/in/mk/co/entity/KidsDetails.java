package in.mk.co.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table
@Entity(name = "KIDS_DETAILS")
@Data
public class KidsDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long kidId;
	private Long caseId;
	
	private String kidName;
	private Integer kidAge;
	private Integer kidSSN;
}

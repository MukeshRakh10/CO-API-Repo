package in.mk.co.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "DC_Cases")
@Data
public class DcCase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long caseId;
	private Long planId;
	private Long appId;
	

}

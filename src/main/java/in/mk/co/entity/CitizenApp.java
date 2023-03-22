package in.mk.co.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table( name = "CITIZEN_APPS")
@Data
public class CitizenApp {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  Long appId;
	private String fullName;
	private String email;
	private Long mobileNo;
	private String gender;
	private LocalDate dob;
	private Long ssn;
	
	@CreationTimestamp
	private LocalDate createdDate;
	private Integer createdBy;
	
	@UpdateTimestamp
	private LocalDate updateDate;
	private Integer updateBy;

}

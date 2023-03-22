package in.mk.co.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mk.co.entity.CoTrigger;

public interface CoTriRepo extends JpaRepository<CoTrigger, Serializable> {

	public List<CoTrigger> findByTrgStatus(String status);
	public CoTrigger findByCaseId(Long caseId);
	
}	

package in.mk.co.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mk.co.entity.IncomeDetails;

public interface IncomeRepository extends JpaRepository<IncomeDetails, Long>{
	
	public IncomeDetails findByCaseId(Long caseId);

}

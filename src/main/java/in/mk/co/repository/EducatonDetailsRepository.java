package in.mk.co.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.mk.co.entity.EducationDetails;

@Repository
public interface EducatonDetailsRepository extends JpaRepository<EducationDetails, Long>{
	EducationDetails findByCaseId(Long caseId);
}
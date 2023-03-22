package in.mk.co.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mk.co.entity.DcCase;

public interface DcCaseRepository extends JpaRepository<DcCase,Serializable> {

}

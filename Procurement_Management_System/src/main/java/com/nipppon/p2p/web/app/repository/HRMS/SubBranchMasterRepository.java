package com.nipppon.p2p.web.app.repository.HRMS;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nipppon.p2p.web.app.entity.HRMS.SubBranchMaster;

public interface SubBranchMasterRepository extends JpaRepository<SubBranchMaster, Integer>{
	List<SubBranchMaster> findDistinctByBNoInOrderByBName(List<String> b_no);

//	@Query("SELECT s FROM SubBranchMaster s ORDER BY s.bName ASC") - by writing queries using import org.springframework.data.jpa.repository.Query;
//	List<SubBranchMaster> findAll(Sort sort); - by getting sorting conditions from service
	default List<SubBranchMaster> findAllOrderByBName(){ //without any parameters and making the find all changes default
		return findAll(Sort.by(Sort.Direction.ASC, "bName")).stream().distinct().toList();
	}
	Optional<SubBranchMaster> findById(Long officeLocId);
}
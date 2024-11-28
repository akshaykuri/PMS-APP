package com.nipppon.p2p.web.app.repository.HRMS;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nipppon.p2p.web.app.entity.HRMS.BranchMaster;

@Repository
public interface BranchMasterRepository extends JpaRepository<BranchMaster, Integer>{
	List<BranchMaster> findByBranchTypeCodeInOrderByBranchName(List<String> b_no);

	default List<BranchMaster> findAllOrderByBName(){ //without any parameters and making the find all changes default
		return findAll(Sort.by(Sort.Direction.ASC, "branchName"));
	}
}
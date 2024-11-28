package com.nipppon.p2p.web.app.repository.P2P.Masters;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nipppon.p2p.web.app.entity.P2P.Masters.DepartmentHead;

@Repository
public interface DepartmentHeadRepository extends JpaRepository<DepartmentHead, Long>{
	Optional<DepartmentHead> findByBranchIdAndDeptIdAndDeptHeadIdAndDeptHeadDeleteStatus(int branchId, int deptId, Long deptHeadId, int deleteStatus);
	DepartmentHead findByBranchIdAndDeptId(int branchId, int departId);
}
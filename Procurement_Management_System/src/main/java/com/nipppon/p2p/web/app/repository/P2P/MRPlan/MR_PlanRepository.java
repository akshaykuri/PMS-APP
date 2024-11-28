package com.nipppon.p2p.web.app.repository.P2P.MRPlan;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nipppon.p2p.web.app.entity.P2P.MRPlan.MR_Plan;

@Repository
public interface MR_PlanRepository extends JpaRepository<MR_Plan, Long>{
	MR_Plan findTopByBranchCodeOrderByIdIndexDesc(int branchCode);
	MR_Plan findTopByReqNumberLikeOrderByIdIndexDesc(String reqNumberPattern);
	List<MR_Plan> findDistinctByReqByOrMrPlanRoutings_RecEmpId(String reqBy, String recEmpId);
}
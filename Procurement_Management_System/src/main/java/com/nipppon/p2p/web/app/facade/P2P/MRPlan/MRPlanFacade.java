package com.nipppon.p2p.web.app.facade.P2P.MRPlan;

import java.util.List;
import com.nipppon.p2p.web.app.dto.P2P.MRPlan.MR_PlanDTO;
import com.nipppon.p2p.web.app.entity.P2P.MRPlan.MR_Plan;
import com.nipppon.p2p.web.app.entity.P2P.MRPlan.MR_Plan_Item;

public interface MRPlanFacade{
	MR_Plan submitMRPlanRequest(MR_Plan mr_Plan, List<MR_Plan_Item> items, String approverId, Long appLevelNo);
	MR_Plan saveMRPlanRequest(MR_Plan mr_Plan, List<MR_Plan_Item> items);
	MR_Plan saveMRPlanAgainRequest(MR_Plan mr_Plan, List<MR_Plan_Item> items, Long id);
	MR_Plan saveSubmitMRPlanRequest(MR_Plan mr_Plan, List<MR_Plan_Item> items, String approverId, Long appLevelNo, Long id);
	MR_Plan statusMRPlanRequest(Long id, String appId, String status, String remarks);
	MR_Plan deleteMRPlanRequest(Long id, String appId, String remarks);
	List<MR_PlanDTO> getMrpRequests(String empId);
	MR_PlanDTO getMrpDetails(Long mrpId);
	List<MR_PlanDTO> getSavedMrpDetails(Long mrpId);
}
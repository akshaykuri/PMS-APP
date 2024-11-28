package com.nipppon.p2p.web.app.facade.P2P.MRPlan;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nipppon.p2p.web.app.dto.P2P.MRPlan.MR_PlanDTO;
import com.nipppon.p2p.web.app.entity.P2P.MRPlan.MR_Plan;
import com.nipppon.p2p.web.app.entity.P2P.MRPlan.MR_Plan_Item;
import com.nipppon.p2p.web.app.service.P2P.MRPlanService;

@Component
public class MRPlanFacadeImpl implements MRPlanFacade{
	@Autowired
	private MRPlanService mrPlanService;

	@Override
	public MR_Plan submitMRPlanRequest(MR_Plan mr_Plan, List<MR_Plan_Item> items, String approverId, Long appLevelNo) {
		return mrPlanService.submitMRPlanRequest(mr_Plan, items, approverId, appLevelNo);
	}

	@Override
	public MR_Plan saveMRPlanRequest(MR_Plan mr_Plan, List<MR_Plan_Item> items) {
		return mrPlanService.saveMRPlanRequest(mr_Plan, items);
	}

	@Override
	public MR_Plan saveMRPlanAgainRequest(MR_Plan mr_Plan, List<MR_Plan_Item> items, Long id) {
		return mrPlanService.saveMRPlanAgainRequest(mr_Plan, items, id);
	}

	@Override
	public MR_Plan saveSubmitMRPlanRequest(MR_Plan mr_Plan, List<MR_Plan_Item> items, String approverId, Long appLevelNo, Long id) {
		return mrPlanService.saveSubmitMRPlanRequest(mr_Plan, items, approverId, appLevelNo, id);
	}

	@Override
	public MR_Plan statusMRPlanRequest(Long id, String appId, String status, String remarks) {
		return mrPlanService.statusMRPlanRequest(id, appId, status, remarks);
	}

	@Override
	public MR_Plan deleteMRPlanRequest(Long id, String appId, String remarks) {
		return mrPlanService.deleteMRPlanRequest(id, appId, remarks);
	}

	@Override
	public List<MR_PlanDTO> getMrpRequests(String empId){
		return mrPlanService.getMrpRequests(empId);
	}

	@Override
	public MR_PlanDTO getMrpDetails(Long mrpId){
		return mrPlanService.getMrpDetails(mrpId);
	}

	@Override
	public List<MR_PlanDTO> getSavedMrpDetails(Long mrpId){
		return mrPlanService.getSavedMrpDetails(mrpId);
	}
}
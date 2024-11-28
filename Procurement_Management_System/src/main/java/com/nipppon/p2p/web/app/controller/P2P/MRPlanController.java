package com.nipppon.p2p.web.app.controller.P2P;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nipppon.p2p.web.app.dto.P2P.MRPlan.MR_PlanDTO;
import com.nipppon.p2p.web.app.entity.P2P.MRPlan.MR_Plan;
import com.nipppon.p2p.web.app.entity.P2P.MRPlan.MR_Plan_Item;
import com.nipppon.p2p.web.app.facade.P2P.MRPlan.MRPlanFacade;

@RestController
@RequestMapping("/api/p2p/mrPlan")
public class MRPlanController{
	@Autowired
	private MRPlanFacade mrPlanFacade;

	@PostMapping("/submit")
	public ResponseEntity<?> submitMRPlanRequest(@RequestBody MR_PlanDTO mr_PlanDTO){
		MR_Plan request = mr_PlanDTO.getRequest();
		List<MR_Plan_Item> items = mr_PlanDTO.getItems();
		String approverId = mr_PlanDTO.getApproverId();
		Long appLevelNo = mr_PlanDTO.getAppLevelNo();
		MR_Plan mr_Plan = mrPlanFacade.submitMRPlanRequest(request, items, approverId, appLevelNo);
		return new ResponseEntity<>(mr_Plan, HttpStatus.CREATED);
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveMRPlanRequest(@RequestBody MR_PlanDTO mr_PlanDTO){
		MR_Plan request = mr_PlanDTO.getRequest();
		List<MR_Plan_Item> items = mr_PlanDTO.getItems();
		MR_Plan mr_Plan = mrPlanFacade.saveMRPlanRequest(request, items);
		return new ResponseEntity<>(mr_Plan ,HttpStatus.CREATED);
	}

	@PostMapping("/saveAgain/{id}")
	public ResponseEntity<?> saveMRPlanAgainRequest(@PathVariable Long id, @RequestBody MR_PlanDTO mr_PlanDTO){
		MR_Plan request = mr_PlanDTO.getRequest();
		List<MR_Plan_Item> items = mr_PlanDTO.getItems();
		MR_Plan mr_Plan = mrPlanFacade.saveMRPlanAgainRequest(request, items, id);
		return new ResponseEntity<>(mr_Plan, HttpStatus.CREATED);
	}

	@PostMapping("/saveSubmit/{id}")
	public ResponseEntity<?> saveSubmitMRPlanRequest(@PathVariable Long id, @RequestBody MR_PlanDTO mr_PlanDTO){
		MR_Plan request = mr_PlanDTO.getRequest();
		List<MR_Plan_Item> items = mr_PlanDTO.getItems();
		String approverId = mr_PlanDTO.getApproverId();
		Long appLevelNo = mr_PlanDTO.getAppLevelNo();
		MR_Plan mr_Plan = mrPlanFacade.saveSubmitMRPlanRequest(request, items, approverId, appLevelNo, id);
		return new ResponseEntity<>(mr_Plan, HttpStatus.CREATED);
	}

	@PutMapping("/statusMRP/{id}/{appId}/{status}")
	public ResponseEntity<?> statusMRPlanRequest(@PathVariable Long id, @PathVariable String appId, @PathVariable String status, @RequestBody Map<String, String> payload) {
		String remarks = payload.get("remarks");
		try {
			MR_Plan mr_Plan = mrPlanFacade.statusMRPlanRequest(id, appId, status, remarks);
			return new ResponseEntity<>(mr_Plan, HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/deleteMRP/{id}/{appId}")
	public ResponseEntity<?> deleteMRPlanRequest(@PathVariable Long id, @PathVariable String appId, @RequestBody Map<String, String> payload) {
		String remarks = payload.get("remarks");
		try {
			MR_Plan mr_Plan = mrPlanFacade.deleteMRPlanRequest(id, appId, remarks);
			return new ResponseEntity<>(mr_Plan, HttpStatus.OK);
		}catch(RuntimeException e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getSavedMrpDetails/{mrpId}")
	public ResponseEntity<?> getSavedMrpDetails(@PathVariable Long mrpId){
		List<MR_PlanDTO> mrpDetails = mrPlanFacade.getSavedMrpDetails(mrpId);
		return new ResponseEntity<>(mrpDetails,HttpStatus.OK);
	}

	@GetMapping("/getMrpRequests/{empId}")
	public ResponseEntity<?> getMrpRequests(@PathVariable String empId){
		List<MR_PlanDTO> listOfMRPs = mrPlanFacade.getMrpRequests(empId);
		return new ResponseEntity<>(listOfMRPs, HttpStatus.OK);
	}

	@GetMapping("/getMrpDetails/{mrpId}")
	public ResponseEntity<?> getMrpDetails(@PathVariable Long mrpId){
		MR_PlanDTO mrpDetails = mrPlanFacade.getMrpDetails(mrpId);
		return new ResponseEntity<>(mrpDetails, HttpStatus.OK);
	}
}
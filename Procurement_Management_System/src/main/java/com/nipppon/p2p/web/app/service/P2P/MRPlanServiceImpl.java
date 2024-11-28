package com.nipppon.p2p.web.app.service.P2P;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nipppon.p2p.web.app.dto.HRMS.UserDTO;
import com.nipppon.p2p.web.app.dto.P2P.MRPlan.MR_PlanDTO;
import com.nipppon.p2p.web.app.dto.P2P.MRPlan.MR_Plan_ItemDTO;
import com.nipppon.p2p.web.app.dto.P2P.MRPlan.MR_Plan_RoutingDTO;
import com.nipppon.p2p.web.app.entity.HRMS.Department;
import com.nipppon.p2p.web.app.entity.HRMS.SubBranchMaster;
import com.nipppon.p2p.web.app.entity.P2P.MRPlan.MR_Plan;
import com.nipppon.p2p.web.app.entity.P2P.MRPlan.MR_Plan_Item;
import com.nipppon.p2p.web.app.entity.P2P.MRPlan.MR_Plan_Routing;
import com.nipppon.p2p.web.app.entity.P2P.Masters.DepartmentHead;
import com.nipppon.p2p.web.app.entity.P2P.Masters.Division;
import com.nipppon.p2p.web.app.entity.P2P.Masters.Product;
import com.nipppon.p2p.web.app.entity.P2P.Masters.UOM;
import com.nipppon.p2p.web.app.repository.HRMS.DepartmentRepository;
import com.nipppon.p2p.web.app.repository.HRMS.SubBranchMasterRepository;
import com.nipppon.p2p.web.app.repository.P2P.MRPlan.MR_PlanRepository;
import com.nipppon.p2p.web.app.repository.P2P.MRPlan.MR_Plan_ItemRepository;
import com.nipppon.p2p.web.app.repository.P2P.MRPlan.MR_Plan_RoutingRepository;
import com.nipppon.p2p.web.app.repository.P2P.Masters.DepartmentHeadRepository;
import com.nipppon.p2p.web.app.repository.P2P.Masters.DivisionRepository;
import com.nipppon.p2p.web.app.repository.P2P.Masters.ProductRepository;
import com.nipppon.p2p.web.app.repository.P2P.Masters.UOMRepository;
import com.nipppon.p2p.web.app.service.HRMS.UserService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MRPlanServiceImpl implements MRPlanService{
	@Autowired
	private MR_PlanRepository mrPlanRepository;
	@Autowired
	private MR_Plan_ItemRepository mrPlanItemRepository;
	@Autowired
	private MR_Plan_RoutingRepository mrPlanRoutingRepository;
	@Autowired
	private UserService userService;

	@Autowired
	private SubBranchMasterRepository subBranchMasterRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private DivisionRepository divisionRepository;
	@Autowired
	private UOMRepository uomRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private DepartmentHeadRepository departmentHeadRepository;

	@Override
	public MR_Plan submitMRPlanRequest(MR_Plan mr_Plan, List<MR_Plan_Item> items, String approverId, Long appLevelNo) {
		String referenceNumber	= generateReferenceNumber();
		String requestNumber	= generateRequestNumber(mr_Plan.getBranchCode());
		String reqNo[] = requestNumber.split("----");
		requestNumber = reqNo[0];
		int idIndex = Integer.parseInt(reqNo[1]);

// inserting into Main table
		MR_Plan mrPlan = new MR_Plan();
		mrPlan.setReqRefNumber(referenceNumber);
		mrPlan.setIdIndex(idIndex);
		mrPlan.setReqNumber(requestNumber);
		mrPlan.setOfficeLocId(mr_Plan.getOfficeLocId());
		mrPlan.setDepartId(mr_Plan.getDepartId());
		mrPlan.setDivId(mr_Plan.getDivId());
		mrPlan.setPurType(mr_Plan.getPurType());
		mrPlan.setReqBy(mr_Plan.getReqBy());
		mrPlan.setBranchId(mr_Plan.getBranchId());
		mrPlan.setBranchCode(mr_Plan.getBranchCode());
		mrPlan.setReqOn(mr_Plan.getReqOn());
		mrPlan.setDraftStatus("Submit");
		mrPlan.setReqStatus("Pending");
		mrPlan.setNoOfItems(mr_Plan.getNoOfItems());
		mrPlan.setTotalMRPValue(mr_Plan.getTotalMRPValue());
		mrPlan.setRemarks(mr_Plan.getRemarks());
		mrPlanRepository.save(mrPlan);

// inserting into Item table
		for(MR_Plan_Item item : items) {
			item.setMrPlan(mrPlan);
			item.setMrplId(mrPlan.getId());
			mrPlanItemRepository.save(item);
		}

// inserting Initiator into Routing table
		MR_Plan_Routing initiatorRouting = new MR_Plan_Routing();
		initiatorRouting.setMrPlan(mrPlan);
		initiatorRouting.setMrrId(mrPlan.getId());
		initiatorRouting.setStatus("Initiator");
		initiatorRouting.setRecEmpId(mrPlan.getReqBy());
		initiatorRouting.setSentEmpId("0");
		initiatorRouting.setMrpRemarks(mrPlan.getRemarks());
		initiatorRouting.setProcessDate(LocalDateTime.now());
		initiatorRouting.setSentDate(LocalDateTime.now());
		initiatorRouting.setAppLevelNo(0L);
		mrPlanRoutingRepository.save(initiatorRouting);

// inserting Approver details into Routing table
		UserDTO user = userService.getEmpDetails(approverId);
		MR_Plan_Routing approverRouting = new MR_Plan_Routing();
		approverRouting.setMrPlan(mrPlan);
		approverRouting.setMrrId(mrPlan.getId());
		approverRouting.setStatus("Pending");
		approverRouting.setRecEmpId(user.getEmpId());
		approverRouting.setSentEmpId(mrPlan.getReqBy());
		approverRouting.setMrpRemarks("Pending");
		approverRouting.setProcessDate(LocalDateTime.now());
		approverRouting.setSentDate(LocalDateTime.now());
		approverRouting.setAppLevelNo(appLevelNo);
		mrPlanRoutingRepository.save(approverRouting);

		return mrPlan;
	}

	@Override
	public MR_Plan saveMRPlanRequest(MR_Plan mr_Plan, List<MR_Plan_Item> items) {
		String referenceNumber	= generateReferenceNumber();
// inserting into Main table
		MR_Plan mrPlan = new MR_Plan();
		mrPlan.setReqRefNumber(referenceNumber);
		mrPlan.setOfficeLocId(mr_Plan.getOfficeLocId());
		mrPlan.setDepartId(mr_Plan.getDepartId());
		mrPlan.setDivId(mr_Plan.getDivId());
		mrPlan.setPurType(mr_Plan.getPurType());
		mrPlan.setReqBy(mr_Plan.getReqBy());
		mrPlan.setBranchId(mr_Plan.getBranchId());
		mrPlan.setBranchCode(mr_Plan.getBranchCode());
		mrPlan.setReqOn(mr_Plan.getReqOn());
		mrPlan.setDraftStatus("Saved");
		mrPlan.setReqStatus("Pending");
		mrPlan.setNoOfItems(mr_Plan.getNoOfItems());
		mrPlan.setTotalMRPValue(mr_Plan.getTotalMRPValue());
		mrPlan.setRemarks(mr_Plan.getRemarks());
		mrPlanRepository.save(mrPlan);

// inserting into Item table
		for(MR_Plan_Item item : items) {
			item.setMrPlan(mrPlan);
			item.setMrplId(mrPlan.getId());
			mrPlanItemRepository.save(item);
		}

		return mrPlan;
	}

	@Override
	public MR_Plan saveMRPlanAgainRequest(MR_Plan mr_Plan, List<MR_Plan_Item> items, Long id) {
		Optional<MR_Plan> existingPlan = mrPlanRepository.findById(id);
		if(existingPlan.isPresent()) {
			MR_Plan existing = existingPlan.get();
			existing.setReqRefNumber(existingPlan.get().getReqRefNumber());
			existing.setOfficeLocId(mr_Plan.getOfficeLocId());
			existing.setDepartId(mr_Plan.getDepartId());
			existing.setDivId(mr_Plan.getDivId());
			existing.setPurType(mr_Plan.getPurType());
			existing.setReqBy(mr_Plan.getReqBy());
			existing.setBranchId(mr_Plan.getBranchId());
			existing.setBranchCode(mr_Plan.getBranchCode());
			existing.setReqOn(mr_Plan.getReqOn());
			existing.setDraftStatus("Saved");
			existing.setReqStatus("Pending");
			existing.setNoOfItems(mr_Plan.getNoOfItems());
			existing.setTotalMRPValue(mr_Plan.getTotalMRPValue());
			existing.setRemarks(mr_Plan.getRemarks());
			mr_Plan = existing;
		}
		MR_Plan savedPlan = mrPlanRepository.save(mr_Plan);

		for(MR_Plan_Item item : items) {
			Optional<MR_Plan_Item> existingItem = mrPlanItemRepository.findByMrplIdAndProductId(savedPlan.getId(), item.getProductId());
			if(existingItem.isPresent()) {
				MR_Plan_Item existing  = existingItem.get();
				existing.setMrplId(savedPlan.getId());
				existing.setProductId(existingItem.get().getProductId());
				existing.setProductDesc(item.getProductDesc());
				existing.setQty(item.getQty());
				existing.setUomId(item.getUomId());
				existing.setUnitPrice(item.getUnitPrice());
				existing.setTotalPrice(item.getTotalPrice());
				mrPlanItemRepository.save(existing);
			} else {
				item.setMrPlan(savedPlan);
				mrPlanItemRepository.save(item);
			}
		}

		return savedPlan;
	}

	@Override
	public MR_Plan saveSubmitMRPlanRequest(MR_Plan mr_Plan, List<MR_Plan_Item> items, String approverId, Long appLevelNo, Long id) {
		String requestNumber	= generateRequestNumber(mr_Plan.getBranchCode());
		String reqNo[] = requestNumber.split("----");
		requestNumber = reqNo[0];
		int idIndex = Integer.parseInt(reqNo[1]);

// updating the Main table
		Optional<MR_Plan> existingPlan = mrPlanRepository.findById(id);
		if(existingPlan.isPresent()) {
			MR_Plan existing = existingPlan.get();
			existing.setReqRefNumber(existingPlan.get().getReqRefNumber());
			existing.setIdIndex(idIndex);
			existing.setReqNumber(requestNumber);
			existing.setOfficeLocId(mr_Plan.getOfficeLocId());
			existing.setDepartId(mr_Plan.getDepartId());
			existing.setDivId(mr_Plan.getDivId());
			existing.setPurType(mr_Plan.getPurType());
			existing.setReqBy(mr_Plan.getReqBy());
			existing.setBranchId(mr_Plan.getBranchId());
			existing.setBranchCode(mr_Plan.getBranchCode());
			existing.setReqOn(mr_Plan.getReqOn());
			existing.setDraftStatus("Submit");
			existing.setReqStatus("Pending");
			existing.setNoOfItems(mr_Plan.getNoOfItems());
			existing.setTotalMRPValue(mr_Plan.getTotalMRPValue());
			existing.setRemarks(mr_Plan.getRemarks());
			mr_Plan = existing;
		}
		MR_Plan savedPlan = mrPlanRepository.save(mr_Plan);

// updating & inserting Product details
		for(MR_Plan_Item item : items) {
			Optional<MR_Plan_Item> existingItem = mrPlanItemRepository.findByMrplIdAndProductId(savedPlan.getId(), item.getProductId());
			if(existingItem.isPresent()) {
				MR_Plan_Item existing  = existingItem.get();
				existing.setMrplId(savedPlan.getId());
				existing.setProductId(existingItem.get().getProductId());
				existing.setProductDesc(item.getProductDesc());
				existing.setQty(item.getQty());
				existing.setUomId(item.getUomId());
				existing.setUnitPrice(item.getUnitPrice());
				existing.setTotalPrice(item.getTotalPrice());
				mrPlanItemRepository.save(existing);
			} else {
				item.setMrPlan(savedPlan);
				mrPlanItemRepository.save(item);
			}
		}

// inserting Initiator into Routing table
		MR_Plan_Routing initiatorRouting = new MR_Plan_Routing();
		initiatorRouting.setMrPlan(mr_Plan);
		initiatorRouting.setMrrId(mr_Plan.getId());
		initiatorRouting.setStatus("Initiator");
		initiatorRouting.setRecEmpId(mr_Plan.getReqBy());
		initiatorRouting.setSentEmpId("0");
		initiatorRouting.setMrpRemarks(mr_Plan.getRemarks());
		initiatorRouting.setProcessDate(LocalDateTime.now());
		initiatorRouting.setSentDate(LocalDateTime.now());
		initiatorRouting.setAppLevelNo(0L);
		mrPlanRoutingRepository.save(initiatorRouting);

// inserting Approver details into Routing table
		UserDTO user = userService.getEmpDetails(approverId);
		MR_Plan_Routing approverRouting = new MR_Plan_Routing();
		approverRouting.setMrPlan(mr_Plan);
		approverRouting.setMrrId(mr_Plan.getId());
		approverRouting.setStatus("Pending");
		approverRouting.setRecEmpId(user.getEmpId());
		approverRouting.setSentEmpId(mr_Plan.getReqBy());
		approverRouting.setMrpRemarks("Pending");
		approverRouting.setProcessDate(LocalDateTime.now());
		approverRouting.setSentDate(LocalDateTime.now());
		approverRouting.setAppLevelNo(appLevelNo);
		mrPlanRoutingRepository.save(approverRouting);

		return savedPlan;
	}

	@Override
	public MR_Plan statusMRPlanRequest(Long id, String appId, String status, String remarks) {
		Optional<MR_Plan> existingPlan = mrPlanRepository.findById(id);
		if(existingPlan.isPresent()) {
			Optional<MR_Plan_Routing> currentRoute = mrPlanRoutingRepository.findByMrrIdAndStatusAndRecEmpId(existingPlan.get().getId(), "Pending", appId);
			Long currentLevel = currentRoute.get().getAppLevelNo();

			MR_Plan existing = existingPlan.get();
			existing.setReqRefNumber(existingPlan.get().getReqRefNumber());
			existing.setIdIndex(existingPlan.get().getIdIndex());
			existing.setReqNumber(existingPlan.get().getReqNumber());
			existing.setOfficeLocId(existingPlan.get().getOfficeLocId());
			existing.setDepartId(existingPlan.get().getDepartId());
			existing.setDivId(existingPlan.get().getDivId());
			existing.setPurType(existingPlan.get().getPurType());
			existing.setReqBy(existingPlan.get().getReqBy());
			existing.setBranchId(existingPlan.get().getBranchId());
			existing.setBranchCode(existingPlan.get().getBranchCode());
			existing.setReqOn(existingPlan.get().getReqOn());
			existing.setDraftStatus(existingPlan.get().getDraftStatus());
			if (status.equalsIgnoreCase("Reject")) {
				existing.setReqStatus("Rejected");
			} else if (status.equalsIgnoreCase("Approve") && currentLevel == 2) {
				existing.setReqStatus("Approved");
			} else {
				existing.setReqStatus("Pending");
			}
			existing.setNoOfItems(existingPlan.get().getNoOfItems());
			existing.setTotalMRPValue(existingPlan.get().getTotalMRPValue());
			existing.setRemarks(existingPlan.get().getRemarks());
			mrPlanRepository.save(existing);

			List<MR_Plan_Routing> routingDetails = mrPlanRoutingRepository.findByMrrIdAndRecEmpIdAndStatus(existing.getId(),appId ,"Pending");
			String updatedStatus = "";
			if (status.equalsIgnoreCase("Reject")) {
				updatedStatus = "Rejected";
			} else if (status.equalsIgnoreCase("Approve")) {
				updatedStatus = "Approved";
			}

			if(routingDetails != null) {
				for (MR_Plan_Routing routing : routingDetails) {
					routing.setStatus(updatedStatus);
					routing.setProcessDate(LocalDateTime.now());
					routing.setMrpRemarks(remarks);
				}
				mrPlanRoutingRepository.saveAll(routingDetails);
			}

			if (status.equalsIgnoreCase("Approve") && currentLevel == 1) {
				DepartmentHead deptHead = departmentHeadRepository.findByBranchIdAndDeptId(existingPlan.get().getBranchId(), (existingPlan.get().getDepartId()).intValue());
				Long nextAppId = deptHead.getDeptHeadId();
				UserDTO user = userService.getEmpDetails(nextAppId.toString());
				MR_Plan_Routing approverRouting = new MR_Plan_Routing();
				approverRouting.setMrPlan(existing);
				approverRouting.setMrrId(existing.getId());
				approverRouting.setStatus("Pending");
				approverRouting.setRecEmpId(user.getEmpId());
				approverRouting.setSentEmpId(appId);
				approverRouting.setMrpRemarks("Pending");
				approverRouting.setProcessDate(LocalDateTime.now());
				approverRouting.setSentDate(LocalDateTime.now());
				approverRouting.setAppLevelNo(2L);
				mrPlanRoutingRepository.save(approverRouting);
			}

			return existing;
		} else {
			throw new RuntimeException("MR Plan Request not found.");
		}
	}

	@Override
	public MR_Plan deleteMRPlanRequest(Long id, String appId, String remarks) {
		Optional<MR_Plan> existingPlan = mrPlanRepository.findById(id);
		if(existingPlan.isPresent()) {
			MR_Plan existing = existingPlan.get();
			existing.setReqRefNumber(existingPlan.get().getReqRefNumber());
			existing.setIdIndex(existingPlan.get().getIdIndex());
			existing.setReqNumber(existingPlan.get().getReqNumber());
			existing.setOfficeLocId(existingPlan.get().getOfficeLocId());
			existing.setDepartId(existingPlan.get().getDepartId());
			existing.setDivId(existingPlan.get().getDivId());
			existing.setPurType(existingPlan.get().getPurType());
			existing.setReqBy(existingPlan.get().getReqBy());
			existing.setBranchId(existingPlan.get().getBranchId());
			existing.setBranchCode(existingPlan.get().getBranchCode());
			existing.setReqOn(existingPlan.get().getReqOn());
			existing.setDraftStatus(existingPlan.get().getDraftStatus());
			existing.setReqStatus("Deleted");
			existing.setNoOfItems(existingPlan.get().getNoOfItems());
			existing.setTotalMRPValue(existingPlan.get().getTotalMRPValue());
			existing.setRemarks(existingPlan.get().getRemarks());
			mrPlanRepository.save(existing);

// updating & inserting Routing details
			List<MR_Plan_Routing> routingDetails = mrPlanRoutingRepository.findByMrrIdAndStatus(existing.getId(), "Pending");
			if(routingDetails != null) {
				for (MR_Plan_Routing routing : routingDetails) {
					routing.setStatus("No Action Required");
					routing.setMrpRemarks("No Action Required");
					routing.setProcessDate(LocalDateTime.now());
				}
				mrPlanRoutingRepository.saveAll(routingDetails);
			}

// inserting Approver details into Routing table
			UserDTO user = userService.getEmpDetails(appId);
			MR_Plan_Routing approverRouting = new MR_Plan_Routing();
			approverRouting.setMrPlan(existing);
			approverRouting.setMrrId(existing.getId());
			approverRouting.setStatus("Deleted");
			approverRouting.setRecEmpId(user.getEmpId());
			approverRouting.setSentEmpId(existing.getReqBy());
			approverRouting.setMrpRemarks(remarks);
			approverRouting.setProcessDate(LocalDateTime.now());
			approverRouting.setSentDate(LocalDateTime.now());
			approverRouting.setAppLevelNo(0L);
			mrPlanRoutingRepository.save(approverRouting);

			return existing;
		} else {
			throw new RuntimeException("MR Plan Request not found.");
		}
	}

	@Override
	public List<MR_PlanDTO> getMrpRequests(String empId){
		List<MR_Plan> mrps = mrPlanRepository.findDistinctByReqByOrMrPlanRoutings_RecEmpId(empId, empId);
		return mrps.stream().map(mrp -> {
			Optional<SubBranchMaster> subBrOpt = subBranchMasterRepository.findById(mrp.getOfficeLocId());
			Optional<Department> deptOpt = departmentRepository.findByDepartmentId(mrp.getDepartId());
			Optional<Division> divOpt = divisionRepository.findById(mrp.getDivId());
			UserDTO userReq = userService.getEmpDetails(mrp.getReqBy());
			return new MR_PlanDTO(
				mrp.getId(),
				mrp.getReqRefNumber(),
				mrp.getIdIndex(),
				mrp.getReqNumber(),
				mrp.getOfficeLocId(),
				mrp.getDepartId(),
				mrp.getDivId(),
				mrp.getPurType(),
				mrp.getReqBy(),
				mrp.getBranchId(),
				mrp.getBranchCode(),
				mrp.getReqOn(),
				mrp.getDraftStatus(),
				mrp.getReqStatus(),
				mrp.getNoOfItems(),
				mrp.getTotalMRPValue(),
				null,
				null,
				null,
				null,
				mrp.getRemarks(),
				mrp.getMrPlanItems().stream().map(item -> {
					Optional<UOM> uomOpt = uomRepository.findById(item.getUomId());
					Optional<Product> prodOpt = productRepository.findById(item.getProductId());
					return new MR_Plan_ItemDTO(
						item.getId(),
						item.getMrplId(),
						item.getProductId(),
						item.getProductDesc(),
						item.getQty(),
						item.getUomId(),
						item.getUnitPrice(),
						item.getTotalPrice(),
						prodOpt.map(Product :: getProductName).orElse(null),
						uomOpt.map(UOM :: getUomName).orElse(null)
					);
				}).collect(Collectors.toList()),
				mrp.getMrPlanRoutings().stream()
//				.filter(routing -> "Pending".equalsIgnoreCase(routing.getStatus()))
				.map(route -> {
					UserDTO userRec  = userService.getEmpDetails(route.getRecEmpId());
					UserDTO userSent = userService.getEmpDetails(route.getSentEmpId());
					return new MR_Plan_RoutingDTO(
						route.getId(),
						route.getMrrId(),
						route.getStatus(),
						route.getRecEmpId(),
						route.getSentEmpId(),
						route.getMrpRemarks(),
						route.getProcessDate(),
						route.getSentDate(),
						route.getAppLevelNo(),
						userRec.getUserName(),
						userSent.getUserName()
					);
				}).collect(Collectors.toList()),
				subBrOpt.map(SubBranchMaster :: getbName).orElse(null),
				deptOpt.map(Department :: getDepartmentName).orElse(null),
				divOpt.map(Division :: getDivisionName).orElse(null),
				userReq.getUserName()
			);
		}).collect(Collectors.toList());
	}

	@Override
	public List<MR_PlanDTO> getSavedMrpDetails(Long mrpId) {
		Optional<MR_Plan> mrPlanOpt = mrPlanRepository.findById(mrpId);
		return mrPlanOpt.stream().map(savedMrp -> {
			Optional<SubBranchMaster> subBrOpt = subBranchMasterRepository.findById(savedMrp.getOfficeLocId());
			Optional<Department> deptOpt = departmentRepository.findByDepartmentId(savedMrp.getDepartId());
			Optional<Division> divOpt = divisionRepository.findById(savedMrp.getDivId());
			UserDTO userReq = userService.getEmpDetails(savedMrp.getReqBy());
			return new MR_PlanDTO(
				savedMrp.getId(),
				savedMrp.getReqRefNumber(),
				savedMrp.getIdIndex(),
				savedMrp.getReqNumber(),
				savedMrp.getOfficeLocId(),
				savedMrp.getDepartId(),
				savedMrp.getDivId(),
				savedMrp.getPurType(),
				savedMrp.getReqBy(),
				savedMrp.getBranchId(),
				savedMrp.getBranchCode(),
				savedMrp.getReqOn(),
				savedMrp.getDraftStatus(),
				savedMrp.getReqStatus(),
				savedMrp.getNoOfItems(),
				savedMrp.getTotalMRPValue(),
				null,
				null,
				null,
				null,
				savedMrp.getRemarks(),
				savedMrp.getMrPlanItems().stream().map(item -> {
					Optional<UOM> uomOpt = uomRepository.findById(item.getUomId());
					Optional<Product> prodOpt = productRepository.findById(item.getProductId());
					return new MR_Plan_ItemDTO(
						item.getId(),
						item.getMrplId(),
						item.getProductId(),
						item.getProductDesc(),
						item.getQty(),
						item.getUomId(),
						item.getUnitPrice(),
						item.getTotalPrice(),
						prodOpt.map(Product :: getProductName).orElse(null),
						uomOpt.map(UOM :: getUomName).orElse(null)
					);
				}).collect(Collectors.toList()),
				savedMrp.getMrPlanRoutings().stream()
//				.filter(routing -> "Pending".equalsIgnoreCase(routing.getStatus()))
				.map(route -> {
					UserDTO userRec  = userService.getEmpDetails(route.getRecEmpId());
					UserDTO userSent = userService.getEmpDetails(route.getSentEmpId());
					return new MR_Plan_RoutingDTO(
						route.getId(),
						route.getMrrId(),
						route.getStatus(),
						route.getRecEmpId(),
						route.getSentEmpId(),
						route.getMrpRemarks(),
						route.getProcessDate(),
						route.getSentDate(),
						route.getAppLevelNo(),
						userRec.getUserName(),
						userSent.getUserName()
					);
				}).collect(Collectors.toList()),
				subBrOpt.map(SubBranchMaster :: getbName).orElse(null),
				deptOpt.map(Department :: getDepartmentName).orElse(null),
				divOpt.map(Division :: getDivisionName).orElse(null),
				userReq.getUserName()
			);
		}).collect(Collectors.toList());
	}

	@Override
	public MR_PlanDTO getMrpDetails(Long mrpId){
		Optional<MR_Plan> mrpOpt = mrPlanRepository.findById(mrpId);
		if(mrpOpt.isPresent()) {
			MR_Plan mrp = mrpOpt.get();
			Optional<SubBranchMaster> subBrOpt = subBranchMasterRepository.findById(mrp.getOfficeLocId());
			Optional<Department> deptOpt = departmentRepository.findByDepartmentId(mrp.getDepartId());
			Optional<Division> divOpt = divisionRepository.findById(mrp.getDivId());
			UserDTO userReq = userService.getEmpDetails(mrp.getReqBy());
			return new MR_PlanDTO(
				mrp.getId(),
				mrp.getReqRefNumber(),
				mrp.getIdIndex(),
				mrp.getReqNumber(),
				mrp.getOfficeLocId(),
				mrp.getDepartId(),
				mrp.getDivId(),
				mrp.getPurType(),
				mrp.getReqBy(),
				mrp.getBranchId(),
				mrp.getBranchCode(),
				mrp.getReqOn(),
				mrp.getDraftStatus(),
				mrp.getReqStatus(),
				mrp.getNoOfItems(),
				mrp.getTotalMRPValue(),
				null,
				null,
				null,
				null,
				mrp.getRemarks(),
				mrp.getMrPlanItems().stream().map(item -> {
					Optional<UOM> uomOpt = uomRepository.findById(item.getUomId());
					Optional<Product> prodOpt = productRepository.findById(item.getProductId());
					return new MR_Plan_ItemDTO(
						item.getId(),
						item.getMrplId(),
						item.getProductId(),
						item.getProductDesc(),
						item.getQty(),
						item.getUomId(),
						item.getUnitPrice(),
						item.getTotalPrice(),
						prodOpt.map(Product :: getProductName).orElse(null),
						uomOpt.map(UOM :: getUomName).orElse(null)
					);
				}).collect(Collectors.toList()),
				mrp.getMrPlanRoutings().stream()
				.map(route -> {
					UserDTO userRec  = userService.getEmpDetails(route.getRecEmpId());
					UserDTO userSent = userService.getEmpDetails(route.getSentEmpId());
					return new MR_Plan_RoutingDTO(
						route.getId(),
						route.getMrrId(),
						route.getStatus(),
						route.getRecEmpId(),
						route.getSentEmpId(),
						route.getMrpRemarks(),
						route.getProcessDate(),
						route.getSentDate(),
						route.getAppLevelNo(),
						userRec.getUserName(),
						userSent.getUserName()
					);
				}).collect(Collectors.toList()),
				subBrOpt.map(SubBranchMaster :: getbName).orElse(null),
				deptOpt.map(Department :: getDepartmentName).orElse(null),
				divOpt.map(Division :: getDivisionName).orElse(null),
				userReq.getUserName()
			);
		} else {
			throw new RuntimeException("MR Plan Details not found");			
		}
	}

	public String generateReferenceNumber() {
		String prefix = "NEIN";
		String date  = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String uniqueId = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
		return String.format("%s-%s-%s", prefix, date, uniqueId);
	}

	public String generateRequestNumber(int branchCode) {
	    String prefix = "NEIN/MRP/";
	    // Determine the current financial year
	    int currentYear		= LocalDate.now().getYear();
	    int currentMonth	= LocalDate.now().getMonthValue();
	    int financialYearStart = (currentMonth >= 4) ? currentYear : currentYear - 1;
	    // Fetch the MR_Plan with the highest idIndex for the current financial year and branch code
	    MR_Plan mrPlan = mrPlanRepository.findTopByReqNumberLikeOrderByIdIndexDesc(prefix + financialYearStart + "/" + branchCode + "-%");
	    int nextSequence = 1; // Default to 1 if no records are found
	    if (mrPlan != null) {
	        nextSequence = mrPlan.getIdIndex() + 1; // Increment the idIndex to get the next sequence
	    }
	    // Format the request number as NEIN/MRP/YYYY/branchCode-#####
	    String reqNumber = String.format("%s%d/%d-%05d", prefix, financialYearStart, branchCode, nextSequence);
	    return reqNumber + "----" + nextSequence;
	}
}
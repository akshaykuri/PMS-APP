package com.nipppon.p2p.web.app.dto.P2P.MRPlan;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.nipppon.p2p.web.app.entity.P2P.MRPlan.MR_Plan;
import com.nipppon.p2p.web.app.entity.P2P.MRPlan.MR_Plan_Item;

public class MR_PlanDTO{
	private long id;
	private String reqRefNumber;
	private int idIndex;
	private String reqNumber;
	private Long officeLocId;
	private Long departId;
	private Long divId;
	private String purType;
	private String reqBy;
	private int branchId;
	private int branchCode;
	private LocalDateTime reqOn;
	private String draftStatus;
	private String reqStatus;
	private int noOfItems;
	private BigDecimal totalMRPValue;
	private String remarks;
	private List<MR_Plan_ItemDTO> itemDTOs;
	private List<MR_Plan_RoutingDTO> routingDTOs;

	private MR_Plan request;
	private List<MR_Plan_Item> items;
	private String approverId;
	private Long appLevelNo;

	private String offLocName;
	private String deptName;
	private String divName;
	private String reqByName;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getReqRefNumber() {
		return reqRefNumber;
	}
	public void setReqRefNumber(String reqRefNumber) {
		this.reqRefNumber = reqRefNumber;
	}
	public int getIdIndex() {
		return idIndex;
	}
	public void setIdIndex(int idIndex) {
		this.idIndex = idIndex;
	}
	public String getReqNumber() {
		return reqNumber;
	}
	public void setReqNumber(String reqNumber) {
		this.reqNumber = reqNumber;
	}
	public Long getOfficeLocId() {
		return officeLocId;
	}
	public void setOfficeLocId(Long officeLocId) {
		this.officeLocId = officeLocId;
	}
	public Long getDepartId() {
		return departId;
	}
	public void setDepartId(Long departId) {
		this.departId = departId;
	}
	public Long getDivId() {
		return divId;
	}
	public void setDivId(Long divId) {
		this.divId = divId;
	}
	public String getPurType() {
		return purType;
	}
	public void setPurType(String purType) {
		this.purType = purType;
	}
	public String getReqBy() {
		return reqBy;
	}
	public void setReqBy(String reqBy) {
		this.reqBy = reqBy;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public int getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(int branchCode) {
		this.branchCode = branchCode;
	}
	public LocalDateTime getReqOn() {
		return reqOn;
	}
	public void setReqOn(LocalDateTime reqOn) {
		this.reqOn = reqOn;
	}
	public String getDraftStatus() {
		return draftStatus;
	}
	public void setDraftStatus(String draftStatus) {
		this.draftStatus = draftStatus;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public int getNoOfItems() {
		return noOfItems;
	}
	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
	}
	public BigDecimal getTotalMRPValue() {
		return totalMRPValue;
	}
	public void setTotalMRPValue(BigDecimal totalMRPValue) {
		this.totalMRPValue = totalMRPValue;
	}
	public MR_Plan getRequest() {
		return request;
	}
	public void setRequest(MR_Plan request) {
		this.request = request;
	}
	public List<MR_Plan_Item> getItems() {
		return items;
	}
	public void setItems(List<MR_Plan_Item> items) {
		this.items = items;
	}
	public String getApproverId() {
		return approverId;
	}
	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}
	public Long getAppLevelNo() {
		return appLevelNo;
	}
	public void setAppLevelNo(Long appLevelNo) {
		this.appLevelNo = appLevelNo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<MR_Plan_ItemDTO> getItemDTOs() {
		return itemDTOs;
	}
	public void setItemDTOs(List<MR_Plan_ItemDTO> itemDTOs) {
		this.itemDTOs = itemDTOs;
	}
	public List<MR_Plan_RoutingDTO> getRoutingDTOs() {
		return routingDTOs;
	}
	public void setRoutingDTOs(List<MR_Plan_RoutingDTO> routingDTOs) {
		this.routingDTOs = routingDTOs;
	}
	public String getOffLocName() {
		return offLocName;
	}
	public void setOffLocName(String offLocName) {
		this.offLocName = offLocName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getReqByName() {
		return reqByName;
	}
	public void setReqByName(String reqByName) {
		this.reqByName = reqByName;
	}

	public MR_PlanDTO(long id, String reqRefNumber, int idIndex, String reqNumber, Long officeLocId, Long departId, Long divId, String purType, String reqBy, int branchId, int branchCode, LocalDateTime reqOn, String draftStatus, String reqStatus, int noOfItems, BigDecimal totalMRPValue, MR_Plan request, List<MR_Plan_Item> items, String approverId, Long appLevelNo, String remarks, List<MR_Plan_ItemDTO> itemDTOs, List<MR_Plan_RoutingDTO> routingDTOs, String offLocName, String deptName, String divName, String reqByName) {
		this.id								= id;
		this.reqRefNumber					= reqRefNumber;
		this.idIndex						= idIndex;
		this.reqNumber						= reqNumber;
		this.officeLocId					= officeLocId;
		this.departId						= departId;
		this.divId							= divId;
		this.purType						= purType;
		this.reqBy							= reqBy;
		this.branchId						= branchId;
		this.branchCode						= branchCode;
		this.reqOn							= reqOn;
		this.draftStatus					= draftStatus;
		this.reqStatus						= reqStatus;
		this.noOfItems						= noOfItems;
		this.totalMRPValue					= totalMRPValue;
		this.request						= request;
		this.items							= items;
		this.approverId						= approverId;
		this.appLevelNo						= appLevelNo;
		this.remarks						= remarks;
		this.itemDTOs						= itemDTOs;
		this.routingDTOs					= routingDTOs;
		this.offLocName						= offLocName;
		this.deptName						= deptName;
		this.divName						= divName;
		this.reqByName						= reqByName;
	}
}
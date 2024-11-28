package com.nipppon.p2p.web.app.entity.P2P.MRPlan;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "mr_plan_request")
public class MR_Plan{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mrpId")
	private long id;

	@Column(unique = true)
	private String reqRefNumber;
	@Column(nullable = false)
	private int idIndex;
	private String reqNumber;
	private Long officeLocId;
	private Long departId;
	private Long divId;
	private String purType;
	private String reqBy;
	@Column(nullable = false)
	private int branchId;
	@Column(nullable = false)
	private int branchCode;
	private LocalDateTime reqOn;
	private String draftStatus;
	private String reqStatus;
	private int noOfItems;
	private BigDecimal totalMRPValue;
	private String remarks;

	@OneToMany(mappedBy = "mrPlan", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<MR_Plan_Routing> mrPlanRoutings;

	@OneToMany(mappedBy = "mrPlan", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<MR_Plan_Item> mrPlanItems;

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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<MR_Plan_Routing> getMrPlanRoutings() {
		return mrPlanRoutings;
	}
	public void setMrPlanRoutings(List<MR_Plan_Routing> mrPlanRoutings) {
		this.mrPlanRoutings = mrPlanRoutings;
	}
	public List<MR_Plan_Item> getMrPlanItems() {
		return mrPlanItems;
	}
	public void setMrPlanItems(List<MR_Plan_Item> mrPlanItems) {
		this.mrPlanItems = mrPlanItems;
	}
}
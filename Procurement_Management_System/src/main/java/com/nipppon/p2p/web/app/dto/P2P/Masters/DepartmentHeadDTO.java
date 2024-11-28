package com.nipppon.p2p.web.app.dto.P2P.Masters;

import java.time.LocalDateTime;

public class DepartmentHeadDTO{
	private Long id;
	private int branchId;
	private int deptId;
	private Long deptHeadId;
	private String createdBy;
	private LocalDateTime createdOn;
	private int deptHeadDeleteStatus;

	private String branchName;
	private String deptName;
	private String deptHeadName;
	private String createdByName;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public Long getDeptHeadId() {
		return deptHeadId;
	}
	public void setDeptHeadId(Long deptHeadId) {
		this.deptHeadId = deptHeadId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	public int getDeptHeadDeleteStatus() {
		return deptHeadDeleteStatus;
	}
	public void setDeptHeadDeleteStatus(int deptHeadDeleteStatus) {
		this.deptHeadDeleteStatus = deptHeadDeleteStatus;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptHeadName() {
		return deptHeadName;
	}
	public void setDeptHeadName(String deptHeadName) {
		this.deptHeadName = deptHeadName;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public DepartmentHeadDTO(Long id, int branchId, int deptId, Long deptHeadId, String createdBy, LocalDateTime createdOn, int deptHeadDeleteStatus, String branchName, String deptName, String deptHeadName, String createdByName){
		this.id = id;
		this.branchId = branchId;
		this.deptId = deptId;
		this.deptHeadId = deptHeadId;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.deptHeadDeleteStatus = deptHeadDeleteStatus;
		this.branchName = branchName;
		this.deptName = deptName;
		this.deptHeadName = deptHeadName;
		this.createdByName = createdByName;
	}
}
package com.nipppon.p2p.web.app.entity.P2P.Masters;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "deptHeadMaster")
public class DepartmentHead{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dh_id")
	private Long id;

	private int branchId;
	private int deptId;
	private Long deptHeadId;
	private String createdBy;
	private LocalDateTime createdOn;
	private int deptHeadDeleteStatus;

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
}
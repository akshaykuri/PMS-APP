package com.nipppon.p2p.web.app.dto.P2P.Masters;

import java.time.LocalDateTime;

public class AssignUserRoleDTO{
	private Long id;
    private String empId;
    private String empName;
    private String empDesig;
    private String empDept;
    private String empMail;
    private String empBranch;
    private String empAssignedRole;
    private String remarks;
    private String createdBy;
    private LocalDateTime createdOn;
    private int userRoleDeleteStatus;
    private int activeStatus;

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpDesig() {
		return empDesig;
	}
	public void setEmpDesig(String empDesig) {
		this.empDesig = empDesig;
	}
	public String getEmpDept() {
		return empDept;
	}
	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}
	public String getEmpMail() {
		return empMail;
	}
	public void setEmpMail(String empMail) {
		this.empMail = empMail;
	}
	public String getEmpBranch() {
		return empBranch;
	}
	public void setEmpBranch(String empBranch) {
		this.empBranch = empBranch;
	}
	public String getEmpAssignedRole() {
		return empAssignedRole;
	}
	public void setEmpAssignedRole(String empAssignedRole) {
		this.empAssignedRole = empAssignedRole;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public int getUserRoleDeleteStatus() {
		return userRoleDeleteStatus;
	}
	public void setUserRoleDeleteStatus(int userRoleDeleteStatus) {
		this.userRoleDeleteStatus = userRoleDeleteStatus;
	}
	public int getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public AssignUserRoleDTO(Long id, String empId, String empName, String empDesig, String empDept, String empMail, String empBranch, String empAssignedRole, String remarks, String createdBy, LocalDateTime createdOn, int userRoleDeleteStatus, int actStatus){
		this.id						= id;
		this.empId					= empId;
		this.empName				= empName;
		this.empDesig				= empDesig;
		this.empDept				= empDept;
		this.empMail				= empMail;
		this.empBranch				= empBranch;
		this.empAssignedRole		= empAssignedRole;
		this.remarks				= remarks;
		this.createdBy				= createdBy;
		this.createdOn				= createdOn;
		this.userRoleDeleteStatus	= userRoleDeleteStatus;
		this.activeStatus			= actStatus;
	}
}
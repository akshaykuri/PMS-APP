package com.nipppon.p2p.web.app.entity.P2P.Masters;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "assignUserRole")
public class AssignUserRole{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ra_id")
	private Long id;
    private String empId;
    private String empName;
    private String empDesig;
    private String empDept;
    private String empMail;
    private String empBranch;
    private String empAssignedRole;
    @Column (name = "description")
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
}
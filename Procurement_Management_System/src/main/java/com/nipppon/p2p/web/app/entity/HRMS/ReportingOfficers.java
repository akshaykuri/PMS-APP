package com.nipppon.p2p.web.app.entity.HRMS;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reporting_officers")
public class ReportingOfficers{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reporting_officers_id")
    private Integer reportingOfficersId;

	@Column(name = "user_id")
    private Integer userId;

	@Column(name = "manager_id")
    private Integer managerId;

	@Column(name = "emp_manager_id", length = 50)
    private String empManagerId;

    @Column(name = "manager2_id")
    private Integer manager2Id;

    @Column(name = "emp_manager2_id", length = 50)
    private String empManager2Id;

    @Column(name = "branch_head")
    private Integer branchHead;

    @Column(name = "branch_id", length = 50)
    private String branchId;

    @Column(name = "department_id", length = 50)
    private String departmentId;

    @Column(name = "emp_id", length = 100)
    private String empId;

	public Integer getReportingOfficersId() {
		return reportingOfficersId;
	}

	public void setReportingOfficersId(Integer reportingOfficersId) {
		this.reportingOfficersId = reportingOfficersId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getEmpManagerId() {
		return empManagerId;
	}

	public void setEmpManagerId(String empManagerId) {
		this.empManagerId = empManagerId;
	}

	public Integer getManager2Id() {
		return manager2Id;
	}

	public void setManager2Id(Integer manager2Id) {
		this.manager2Id = manager2Id;
	}

	public String getEmpManager2Id() {
		return empManager2Id;
	}

	public void setEmpManager2Id(String empManager2Id) {
		this.empManager2Id = empManager2Id;
	}

	public Integer getBranchHead() {
		return branchHead;
	}

	public void setBranchHead(Integer branchHead) {
		this.branchHead = branchHead;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
}
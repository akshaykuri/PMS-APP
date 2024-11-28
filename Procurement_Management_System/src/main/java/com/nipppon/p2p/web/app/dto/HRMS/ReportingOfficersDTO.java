package com.nipppon.p2p.web.app.dto.HRMS;

public class ReportingOfficersDTO{
    private Integer reportingOfficersId;
    private Integer userId;
    private Integer managerId;
    private String empManagerId;
    private Integer manager2Id;
    private String empManager2Id;
    private Integer branchHead;
    private String branchId;
    private String departmentId;
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
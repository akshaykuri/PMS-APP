package com.nipppon.p2p.web.app.entity.HRMS;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "branchmaster")
public class BranchMaster{
    @Id
    @Column(name = "branch_id", length = 50)
    private int branchId;

    @Column(name = "branch_name", length = 100)
    private String branchName;

    @Column(name = "branch_code", length = 50)
    private String branchCode;

    @Column(name = "branch_type_code", nullable = false)
    private int branchTypeCode;

    @Column(name = "reporting_branch_lta", length = 20)
    private String reportingBranchLta;

    @Column(name = "vrf_counto", nullable = false)
    private int vrfCountO;

    @Column(name = "vrf_countoa", nullable = false)
    private int vrfCountOa;

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public int getBranchTypeCode() {
		return branchTypeCode;
	}

	public void setBranchTypeCode(int branchTypeCode) {
		this.branchTypeCode = branchTypeCode;
	}

	public String getReportingBranchLta() {
		return reportingBranchLta;
	}

	public void setReportingBranchLta(String reportingBranchLta) {
		this.reportingBranchLta = reportingBranchLta;
	}

	public int getVrfCountO() {
		return vrfCountO;
	}

	public void setVrfCountO(int vrfCountO) {
		this.vrfCountO = vrfCountO;
	}

	public int getVrfCountOa() {
		return vrfCountOa;
	}

	public void setVrfCountOa(int vrfCountOa) {
		this.vrfCountOa = vrfCountOa;
	}
}
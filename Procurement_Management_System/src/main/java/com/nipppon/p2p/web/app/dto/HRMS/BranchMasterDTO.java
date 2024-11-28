package com.nipppon.p2p.web.app.dto.HRMS;

public class BranchMasterDTO{
    private int branchId;
    private String branchName;
    private String branchCode;
    private int branchTypeCode;
    private String reportingBranchLta;
    private int vrfCountO;
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

	public BranchMasterDTO(int branchId, String branchName, String branchCode, int branchTypeCode, String reportingBranchLta, int vrfCountO, int vrfCountOa) {
		this.branchId = branchId;
		this.branchName = branchName;
		this.branchCode = branchCode;
		this.branchTypeCode = branchTypeCode;
		this.reportingBranchLta = reportingBranchLta;
		this.vrfCountO = vrfCountO;
		this.vrfCountOa = vrfCountOa;
	}
}
package com.nipppon.p2p.web.app.dto.HRMS;

public class SubBranchMasterDTO{
    private int id;
    private String bName;
    private String bNo;
    private String bLocation;
    private String bCode;

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	public String getbNo() {
		return bNo;
	}
	public void setbNo(String bNo) {
		this.bNo = bNo;
	}
	public String getbLocation() {
		return bLocation;
	}
	public void setbLocation(String bLocation) {
		this.bLocation = bLocation;
	}
	public String getbCode() {
		return bCode;
	}
	public void setbCode(String bCode) {
		this.bCode = bCode;
	}

	public SubBranchMasterDTO(int id, String bName, String bNo, String bLocation, String bCode) {
		this.id = id;
		this.bName = bName;
		this.bNo = bNo;
		this.bLocation = bLocation;
		this.bCode = bCode;
	}
}
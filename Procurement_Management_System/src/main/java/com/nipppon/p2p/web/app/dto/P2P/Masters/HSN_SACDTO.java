package com.nipppon.p2p.web.app.dto.P2P.Masters;

import java.time.LocalDateTime;

public class HSN_SACDTO{
	private Long id;
	private String hsnCode;
	private String hsnDesc;
	private String createdBy;
	private LocalDateTime createdOn;
	private int hsnDeleteStatus;
	private String createdByName;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public String getHsnDesc() {
		return hsnDesc;
	}
	public void setHsnDesc(String hsnDesc) {
		this.hsnDesc = hsnDesc;
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
	public int getHsnDeleteStatus() {
		return hsnDeleteStatus;
	}
	public void setHsnDeleteStatus(int hsnDeleteStatus) {
		this.hsnDeleteStatus = hsnDeleteStatus;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public HSN_SACDTO(Long id, String hsnCode, String hsnDesc, String createdBy, LocalDateTime createdOn, int hsnDeleteStatus, String createdByName) {
		this.id					= id;
		this.hsnCode 			= hsnCode;
		this.hsnDesc 			= hsnDesc;
		this.createdBy 			= createdBy;
		this.createdOn 			= createdOn;
		this.hsnDeleteStatus 	= hsnDeleteStatus;
		this.createdByName 		= createdByName;
	}
}
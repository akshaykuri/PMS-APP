package com.nipppon.p2p.web.app.dto.P2P.Masters;

import java.time.LocalDateTime;

public class UOMDTO{
	private Long id;
	private String uomName;
	private String uomDesc;
	private String createdBy;
	private LocalDateTime createdOn;
	private int uomDeleteStatus;
	private String createdByName;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	public String getUomDesc() {
		return uomDesc;
	}
	public void setUomDesc(String uomDesc) {
		this.uomDesc = uomDesc;
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
	public int getUomDeleteStatus() {
		return uomDeleteStatus;
	}
	public void setUomDeleteStatus(int uomDeleteStatus) {
		this.uomDeleteStatus = uomDeleteStatus;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public UOMDTO(Long id, String uomName, String uomDesc, String createdBy, LocalDateTime createdOn, int uomDeleteStatus, String createdByName) {
		this.id 				= id;
		this.uomName 			= uomName;
		this.uomDesc 			= uomDesc;
		this.createdBy 			= createdBy;
		this.createdOn 			= createdOn;
		this.uomDeleteStatus 	= uomDeleteStatus;
		this.createdByName 		= createdByName;
	}
}
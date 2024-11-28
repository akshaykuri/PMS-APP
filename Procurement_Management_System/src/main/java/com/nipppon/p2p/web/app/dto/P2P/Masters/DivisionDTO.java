package com.nipppon.p2p.web.app.dto.P2P.Masters;

import java.time.LocalDateTime;

public class DivisionDTO{
	private Long id;
	private String divisionName;
	private String divisionDesc;
	private String createdBy;
	private LocalDateTime createdOn;
	private int divDeleteStatus;
	private String createdByName;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getDivisionDesc() {
		return divisionDesc;
	}
	public void setDivisionDesc(String divisionDesc) {
		this.divisionDesc = divisionDesc;
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
	public int getDivDeleteStatus() {
		return divDeleteStatus;
	}
	public void setDivDeleteStatus(int divDeleteStatus) {
		this.divDeleteStatus = divDeleteStatus;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public DivisionDTO(Long id, String divisionName, String divisionDesc, String createdBy, LocalDateTime createdOn, int divDeleteStatus, String createdByName) {
		this.id 				= id;
		this.divisionName		= divisionName;
		this.divisionDesc		= divisionDesc;
		this.createdBy			= createdBy;
		this.createdOn			= createdOn;
		this.divDeleteStatus	= divDeleteStatus;
		this.createdByName		= createdByName;
	}
}
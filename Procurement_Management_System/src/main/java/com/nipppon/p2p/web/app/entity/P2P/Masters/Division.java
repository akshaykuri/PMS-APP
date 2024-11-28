package com.nipppon.p2p.web.app.entity.P2P.Masters;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "divisionMaster")
public class Division{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "div_id")
	private Long id;

	private String divisionName;
	private String divisionDesc;
	private String createdBy;
	private LocalDateTime createdOn;
	private int divDeleteStatus;

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
}
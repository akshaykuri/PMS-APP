package com.nipppon.p2p.web.app.entity.P2P.Masters;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hsnsacMaster")
public class HSN_SAC{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hs_id")
	private Long id;

	private String hsnCode;
	private String hsnDesc;
	private String createdBy;
	private LocalDateTime createdOn;
	private int hsnDeleteStatus;

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
}
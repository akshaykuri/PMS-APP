package com.nipppon.p2p.web.app.entity.P2P.Masters;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "uomMaster")
public class UOM{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uom_id")
	private Long id;

	private String uomName;
	private String uomDesc;
	private String createdBy;
	private LocalDateTime createdOn;
	private int uomDeleteStatus;

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
}
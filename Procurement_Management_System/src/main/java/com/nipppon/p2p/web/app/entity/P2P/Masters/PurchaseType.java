package com.nipppon.p2p.web.app.entity.P2P.Masters;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchaseType")
public class PurchaseType{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pt_id")
	private Long id;

	private String purchaseType;
	private String purchaseTypeDesc;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	public String getPurchaseTypeDesc() {
		return purchaseTypeDesc;
	}
	public void setPurchaseTypeDesc(String purchaseTypeDesc) {
		this.purchaseTypeDesc = purchaseTypeDesc;
	}
}
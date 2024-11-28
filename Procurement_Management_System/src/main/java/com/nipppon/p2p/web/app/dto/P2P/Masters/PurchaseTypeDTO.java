package com.nipppon.p2p.web.app.dto.P2P.Masters;

public class PurchaseTypeDTO{
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

	public PurchaseTypeDTO(Long id, String purchaseType, String purchaseTypeDesc) {
		this.id = id;
		this.purchaseType = purchaseType;
		this.purchaseTypeDesc = purchaseTypeDesc;
	}
}
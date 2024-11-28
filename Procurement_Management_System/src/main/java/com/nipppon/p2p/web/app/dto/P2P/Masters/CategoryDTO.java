package com.nipppon.p2p.web.app.dto.P2P.Masters;

import java.time.LocalDateTime;

public class CategoryDTO{
	private Long id;
	private String categoryName;
	private String categoryDesc;
	private String createdBy;
	private LocalDateTime createdOn;
	private int catDeleteStatus;
	private String createdByName;
	private String purchaseType;

	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
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
	public int getCatDeleteStatus() {
		return catDeleteStatus;
	}
	public void setCatDeleteStatus(int catDeleteStatus) {
		this.catDeleteStatus = catDeleteStatus;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public CategoryDTO(Long id, String categoryName, String categoryDesc, String createdBy, LocalDateTime createdOn, int catDeleteStatus, String createdByName, String purchaseType) {
		this.id 				= id;
		this.categoryName 		= categoryName;
		this.categoryDesc 		= categoryDesc;
		this.createdBy 			= createdBy;
		this.createdOn 			= createdOn;
		this.catDeleteStatus 	= catDeleteStatus;
		this.createdByName 		= createdByName;
		this.purchaseType		= purchaseType;
	}
}
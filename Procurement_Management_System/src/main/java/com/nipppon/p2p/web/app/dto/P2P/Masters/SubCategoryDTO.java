package com.nipppon.p2p.web.app.dto.P2P.Masters;

import java.time.LocalDateTime;

public class SubCategoryDTO{
	private Long id;
	private String subCategoryName;
	private String subCategoryDesc;
	private Long categoryId;
	private String createdBy;
	private LocalDateTime createdOn;
	private int subCatDeleteStatus;
	private String createdByName;
	private String categoryName;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public String getSubCategoryDesc() {
		return subCategoryDesc;
	}
	public void setSubCategoryDesc(String subCategoryDesc) {
		this.subCategoryDesc = subCategoryDesc;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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
	public int getSubCatDeleteStatus() {
		return subCatDeleteStatus;
	}
	public void setSubCatDeleteStatus(int subCatDeleteStatus) {
		this.subCatDeleteStatus = subCatDeleteStatus;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public SubCategoryDTO(Long id, String subCategoryName, String subCategoryDesc, Long categoryId, String createdBy, LocalDateTime createdOn, int subCatDeleteStatus, String createdByName, String categoryName) {
		this.id 					= id;
		this.subCategoryName 		= subCategoryName;
		this.subCategoryDesc 		= subCategoryDesc;
		this.categoryId 			= categoryId;
		this.createdBy 				= createdBy;
		this.createdOn 				= createdOn;
		this.subCatDeleteStatus 	= subCatDeleteStatus;
		this.createdByName 			= createdByName;
		this.categoryName 			= categoryName;
	}
}
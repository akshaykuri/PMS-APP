package com.nipppon.p2p.web.app.dto.P2P.Masters;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductDTO{
	private Long id;
	private String productCode;
	private Long hsnsacId;
	private String productName;
	private String productDesc;
	private Long categoryId;
	private Long subCategoryId;
	private int minQty;
	private int maxQty;
	private Long prodDivId;
	private String purchaseType;
	private Long uomId;
	private BigDecimal price;
	private String manufacturer;
	private String model;
	private String createdBy;
	private LocalDateTime createdOn;
	private int prodDeleteStatus;

	private String createdByName;
	private String divisionName;
	private String categoryName;
	private String subCategoryName;
	private String uomName;
	private String hsnSacName;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Long getHsnsacId() {
		return hsnsacId;
	}
	public void setHsnsacId(Long hsnsacId) {
		this.hsnsacId = hsnsacId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public int getMinQty() {
		return minQty;
	}
	public void setMinQty(int minQty) {
		this.minQty = minQty;
	}
	public int getMaxQty() {
		return maxQty;
	}
	public void setMaxQty(int maxQty) {
		this.maxQty = maxQty;
	}
	public Long getProdDivId() {
		return prodDivId;
	}
	public void setProdDivId(Long prodDivId) {
		this.prodDivId = prodDivId;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	public Long getUomId() {
		return uomId;
	}
	public void setUomId(Long uomId) {
		this.uomId = uomId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
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
	public int getProdDeleteStatus() {
		return prodDeleteStatus;
	}
	public void setProdDeleteStatus(int prodDeleteStatus) {
		this.prodDeleteStatus = prodDeleteStatus;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	public String getHsnSacName() {
		return hsnSacName;
	}
	public void setHsnSacName(String hsnSacName) {
		this.hsnSacName = hsnSacName;
	}

	public ProductDTO(Long id, String productCode, Long hsnsacId, String productName, String productDesc, Long categoryId, Long subCategoryId, int minQty, int maxQty, Long prodDivId, String purchaseType, Long uomId, BigDecimal price, String manufacturer, String model, String createdBy, LocalDateTime createdOn, int prodDeleteStatus, String createdByName, String divisionName, String categoryName, String subCategoryName, String uomName, String hsnSacName) {
		this.id 					= id;
		this.productCode 			= productCode;
		this.hsnsacId 				= hsnsacId;
		this.productName 			= productName;
		this.productDesc 			= productDesc;
		this.categoryId 			= categoryId;
		this.subCategoryId 			= subCategoryId;
		this.minQty 				= minQty;
		this.maxQty 				= maxQty;
		this.prodDivId 				= prodDivId;
		this.purchaseType 			= purchaseType;
		this.uomId 					= uomId;
		this.price 					= price;
		this.manufacturer 			= manufacturer;
		this.model 					= model;
		this.createdBy 				= createdBy;
		this.createdOn 				= createdOn;
		this.prodDeleteStatus 		= prodDeleteStatus;
		this.createdByName 			= createdByName;
		this.divisionName 			= divisionName;
		this.categoryName 			= categoryName;
		this.subCategoryName 		= subCategoryName;
		this.uomName 				= uomName;
		this.hsnSacName 			= hsnSacName;
	}
}
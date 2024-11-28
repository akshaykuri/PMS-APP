package com.nipppon.p2p.web.app.dto.P2P.MRPlan;

import java.math.BigDecimal;

public class MR_Plan_ItemDTO{
	private Long id;
	private long mrplId;
	private Long productId;
	private String productDesc;
	private BigDecimal qty;
	private Long uomId;
	private BigDecimal unitPrice;
	private BigDecimal totalPrice;

	private String productName;
	private String uomName;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getMrplId() {
		return mrplId;
	}
	public void setMrplId(long mrplId) {
		this.mrplId = mrplId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	public Long getUomId() {
		return uomId;
	}
	public void setUomId(Long uomId) {
		this.uomId = uomId;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}

	public MR_Plan_ItemDTO(Long id, long mrplId, Long productId, String productDesc, BigDecimal qty, Long uomId, BigDecimal unitPrice, BigDecimal totalPrice, String productName, String uomName) {
		this.id								= id;
		this.mrplId							= mrplId;
		this.productId						= productId;
		this.productDesc					= productDesc;
		this.qty							= qty;
		this.uomId							= uomId;
		this.unitPrice						= unitPrice;
		this.totalPrice						= totalPrice;
		this.productName					= productName;
		this.uomName						= uomName;
	}
}
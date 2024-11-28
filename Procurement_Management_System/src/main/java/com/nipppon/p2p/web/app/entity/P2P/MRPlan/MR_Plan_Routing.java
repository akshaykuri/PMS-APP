package com.nipppon.p2p.web.app.entity.P2P.MRPlan;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mr_plan_routing")
public class MR_Plan_Routing{
	@Id
	@Column(name = "rId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "mrrId", insertable = false, updatable = false)
	private Long mrrId;

	private String status;
	private String recEmpId;
	private String sentEmpId;
	private String mrpRemarks;
	private LocalDateTime processDate;
	private LocalDateTime sentDate;
	private Long appLevelNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "mrrId", nullable = false)
	private MR_Plan mrPlan;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMrrId() {
		return mrrId;
	}
	public void setMrrId(Long mrrId) {
		this.mrrId = mrrId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRecEmpId() {
		return recEmpId;
	}
	public void setRecEmpId(String recEmpId) {
		this.recEmpId = recEmpId;
	}
	public String getSentEmpId() {
		return sentEmpId;
	}
	public void setSentEmpId(String sentEmpId) {
		this.sentEmpId = sentEmpId;
	}
	public String getMrpRemarks() {
		return mrpRemarks;
	}
	public void setMrpRemarks(String mrpRemarks) {
		this.mrpRemarks = mrpRemarks;
	}
	public LocalDateTime getProcessDate() {
		return processDate;
	}
	public void setProcessDate(LocalDateTime processDate) {
		this.processDate = processDate;
	}
	public LocalDateTime getSentDate() {
		return sentDate;
	}
	public void setSentDate(LocalDateTime sentDate) {
		this.sentDate = sentDate;
	}
	public Long getAppLevelNo() {
		return appLevelNo;
	}
	public void setAppLevelNo(Long appLevelNo) {
		this.appLevelNo = appLevelNo;
	}
	public MR_Plan getMrPlan() {
		return mrPlan;
	}
	public void setMrPlan(MR_Plan mrPlan) {
		this.mrPlan = mrPlan;
	}
}
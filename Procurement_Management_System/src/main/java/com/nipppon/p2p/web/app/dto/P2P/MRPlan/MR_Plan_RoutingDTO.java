package com.nipppon.p2p.web.app.dto.P2P.MRPlan;

import java.time.LocalDateTime;

public class MR_Plan_RoutingDTO{
	private Long id;
	private Long mrrId;
	private String status;
	private String recEmpId;
	private String sentEmpId;
	private String mrpRemarks;
	private LocalDateTime processDate;
	private LocalDateTime sentDate;
	private Long appLevelNo;

	private String recEmpName;
	private String sentEmpName;

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
	public String getRecEmpName() {
		return recEmpName;
	}
	public void setRecEmpName(String recEmpName) {
		this.recEmpName = recEmpName;
	}
	public String getSentEmpName() {
		return sentEmpName;
	}
	public void setSentEmpName(String sentEmpName) {
		this.sentEmpName = sentEmpName;
	}

	public MR_Plan_RoutingDTO(Long id, Long mrrId, String status, String recEmpId, String sentEmpId, String mrpRemarks, LocalDateTime processDate, LocalDateTime sentDate, Long appLevelNo, String recEmpName, String sentEmpName) {
		this.id								= id;
		this.mrrId							= mrrId;
		this.status							= status;
		this.recEmpId						= recEmpId;
		this.sentEmpId						= sentEmpId;
		this.mrpRemarks						= mrpRemarks;
		this.processDate					= processDate;
		this.sentDate						= sentDate;
		this.appLevelNo						= appLevelNo;
		this.recEmpName						= recEmpName;
		this.sentEmpName					= sentEmpName;
	}
}
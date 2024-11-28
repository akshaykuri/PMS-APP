package com.nipppon.p2p.web.app.entity.HRMS;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
public class HRMSRole{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="role_id", nullable = false)
	private int role_id;

	@Column(name = "role_code", length = 50)
	private String roleCode;
	
	@Column(name = "role_description", length = 50)
	private String roleDescription;

	public int getRoleId() {
		return role_id;
	}

	public void setRoleId(int role_id) {
		this.role_id = role_id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
}
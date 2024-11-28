package com.nipppon.p2p.web.app.entity.P2P.Masters;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roleMaster")
public class Role{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="role_id")
	private Long id;

	private String roleName;
	private String roleDescription;
    private String roleCreatedBy;
    private LocalDateTime roleCreatedOn;
    private int roleDeleteStatus;

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public String getRoleCreatedBy() {
		return roleCreatedBy;
	}
	public void setRoleCreatedBy(String roleCreatedBy) {
		this.roleCreatedBy = roleCreatedBy;
	}
	public LocalDateTime getRoleCreatedOn() {
		return roleCreatedOn;
	}
	public void setRoleCreatedOn(LocalDateTime roleCreatedOn) {
		this.roleCreatedOn = roleCreatedOn;
	}
	public int getRoleDeleteStatus() {
		return roleDeleteStatus;
	}
	public void setRoleDeleteStatus(int roleDeleteStatus) {
		this.roleDeleteStatus = roleDeleteStatus;
	}
}
package com.nipppon.p2p.web.app.dto.P2P.Masters;

import java.time.LocalDateTime;

public class RoleDTO{
	private Long id;
    private String roleName;
    private String roleDescription;
    private String roleCreatedBy;
    private String roleCreatedByName;
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
    public String getRoleCreatedByName() {
		return roleCreatedByName;
	}
	public void setRoleCreatedByName(String roleCreatedByName) {
		this.roleCreatedByName = roleCreatedByName;
	}

	// Constructor with almost all fields
    public RoleDTO(Long id, String roleName, String roleDescription, String roleCreatedBy, LocalDateTime roleCreatedOn, int roleDeleteStatus, String roleCreatedByName){
        this.id 				= id;
        this.roleName 			= roleName;
        this.roleDescription 	= roleDescription;
        this.roleCreatedBy 		= roleCreatedBy;
        this.roleCreatedOn 		= roleCreatedOn;
        this.roleDeleteStatus 	= roleDeleteStatus;
        this.roleCreatedByName 	= roleCreatedByName;
    }
}
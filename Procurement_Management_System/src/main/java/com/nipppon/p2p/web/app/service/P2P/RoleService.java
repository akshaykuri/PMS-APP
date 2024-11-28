package com.nipppon.p2p.web.app.service.P2P;

import java.util.List;
import java.util.Optional;

import com.nipppon.p2p.web.app.dto.P2P.Masters.AssignUserRoleDTO;
import com.nipppon.p2p.web.app.dto.P2P.Masters.RoleDTO;
import com.nipppon.p2p.web.app.entity.P2P.Masters.AssignUserRole;
import com.nipppon.p2p.web.app.entity.P2P.Masters.Role;

public interface RoleService{
//for Role Master
	Optional<Role> findByRoleNameAndDeleteStatus(String roleName, int deleteStatus);
	Role saveRole(RoleDTO roleDTO);
	Role updateRole(Long id, RoleDTO roleDTO);
	Role getRoleById(Long id);
	List<RoleDTO> getAllRolesWithUserDetails();
	List<RoleDTO> getAllRoles();
	void deleteRole(Long id);
//for Assign User Role
	Optional<AssignUserRole> findByEmpIdAndDeleteStatus(String empId, int deleteStatus);
	AssignUserRole saveAssignUserRole(AssignUserRoleDTO assignUserRoleDTO);
	List<AssignUserRoleDTO> getAllUserRolesWithUserDetailsByRoleId(String bId);
	AssignUserRole getUserDetails(Long id);
	void updateUserRole(Long id ,AssignUserRoleDTO assignUserRoleDTO);
	void enableUser(Long id);
	void deleteUser(Long id);
}
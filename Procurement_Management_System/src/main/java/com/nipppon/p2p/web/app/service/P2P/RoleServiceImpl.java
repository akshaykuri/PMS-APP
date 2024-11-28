package com.nipppon.p2p.web.app.service.P2P;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nipppon.p2p.web.app.dto.P2P.Masters.AssignUserRoleDTO;
import com.nipppon.p2p.web.app.dto.P2P.Masters.RoleDTO;
import com.nipppon.p2p.web.app.entity.HRMS.BranchMaster;
import com.nipppon.p2p.web.app.entity.HRMS.Department;
import com.nipppon.p2p.web.app.entity.HRMS.HRMSRole;
import com.nipppon.p2p.web.app.entity.HRMS.User;
import com.nipppon.p2p.web.app.entity.HRMS.UserRole;
import com.nipppon.p2p.web.app.entity.P2P.Masters.AssignUserRole;
import com.nipppon.p2p.web.app.entity.P2P.Masters.Role;
import com.nipppon.p2p.web.app.repository.HRMS.BranchMasterRepository;
import com.nipppon.p2p.web.app.repository.HRMS.DepartmentRepository;
import com.nipppon.p2p.web.app.repository.HRMS.HRMSRoleRepository;
import com.nipppon.p2p.web.app.repository.HRMS.UserRoleRepository;
import com.nipppon.p2p.web.app.repository.P2P.Masters.AssignUserRoleRepository;
import com.nipppon.p2p.web.app.repository.P2P.Masters.RoleRepository;
import com.nipppon.p2p.web.app.service.HRMS.UserService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private AssignUserRoleRepository assignUserRoleRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private BranchMasterRepository branchMasterRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private HRMSRoleRepository hrmsRoleRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Role saveRole(RoleDTO roleDTO){
		Role role = new Role();
		role.setRoleName(roleDTO.getRoleName());
		role.setRoleDescription(roleDTO.getRoleDescription());
		role.setRoleCreatedBy(roleDTO.getRoleCreatedBy());
		role.setRoleCreatedOn(roleDTO.getRoleCreatedOn());
		role.setRoleDeleteStatus(roleDTO.getRoleDeleteStatus());
		return roleRepository.save(role);
	}

	@Override
    public Optional<Role> findByRoleNameAndDeleteStatus(String roleName, int deleteStatus){
        return roleRepository.findByRoleNameAndRoleDeleteStatus(roleName, deleteStatus);
    }

	@Override
	public Role updateRole(Long id, RoleDTO roleDTO){
		Optional<Role> optionalRole = roleRepository.findById(id);
		if(optionalRole.isPresent()){
			Role role = new Role();
			role.setRoleName(roleDTO.getRoleName());
			role.setRoleDescription(roleDTO.getRoleDescription());
			role.setRoleCreatedBy(roleDTO.getRoleCreatedBy());
			role.setRoleCreatedOn(roleDTO.getRoleCreatedOn());
			role.setRoleDeleteStatus(roleDTO.getRoleDeleteStatus());
			return roleRepository.save(role);
		}else{
			throw new RuntimeException("Role Not Found");
		}
	}

	@Override
	public Role getRoleById(Long id){
		return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role Not Found"));
	}
	
	@Override
	public AssignUserRole getUserDetails(Long id){
		return assignUserRoleRepository.findById(id).orElseThrow(() -> new RuntimeException("User Details Not Found"));
	}

	@Override
	public void deleteRole(Long id){
		Optional<Role> optionalRole = roleRepository.findById(id);
		if(optionalRole.isPresent()){
			Role role = optionalRole.get();
			role.setRoleDeleteStatus(1);
			roleRepository.save(role);
		}else{
			throw new RuntimeException("Role Not Found");
		}
	}

	@Override
	public List<RoleDTO> getAllRolesWithUserDetails(){
		List<Role> roles = roleRepository.findAll();
		return roles.stream()
			.filter(role -> role.getRoleDeleteStatus() != 1) //filter out deleted roles
			.map(role -> {
				Optional<User> userOpt = userService.findByEmpId(role.getRoleCreatedBy());
				return new RoleDTO(
						role.getId(),
						role.getRoleName(),
						role.getRoleDescription(),
						userOpt.map(User :: getEmpId).orElse(null),
						role.getRoleCreatedOn(),
						role.getRoleDeleteStatus(),
						userOpt.map(User :: getUserName).orElse(null)
				);
			}).collect(Collectors.toList());
	}

	@Override
	public List<AssignUserRoleDTO> getAllUserRolesWithUserDetailsByRoleId(String bId){
		Optional<BranchMaster> brOpt = branchMasterRepository.findById(Integer.parseInt(bId));
		int branchTypeCode = brOpt.get().getBranchTypeCode();

		List<AssignUserRole> assignUserRoles = assignUserRoleRepository.findAll();
		return assignUserRoles.stream()
			.filter(userRole -> userRole.getUserRoleDeleteStatus() != 1) //filtering the deleted user details
			.filter(bIds -> {
				return branchTypeCode == 90 || (branchTypeCode != 90 && bId.equalsIgnoreCase(bIds.getEmpBranch()));
			}).map(userRole -> {
				Optional<User> userOpt 	= userService.findByEmpId(userRole.getEmpId());
				Optional<User> userOpt1	= userService.findByEmpId(userRole.getCreatedBy());
				Optional<BranchMaster> bmOpt = branchMasterRepository.findById(Integer.parseInt(userOpt.get().getBranchId()));
				Optional<UserRole> urOpt = userRoleRepository.findById(userOpt.get().getUserId());
				Optional<HRMSRole> rOpt = hrmsRoleRepository.findById(urOpt.get().getRoleId());
				Optional<Department> dOpt = departmentRepository.findById(Integer.parseInt(userOpt.get().getDepartmentId()));
				// Parse and fetch all roles in one query
	            List<Long> roleIds 		= Arrays.stream(userRole.getEmpAssignedRole().split(",")).map(Long::parseLong).collect(Collectors.toList());
	            List<String> roleNames	= roleRepository.findAllById(roleIds).stream().map(Role::getRoleName).collect(Collectors.toList());

				return new AssignUserRoleDTO(
					userRole.getId(),
					userOpt.map(User :: getEmpId).orElse(null),
					userOpt.map(User :: getUserName).orElse(null),
					rOpt.map(role -> role.getRoleCode()).orElse(null),
					dOpt.map(dept -> dept.getDepartmentCode()).orElse(null),
					userOpt.map(User :: getEmail).orElse(null),
					bmOpt.map(brc -> brc.getBranchName()).orElse(null),
					String.join(", ", roleNames),// Combine role names into a single string
					userRole.getRemarks(),
					userOpt1.map(User :: getUserName).orElse(null),
					userRole.getCreatedOn(),
					userRole.getUserRoleDeleteStatus(),
					userRole.getActiveStatus()
				);
			}).collect(Collectors.toList());
	}

	@Override
	public List<RoleDTO> getAllRoles(){
		List<Role> role = roleRepository.findAll();
		return role.stream()
			.filter(roles -> roles.getRoleDeleteStatus() != 1) //filter out deleted roles
			.map(roles -> {
				return new RoleDTO(
					roles.getId(),
					roles.getRoleName(),
					null, null, null, 0, null
				);
			}).collect(Collectors.toList());
	}

	@Override
	public Optional<AssignUserRole> findByEmpIdAndDeleteStatus(String empId, int deleteStatus){
		return assignUserRoleRepository.findByEmpIdAndUserRoleDeleteStatus(empId, deleteStatus);
	}

	@Override
	public AssignUserRole saveAssignUserRole(AssignUserRoleDTO assignUserRoleDTO){
		AssignUserRole assignUserRole = new AssignUserRole();
		assignUserRole.setEmpId(assignUserRoleDTO.getEmpId());
		assignUserRole.setEmpName(assignUserRoleDTO.getEmpName());
		assignUserRole.setEmpDesig(assignUserRoleDTO.getEmpDesig());
		assignUserRole.setEmpDept(assignUserRoleDTO.getEmpDept());
		assignUserRole.setEmpMail(assignUserRoleDTO.getEmpMail());
		assignUserRole.setEmpBranch(assignUserRoleDTO.getEmpBranch());
		assignUserRole.setEmpAssignedRole(assignUserRoleDTO.getEmpAssignedRole());
		assignUserRole.setRemarks(assignUserRoleDTO.getRemarks());
		assignUserRole.setCreatedBy(assignUserRoleDTO.getCreatedBy());
		assignUserRole.setCreatedOn(assignUserRoleDTO.getCreatedOn());
		assignUserRole.setUserRoleDeleteStatus(assignUserRoleDTO.getUserRoleDeleteStatus());
		assignUserRole.setActiveStatus(assignUserRoleDTO.getActiveStatus());
		return assignUserRoleRepository.save(assignUserRole);
	}

	@Override
	public void updateUserRole(Long id ,AssignUserRoleDTO assignUserRoleDTO){
		Optional<AssignUserRole> optionalUserRole = assignUserRoleRepository.findById(id);
		if(optionalUserRole.isPresent()){
			AssignUserRole assignUserRole = optionalUserRole.get();
			assignUserRole.setEmpId(assignUserRoleDTO.getEmpId());
			assignUserRole.setEmpName(assignUserRoleDTO.getEmpName());
			assignUserRole.setEmpDesig(assignUserRoleDTO.getEmpDesig());
			assignUserRole.setEmpDept(assignUserRoleDTO.getEmpDept());
			assignUserRole.setEmpMail(assignUserRoleDTO.getEmpMail());
			assignUserRole.setEmpBranch(assignUserRoleDTO.getEmpBranch());
			assignUserRole.setEmpAssignedRole(assignUserRoleDTO.getEmpAssignedRole());
			assignUserRole.setRemarks(optionalUserRole.get().getRemarks());
			assignUserRole.setCreatedBy(assignUserRoleDTO.getCreatedBy());
			assignUserRole.setCreatedOn(assignUserRoleDTO.getCreatedOn());
			assignUserRole.setUserRoleDeleteStatus(optionalUserRole.get().getUserRoleDeleteStatus());
			assignUserRole.setActiveStatus(optionalUserRole.get().getActiveStatus());
			assignUserRoleRepository.save(assignUserRole);
		}else{
			throw new RuntimeException("User Not Found");
		}
	}

	@Override
	public void enableUser(Long id){
		Optional<AssignUserRole> optionalUser = assignUserRoleRepository.findById(id);
		if(optionalUser.isPresent()){
			AssignUserRole assignUserRole = optionalUser.get();
			assignUserRole.setActiveStatus(assignUserRole.getActiveStatus() == 1 ? 0 : 1);
			assignUserRoleRepository.save(assignUserRole);
		}else{
			throw new RuntimeException("Role Not Found");
		}
	}

	@Override
	public void deleteUser(Long id){
		Optional<AssignUserRole> optionalUser = assignUserRoleRepository.findById(id);
		if(optionalUser.isPresent()){
			AssignUserRole assignUserRole = optionalUser.get();
			assignUserRole.setUserRoleDeleteStatus(1);
			assignUserRole.setActiveStatus(1);
			assignUserRoleRepository.save(assignUserRole);
		}else{
			throw new RuntimeException("Role Not Found");
		}
	}
}
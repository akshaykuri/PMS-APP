package com.nipppon.p2p.web.app.controller.P2P;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nipppon.p2p.web.app.dto.HRMS.UserDTO;
import com.nipppon.p2p.web.app.dto.P2P.Masters.AssignUserRoleDTO;
import com.nipppon.p2p.web.app.dto.P2P.Masters.RoleDTO;
import com.nipppon.p2p.web.app.entity.P2P.Masters.AssignUserRole;
import com.nipppon.p2p.web.app.entity.P2P.Masters.Role;
import com.nipppon.p2p.web.app.service.HRMS.UserService;
import com.nipppon.p2p.web.app.service.P2P.RoleService;

@RestController
@RequestMapping("/api/p2p/role")
public class RoleController{
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;

	@PostMapping("/roles")
	public ResponseEntity<?> createRole(@RequestBody RoleDTO roleDTO){
		Optional<Role> existingRole = roleService.findByRoleNameAndDeleteStatus(roleDTO.getRoleName(),0);
		if(existingRole.isPresent()){
			return new ResponseEntity<>("Role already exists", HttpStatus.CONFLICT);
	    }
		Role savedRole = roleService.saveRole(roleDTO);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }
	
	@PostMapping("/userRoles")
	public ResponseEntity<?> createUserRoles(@RequestBody AssignUserRoleDTO assignUserRoleDTO){
		Optional<AssignUserRole> existingUser = roleService.findByEmpIdAndDeleteStatus(assignUserRoleDTO.getEmpId(), 0);
		if(existingUser.isPresent()){
			return new ResponseEntity<>("User Role is already assigned.", HttpStatus.CONFLICT);
		}
		AssignUserRole savedAssignUserRole = roleService.saveAssignUserRole(assignUserRoleDTO);
		return new ResponseEntity<>(savedAssignUserRole, HttpStatus.CREATED);
	}

	@PutMapping("/userRoles/{id}")
	public ResponseEntity<?> updateUserRoles(@PathVariable Long id, @RequestBody AssignUserRoleDTO assignUserRoleDTO){
		try{
			roleService.updateUserRole(id, assignUserRoleDTO);
			return ResponseEntity.ok("User Details Updated successfully.");
		}catch(UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user details.");
		}
	}

	@GetMapping("/roles")
	public ResponseEntity<List<RoleDTO>> getAllRolesWithUserDetails(){
		List<RoleDTO> listOfRoles = roleService.getAllRolesWithUserDetails();
		return new ResponseEntity<>(listOfRoles, HttpStatus.OK);
	}

	@GetMapping("/userRoles/{bId}")
	public ResponseEntity<?> getAllUserRolesWithUserDetails(@PathVariable String bId){
		List<AssignUserRoleDTO> listOfUserRoles = roleService.getAllUserRolesWithUserDetailsByRoleId(bId);
		return new ResponseEntity<>(listOfUserRoles, HttpStatus.OK);
	}

	@GetMapping("/userDetails/{id}")
	public ResponseEntity<?> getUserDetails(@PathVariable Long id){
		AssignUserRole assignUserRole = roleService.getUserDetails(id);
		return new ResponseEntity<>(assignUserRole, HttpStatus.OK);
	}

	@GetMapping("/allRoles")
	public ResponseEntity<?> getAllRoles(){
		List<RoleDTO> roles = roleService.getAllRoles();
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody RoleDTO roleDTO){
        Role updatedRole = roleService.updateRole(id, roleDTO);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id){
        Role role = roleService.getRoleById(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id){
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/enableUser/{id}")
    public ResponseEntity<?> enableUser(@PathVariable Long id){
    	try{
    	roleService.enableUser(id);
    	return new ResponseEntity<>(HttpStatus.OK);
    	}catch(RuntimeException e){
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
    }
    
    @PutMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
    	try{
    		roleService.deleteUser(id);
    		return new ResponseEntity<>(HttpStatus.OK);
    	}catch(RuntimeException e){
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
    }

    @GetMapping("/empDetails/{id}")
    public ResponseEntity<?> getEmpDetails(@PathVariable String id){
    	UserDTO userDto = userService.getEmpDetails(id);
    	if(userDto != null){
    		return new ResponseEntity<>(userDto, HttpStatus.OK);
    	}else{
    		return new ResponseEntity<>("Selected Employee is not active",HttpStatus.NOT_FOUND);
    	}
    }
}
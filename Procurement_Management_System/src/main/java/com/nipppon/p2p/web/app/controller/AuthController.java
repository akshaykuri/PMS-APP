package com.nipppon.p2p.web.app.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nipppon.p2p.web.app.config.JwtTokenUtil;
import com.nipppon.p2p.web.app.dto.HRMS.UserDTO;
import com.nipppon.p2p.web.app.entity.P2P.Masters.AssignUserRole;
import com.nipppon.p2p.web.app.service.HRMS.UserService;
import com.nipppon.p2p.web.app.service.P2P.RoleService;

@RestController
@RequestMapping("/api/auth")
public class AuthController{
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO loginRequest){
		try{
			// Find user by employee ID
			UserDTO userDto = userService.getEmpDetails(loginRequest.getEmpId());
			Optional<AssignUserRole> p2pUser = roleService.findByEmpIdAndDeleteStatus(userDto.getEmpId(), 0);
			String role;
			int actStat;
			if (p2pUser.isPresent()) {
				role			= p2pUser.get().getEmpAssignedRole();
				actStat			= p2pUser.get().getActiveStatus();
			} else {
				role			= "0";
				actStat			= 0;
			}

			// Check employee status
		    if("no".equalsIgnoreCase(userDto.getEmployeeStatus())){
		    	return ResponseEntity.status(403).body("User is In-Active");
		    }

		    // Validate password
			if(!userDto.getPassword().equals(loginRequest.getPassword())){
				return ResponseEntity.status(401).body("Invalid Credentials");
			}

			if(actStat == 1) {
				return ResponseEntity.status(403).body("Your Account is Disabled, Please Contact Branch IT");				
			}

			// Load user details and generate JWT token
			final UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getEmpId());
			final String jwt = jwtTokenUtil.generateToken(userDetails.getUsername());

			// Prepare a response object with both JWT and employee details
			Map<String, Object> response = new HashMap<>();
			response.put("token", jwt);
			response.put("roles", role);
			response.put("actStat", actStat);
			response.put("empDetails", userDto); // This will include the user details
			return ResponseEntity.ok(response);
		}catch(Exception e){
			System.out.println("error in login mapping is ====> "+e);
		}
		return null;
	}

	// Token validation
	@GetMapping("/validate")
	public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authorizationHeader){
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
			String token = authorizationHeader.substring(7); // Remove "Bearer " prefix
			if(jwtTokenUtil.validateToken(token, jwtTokenUtil.extractUsername(token))){
	            return ResponseEntity.ok("Token is valid");
	        }else{
	            return ResponseEntity.status(401).body("Token is expired or invalid");
	        }
		}else{
	        return ResponseEntity.status(401).body("Authorization header must be provided");
	    }
	}
}
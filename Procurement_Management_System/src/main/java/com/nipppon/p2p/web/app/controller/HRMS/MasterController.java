package com.nipppon.p2p.web.app.controller.HRMS;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nipppon.p2p.web.app.dto.HRMS.BranchMasterDTO;
import com.nipppon.p2p.web.app.dto.HRMS.DepartmentDTO;
import com.nipppon.p2p.web.app.dto.HRMS.SubBranchMasterDTO;
import com.nipppon.p2p.web.app.dto.HRMS.UserDTO;
import com.nipppon.p2p.web.app.service.HRMS.MasterService;
import com.nipppon.p2p.web.app.service.HRMS.UserService;

@RestController
@RequestMapping("/api/hrms/masters")
public class MasterController{
	@Autowired
	private MasterService masterService;
	@Autowired
	private UserService userService;

	@GetMapping("/getEmployees/{branchId}")
	public ResponseEntity<?> getEmployees(@PathVariable String branchId){
		List<UserDTO> listOfEmployees = masterService.getEmployees(branchId);
		return new ResponseEntity<>(listOfEmployees, HttpStatus.OK);
	}

	@GetMapping("/getBranches/{branchTypeCode}")
	public ResponseEntity<?> getBranches(@PathVariable int branchTypeCode){
		List<BranchMasterDTO> listOfBranches = masterService.getBranches(branchTypeCode);
		return new ResponseEntity<>(listOfBranches, HttpStatus.OK);
	}

	@GetMapping("/getOfficeLocs/{b_no}")
	public ResponseEntity<?> getOfficeLocs(@PathVariable String b_no){
		List<SubBranchMasterDTO> listOfOfficeLocations = masterService.getOfficeLocations(b_no);
		return new ResponseEntity<>(listOfOfficeLocations, HttpStatus.OK);
	}

	@GetMapping("/getDepartments")
	public ResponseEntity<?> getDepartments(){
		List<DepartmentDTO> listOfDepartments = masterService.getDepartments();
		return new ResponseEntity<>(listOfDepartments,HttpStatus.OK);
	}

	@GetMapping("/getEmpDetails/{roId}")
	public ResponseEntity<?> getEmpDetails(@PathVariable String roId){
		UserDTO userDTO = userService.getEmpDetails(roId);
    	if(userDTO != null){
    		return new ResponseEntity<>(userDTO, HttpStatus.OK);
    	}else{
    		return new ResponseEntity<>("Selected Employee is not active",HttpStatus.NOT_FOUND);
    	}
	}
}
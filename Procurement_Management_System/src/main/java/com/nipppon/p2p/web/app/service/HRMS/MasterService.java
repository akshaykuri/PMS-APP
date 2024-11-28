package com.nipppon.p2p.web.app.service.HRMS;

import java.util.List;
import com.nipppon.p2p.web.app.dto.HRMS.BranchMasterDTO;
import com.nipppon.p2p.web.app.dto.HRMS.DepartmentDTO;
import com.nipppon.p2p.web.app.dto.HRMS.SubBranchMasterDTO;
import com.nipppon.p2p.web.app.dto.HRMS.UserDTO;

public interface MasterService {
	List<UserDTO> getEmployees(String branchId);
	List<BranchMasterDTO> getBranches(int branchTypeCode);
	List<SubBranchMasterDTO> getOfficeLocations(String b_no);
	List<DepartmentDTO> getDepartments();
}
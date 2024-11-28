package com.nipppon.p2p.web.app.service.HRMS;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nipppon.p2p.web.app.dto.HRMS.UserDTO;
import com.nipppon.p2p.web.app.entity.HRMS.BranchMaster;
import com.nipppon.p2p.web.app.entity.HRMS.Department;
import com.nipppon.p2p.web.app.entity.HRMS.ReportingOfficers;
import com.nipppon.p2p.web.app.entity.HRMS.HRMSRole;
import com.nipppon.p2p.web.app.entity.HRMS.User;
import com.nipppon.p2p.web.app.entity.HRMS.UserRole;
import com.nipppon.p2p.web.app.repository.HRMS.BranchMasterRepository;
import com.nipppon.p2p.web.app.repository.HRMS.DepartmentRepository;
import com.nipppon.p2p.web.app.repository.HRMS.HRMSRoleRepository;
import com.nipppon.p2p.web.app.repository.HRMS.ReportingOfficersRepository;
import com.nipppon.p2p.web.app.repository.HRMS.UserRepository;
import com.nipppon.p2p.web.app.repository.HRMS.UserRoleRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BranchMasterRepository branchMasterRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private HRMSRoleRepository roleRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private ReportingOfficersRepository reportingOfficersRepository;

	@Override
	public Optional<User> findByEmpId(String empId){
		return userRepository.findByEmpId(empId).filter(user -> "yes".equalsIgnoreCase(user.getEmployeeStatus()));//filter user by employeeStatus
	}

	@Override
	public UserDTO getEmpDetails(String empId){
		User user = userRepository.findByEmpId(empId).orElseThrow(() -> new RuntimeException("User Not Found"));
		Optional<BranchMaster> bmOpt = branchMasterRepository.findById(Integer.parseInt(user.getBranchId()));
		Optional<UserRole> urOpt = userRoleRepository.findById(user.getUserId());
		Optional<HRMSRole> rOpt = roleRepository.findById(urOpt.get().getRoleId());
		Optional<Department> dOpt = departmentRepository.findById(Integer.parseInt(user.getDepartmentId()));
		Optional<ReportingOfficers> roOpt = reportingOfficersRepository.findByUserId(user.getUserId());
		return new UserDTO(
			user.getUserId(),
			user.getSalt(),
			user.getFullName(),
			user.getUserName(),
			user.getPassword(),
			user.getEmail(),
			user.getMobileNumber(),
			user.getGender(),
			user.getDob(),
			user.getCountryBirthPlace(),
			user.getMaritalStatus(),
			user.getEmpId(),
			user.getDepartmentId(),
			user.getBranchId(),
			user.getPersonalEmail(),
			user.getPanNumber(),
			user.getAdharNumber(),
			user.getTypeOfEmployee(),
			user.getEmpGrade(),
			user.getDateOfJoining(),
			user.getTraineeId(),
			user.getEmployeeStatus(),
			user.getDateOfReliving(),
			user.getDateOfResign(),
			user.getPfNumber(),
			user.getEsic(),
			user.getMedicalInsurance(),
			user.getEmployeeDept(),
			user.getReportingBranchLta(),
			user.getCompany(),
			user.getUanNumber(),
			user.getExtNo(),
			user.getEmployeeGa(),
			user.getOtpCode(),
			user.getOtpDateTime(),
			user.getModifiedDate(),
			user.getLastPwdChangedDate(),
			user.isWrongAttempt(),
			user.getReportMail(),
			bmOpt.map(BranchMaster :: getBranchId).orElse(null),
			bmOpt.map(BranchMaster :: getBranchTypeCode).orElse(null),
			bmOpt.map(BranchMaster :: getBranchName).orElse(null),
			rOpt.map(HRMSRole :: getRoleId).orElse(null),
			rOpt.map(HRMSRole :: getRoleCode).orElse(null),
			dOpt.map(Department :: getDepartmentId).orElse(null),
			dOpt.map(Department :: getDepartmentName).orElse(null),
			roOpt.map(ReportingOfficers :: getEmpManagerId).orElse(null),
			roOpt.map(ReportingOfficers :: getEmpManager2Id).orElse(null)
		);
	}
}
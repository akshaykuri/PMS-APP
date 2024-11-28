package com.nipppon.p2p.web.app.service.HRMS;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nipppon.p2p.web.app.dto.HRMS.BranchMasterDTO;
import com.nipppon.p2p.web.app.dto.HRMS.DepartmentDTO;
import com.nipppon.p2p.web.app.dto.HRMS.SubBranchMasterDTO;
import com.nipppon.p2p.web.app.dto.HRMS.UserDTO;
import com.nipppon.p2p.web.app.entity.HRMS.BranchMaster;
import com.nipppon.p2p.web.app.entity.HRMS.Department;
import com.nipppon.p2p.web.app.entity.HRMS.SubBranchMaster;
import com.nipppon.p2p.web.app.entity.HRMS.User;
import com.nipppon.p2p.web.app.repository.HRMS.BranchMasterRepository;
import com.nipppon.p2p.web.app.repository.HRMS.DepartmentRepository;
import com.nipppon.p2p.web.app.repository.HRMS.SubBranchMasterRepository;
import com.nipppon.p2p.web.app.repository.HRMS.UserRepository;

@Service
public class MasterServiceImpl implements MasterService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BranchMasterRepository branchMasterRepository;
	@Autowired
	private SubBranchMasterRepository subBranchMasterRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<UserDTO> getEmployees(String branchId){
		Optional<BranchMaster> brOpt = branchMasterRepository.findById(Integer.parseInt(branchId));
		int branchTypeCode = brOpt.get().getBranchTypeCode();

		List<User> listUser = userRepository.findAllOrderByEmpId();
		return listUser.stream()
			.filter(us -> us.getEmployeeStatus().equalsIgnoreCase("yes"))
			.filter(bIds -> {
				return branchTypeCode == 90 || (branchTypeCode != 90 && branchId.equalsIgnoreCase(bIds.getBranchId()));
			})
			.map(user -> {
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
				0,0,null,0,null,0,null,null,null
			);
		}).collect(Collectors.toList());
	}

	@Override
	public List<BranchMasterDTO> getBranches(int branchTypeCode){
		String b_no = "";
		if(branchTypeCode == 10) {
			b_no = "10,12";
		}else if(branchTypeCode == 20) {
			b_no = "20,21,22,23,24,25,26,27,28,88,89";
		}else if(branchTypeCode == 30) {
			b_no = "30,53,32,11";
		}else if(branchTypeCode == 40) {
			b_no = "40,42,43,41,52";
		}else if(branchTypeCode == 54) {
			b_no = "54,44";
		}

		List<BranchMaster> listBranch = null;
		List<String> bNoList = Arrays.asList(b_no.split(","));
		if(branchTypeCode != 90) {
			listBranch = branchMasterRepository.findByBranchTypeCodeInOrderByBranchName(bNoList);
		}else{
			listBranch = branchMasterRepository.findAllOrderByBName();
		}
		return listBranch.stream().map(br -> {
			return new BranchMasterDTO(
				br.getBranchId(),
				br.getBranchName(),
				br.getBranchCode(),
				br.getBranchTypeCode(),
				br.getReportingBranchLta(),
				br.getVrfCountO(),
				br.getVrfCountOa()
			);
		}).collect(Collectors.toList());
	}

	@Override
	public List<SubBranchMasterDTO> getOfficeLocations(String b_no){
		if(b_no.equalsIgnoreCase("10")) {
			b_no = "10,12";
		}else if(b_no.equalsIgnoreCase("20")) {
			b_no = "20,21,22,23,24,25,26,27,28,88,89";
		}else if(b_no.equalsIgnoreCase("30")) {
			b_no = "30,53,32,11";
		}else if(b_no.equalsIgnoreCase("40")) {
			b_no = "40,42,43,41,52";
		}else if(b_no.equalsIgnoreCase("54")) {
			b_no = "54,44";
		}

		List<SubBranchMaster> listSubBranch = null;
		List<String> bNoList = Arrays.asList(b_no.split(","));
		if(!b_no.equalsIgnoreCase("90")) {
			listSubBranch = subBranchMasterRepository.findDistinctByBNoInOrderByBName(bNoList);
		}else{
//			listSubBranch = subBranchMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "bName")); - by sorting and passing here only to repository using import org.springframework.data.domain.Sort;
			listSubBranch = subBranchMasterRepository.findAllOrderByBName();
		}
		
		return listSubBranch.stream().map(sb -> {
			return new SubBranchMasterDTO(
				sb.getId(),
				sb.getbName(),
				sb.getbNo(),
				sb.getbLocation(),
				sb.getbCode()
			);
		}).collect(Collectors.toList());
	}

	@Override
	public List<DepartmentDTO> getDepartments(){
		List<Department> listDepartment = departmentRepository.findAllOrderBydepartmentName();
		return listDepartment.stream().map(d -> {
			return new DepartmentDTO(
				d.getDepartmentId(),
				d.getDepartmentName(),
				d.getDepartmentCode()
			);
		}).collect(Collectors.toList());
	}
}
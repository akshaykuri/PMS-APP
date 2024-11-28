package com.nipppon.p2p.web.app.dto.HRMS;

import java.util.Date;

public class UserDTO{
    private int userId;
    private String salt;
    private String fullName;
    private String userName;
    private String password;
    private String email;
    private String mobileNumber;
    private String gender;
    private Date dob;
    private String countryBirthPlace;
    private String maritalStatus;
    private String empId;
    private String departmentId;
    private String branchId;
    private String personalEmail;
    private String panNumber;
    private String adharNumber;
    private String typeOfEmployee;
    private String empGrade;
    private Date dateOfJoining;
    private String traineeId;
    private String employeeStatus;
    private Date dateOfReliving;
    private Date dateOfResign;
    private String pfNumber;
    private String esic;
    private String medicalInsurance;
    private String employeeDept;
    private String reportingBranchLta;
    private String company;
    private String uanNumber;
    private String extNo;
    private String employeeGa;
    private int otpCode = 0;
    private Date otpDateTime;
    private Date modifiedDate;
    private Date lastPwdChangedDate = new Date();
    private boolean wrongAttempt = false;
    private int reportMail = 0;

    private int branchIdDTO;
    private int branchTypeCodeDTO;
    private String branchNameDTO;
    private int roleIdDTO;
    private String roleCodeDTO;
    private int departmentIdDTO;
    private String departmentNameDTO;
    private String empManagerIdDTO;
    private String empManager2IdDTO;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCountryBirthPlace() {
        return countryBirthPlace;
    }

    public void setCountryBirthPlace(String countryBirthPlace) {
        this.countryBirthPlace = countryBirthPlace;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getAdharNumber() {
        return adharNumber;
    }

    public void setAdharNumber(String adharNumber) {
        this.adharNumber = adharNumber;
    }

    public String getTypeOfEmployee() {
        return typeOfEmployee;
    }

    public void setTypeOfEmployee(String typeOfEmployee) {
        this.typeOfEmployee = typeOfEmployee;
    }

    public String getEmpGrade() {
        return empGrade;
    }

    public void setEmpGrade(String empGrade) {
        this.empGrade = empGrade;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(String traineeId) {
        this.traineeId = traineeId;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public Date getDateOfReliving() {
        return dateOfReliving;
    }

    public void setDateOfReliving(Date dateOfReliving) {
        this.dateOfReliving = dateOfReliving;
    }

    public Date getDateOfResign() {
        return dateOfResign;
    }

    public void setDateOfResign(Date dateOfResign) {
        this.dateOfResign = dateOfResign;
    }

    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    public String getEsic() {
        return esic;
    }

    public void setEsic(String esic) {
        this.esic = esic;
    }

    public String getMedicalInsurance() {
        return medicalInsurance;
    }

    public void setMedicalInsurance(String medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    public String getEmployeeDept() {
        return employeeDept;
    }

    public void setEmployeeDept(String employeeDept) {
        this.employeeDept = employeeDept;
    }

    public String getReportingBranchLta() {
        return reportingBranchLta;
    }

    public void setReportingBranchLta(String reportingBranchLta) {
        this.reportingBranchLta = reportingBranchLta;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUanNumber() {
        return uanNumber;
    }

    public void setUanNumber(String uanNumber) {
        this.uanNumber = uanNumber;
    }

    public String getExtNo() {
        return extNo;
    }

    public void setExtNo(String extNo) {
        this.extNo = extNo;
    }

    public String getEmployeeGa() {
        return employeeGa;
    }

    public void setEmployeeGa(String employeeGa) {
        this.employeeGa = employeeGa;
    }

    public int getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(int otpCode) {
        this.otpCode = otpCode;
    }

    public Date getOtpDateTime() {
        return otpDateTime;
    }

    public void setOtpDateTime(Date otpDateTime) {
        this.otpDateTime = otpDateTime;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getLastPwdChangedDate() {
        return lastPwdChangedDate;
    }

    public void setLastPwdChangedDate(Date lastPwdChangedDate) {
        this.lastPwdChangedDate = lastPwdChangedDate;
    }

    public boolean isWrongAttempt() {
        return wrongAttempt;
    }

    public void setWrongAttempt(boolean wrongAttempt) {
        this.wrongAttempt = wrongAttempt;
    }

    public int getReportMail() {
        return reportMail;
    }

    public void setReportMail(int reportMail) {
        this.reportMail = reportMail;
    }

	public int getBranchIdDTO() {
		return branchIdDTO;
	}

	public void setBranchIdDTO(int branchIdDTO) {
		this.branchIdDTO = branchIdDTO;
	}

	public int getBranchTypeCodeDTO() {
		return branchTypeCodeDTO;
	}

	public void setBranchTypeCodeDTO(int branchTypeCodeDTO) {
		this.branchTypeCodeDTO = branchTypeCodeDTO;
	}

	public String getBranchNameDTO() {
		return branchNameDTO;
	}

	public void setBranchNameDTO(String branchNameDTO) {
		this.branchNameDTO = branchNameDTO;
	}

	public int getRoleIdDTO() {
		return roleIdDTO;
	}

	public void setRoleIdDTO(int roleIdDTO) {
		this.roleIdDTO = roleIdDTO;
	}

	public String getRoleCodeDTO() {
		return roleCodeDTO;
	}

	public void setRoleCodeDTO(String roleCodeDTO) {
		this.roleCodeDTO = roleCodeDTO;
	}

	public int getDepartmentIdDTO() {
		return departmentIdDTO;
	}

	public void setDepartmentIdDTO(int departmentIdDTO) {
		this.departmentIdDTO = departmentIdDTO;
	}

	public String getDepartmentNameDTO() {
		return departmentNameDTO;
	}

	public void setDepartmentNameDTO(String departmentNameDTO) {
		this.departmentNameDTO = departmentNameDTO;
	}

	public String getEmpManagerIdDTO() {
		return empManagerIdDTO;
	}

	public void setEmpManagerIdDTO(String empManagerIdDTO) {
		this.empManagerIdDTO = empManagerIdDTO;
	}

	public String getEmpManager2IdDTO() {
		return empManager2IdDTO;
	}

	public void setEmpManager2IdDTO(String empManager2IdDTO) {
		this.empManager2IdDTO = empManager2IdDTO;
	}

	public UserDTO(int userId, String salt, String fullName, String userName, String password, String email,
			String mobileNumber, String gender, Date dob, String countryBirthPlace, String maritalStatus, String empId,
			String departmentId, String branchId, String personalEmail, String panNumber, String adharNumber,
			String typeOfEmployee, String empGrade, Date dateOfJoining, String traineeId, String employeeStatus,
			Date dateOfReliving, Date dateOfResign, String pfNumber, String esic, String medicalInsurance,
			String employeeDept, String reportingBranchLta, String company, String uanNumber, String extNo,
			String employeeGa, int otpCode, Date otpDateTime, Date modifiedDate, Date lastPwdChangedDate,
			boolean wrongAttempt, int reportMail, int branchIdDTO, int branchTypeCodeDTO, String branchNameDTO,
			int roleIdDTO, String roleCodeDTO, int departmentIdDTO, String departmentNameDTO, String empManagerIdDTO,
			String empManager2IdDTO) {
		super();
		this.userId = userId;
		this.salt = salt;
		this.fullName = fullName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.dob = dob;
		this.countryBirthPlace = countryBirthPlace;
		this.maritalStatus = maritalStatus;
		this.empId = empId;
		this.departmentId = departmentId;
		this.branchId = branchId;
		this.personalEmail = personalEmail;
		this.panNumber = panNumber;
		this.adharNumber = adharNumber;
		this.typeOfEmployee = typeOfEmployee;
		this.empGrade = empGrade;
		this.dateOfJoining = dateOfJoining;
		this.traineeId = traineeId;
		this.employeeStatus = employeeStatus;
		this.dateOfReliving = dateOfReliving;
		this.dateOfResign = dateOfResign;
		this.pfNumber = pfNumber;
		this.esic = esic;
		this.medicalInsurance = medicalInsurance;
		this.employeeDept = employeeDept;
		this.reportingBranchLta = reportingBranchLta;
		this.company = company;
		this.uanNumber = uanNumber;
		this.extNo = extNo;
		this.employeeGa = employeeGa;
		this.otpCode = otpCode;
		this.otpDateTime = otpDateTime;
		this.modifiedDate = modifiedDate;
		this.lastPwdChangedDate = lastPwdChangedDate;
		this.wrongAttempt = wrongAttempt;
		this.reportMail = reportMail;
		this.branchIdDTO = branchIdDTO;
		this.branchTypeCodeDTO = branchTypeCodeDTO;
		this.branchNameDTO = branchNameDTO;
		this.roleIdDTO = roleIdDTO;
		this.roleCodeDTO = roleCodeDTO;
		this.departmentIdDTO = departmentIdDTO;
		this.departmentNameDTO = departmentNameDTO;
		this.empManagerIdDTO = empManagerIdDTO;
		this.empManager2IdDTO = empManager2IdDTO;
	}
}
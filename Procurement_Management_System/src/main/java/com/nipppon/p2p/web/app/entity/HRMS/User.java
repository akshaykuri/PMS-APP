package com.nipppon.p2p.web.app.entity.HRMS;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "user")
public class User{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
	private int userId;

    @Column(name = "salt", length = 100)
    private String salt;

    @Column(name = "full_name", length = 200)
    private String fullName;

    @Column(name = "user_name", length = 50)
    private String userName;

    @Column(name = "password", length = 100, columnDefinition = "varchar(100) CHARACTER SET latin1 COLLATE latin1_bin")
    private String password;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "mobile_number", length = 50)
    private String mobileNumber;

    @Column(name = "gender", length = 1)
    private String gender;

    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(name = "country_birth_place", length = 100)
    private String countryBirthPlace;

    @Column(name = "marital_status", length = 50)
    private String maritalStatus;

    @Column(name = "emp_id", length = 200)
    private String empId;

    @Column(name = "department_id", length = 50, insertable = false, updatable = false)
    private String departmentId;

    @Column(name = "branch_id", length = 50, insertable = false, updatable = false)
    private String branchId;

    @Column(name = "personal_email", length = 200)
    private String personalEmail;

    @Column(name = "pan_number", length = 200)
    private String panNumber;

    @Column(name = "adhar_number", length = 100)
    private String adharNumber;

    @Column(name = "type_of_employee", length = 100)
    private String typeOfEmployee;

    @Column(name = "emp_grade", length = 100)
    private String empGrade;

    @Column(name = "date_of_joining")
    @Temporal(TemporalType.DATE)
    private Date dateOfJoining;

    @Column(name = "trainee_id", length = 100)
    private String traineeId;

    @Column(name = "employee_status", length = 10)
    private String employeeStatus;

    @Column(name = "date_of_reliving")
    @Temporal(TemporalType.DATE)
    private Date dateOfReliving;

    @Column(name = "date_of_resign")
    @Temporal(TemporalType.DATE)
    private Date dateOfResign;

    @Column(name = "pf_number", length = 100)
    private String pfNumber;

    @Column(name = "esic", length = 100)
    private String esic;

    @Column(name = "medical_insurance", length = 100)
    private String medicalInsurance;

    @Column(name = "employee_dept", length = 500)
    private String employeeDept;

    @Column(name = "reporting_branch_lta", length = 50)
    private String reportingBranchLta;

    @Column(name = "company", length = 50)
    private String company;

    @Column(name = "uan_number", length = 200)
    private String uanNumber;

    @Column(name = "ext_no", length = 50)
    private String extNo;

    @Column(name = "employee_ga", length = 20)
    private String employeeGa;

    @Column(name = "otpCode", nullable = false)
    private int otpCode = 0;

    @Column(name = "otpDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date otpDateTime;

    @Column(name = "modifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Column(name = "lastpwdchangeddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPwdChangedDate = new Date();

    @Column(name = "wrongAttempt", nullable = false)
    private boolean wrongAttempt = false;

    @Column(name = "ReportMail", nullable = false)
    private int reportMail = 0;

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
}
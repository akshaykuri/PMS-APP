package com.nipppon.p2p.web.app.entity.CRF;

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
@Table(name = "form_customer_details")
public class Customer{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "", nullable = false, length = 20)
	private String branchCode;

    @Column(name = "branchName", length = 50)
    private String branchName;

    @Column(name = "idindex", nullable = false)
    private int idIndex;

    @Column(name = "CRF_Form_Type", length = 10)
    private String crfFormType;

    @Column(name = "form_no", nullable = false, length = 50)
    private String formNo;

    @Column(name = "customerName", nullable = false, length = 200)
    private String customerName;

    @Column(name = "customerPanNo", nullable = false, length = 30)
    private String customerPanNo;

    @Column(name = "groupName", length = 200)
    private String groupName;

    @Column(name = "majorProductService", length = 100)
    private String majorProductService;

    @Column(name = "DORegistration")
    @Temporal(TemporalType.DATE)
    private Date doRegistration;

    @Column(name = "farmType", length = 50)
    private String farmType;

    @Column(name = "detailsOfDirector", length = 300)
    private String detailsOfDirector;

    @Column(name = "contactPerson1Name", length = 50)
    private String contactPerson1Name;

    @Column(name = "contactPerson1ContactNo", length = 50)
    private String contactPerson1ContactNo;

    @Column(name = "contactPerson1EmailID", length = 200)
    private String contactPerson1EmailID;

    @Column(name = "contactPerson2Name", length = 50)
    private String contactPerson2Name;

    @Column(name = "contactPerson2ContactNo", length = 50)
    private String contactPerson2ContactNo;

    @Column(name = "contactPerson2EmailID", length = 50)
    private String contactPerson2EmailID;

    @Column(name = "billingCityName", length = 50)
    private String billingCityName;

    @Column(name = "billingStatName", length = 50)
    private String billingStatName;

    @Column(name = "billingPINCode", length = 20)
    private String billingPINCode;

    @Column(name = "billingContactNo", length = 50)
    private String billingContactNo;

    @Column(name = "correspondanceCityName", length = 100)
    private String correspondanceCityName;

    @Column(name = "correspondanceStatName", length = 50)
    private String correspondanceStatName;

    @Column(name = "correspondancePINCode", length = 20)
    private String correspondancePINCode;

    @Column(name = "correspondanceContactNo", length = 50)
    private String correspondanceContactNo;

    @Column(name = "registeredCityName", length = 100)
    private String registeredCityName;

    @Column(name = "registeredStatName", length = 50)
    private String registeredStatName;

    @Column(name = "registeredPINCode", length = 20)
    private String registeredPINCode;

    @Column(name = "registeredContactNo", length = 50)
    private String registeredContactNo;

    @Column(name = "TANNo", length = 30)
    private String tanNo;

    @Column(name = "GSTNo", length = 40)
    private String gstNo;

    @Column(name = "additionalInfo", length = 500)
    private String additionalInfo;

    @Column(name = "brifDeatailProBussiness", length = 5000)
    private String briefDetailProBussiness;

    @Column(name = "creditFacilityDay", length = 20)
    private String creditFacilityDay;

    @Column(name = "closeFormStatus", nullable = false)
    private int closeFormStatus = 0;

    @Column(name = "createrEMPID", nullable = false, length = 20)
    private String createrEmpId;

    @Column(name = "createDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "billAddress", length = 500)
    private String billAddress;

    @Column(name = "corresAddress", length = 500)
    private String corresAddress;

    @Column(name = "regisAddress", length = 500)
    private String regisAddress;

    @Column(name = "updatedEmpId", length = 55)
    private String updatedEmpId;

    @Column(name = "updatedEmpName", length = 100)
    private String updatedEmpName;

    @Column(name = "updatedDate", length = 100)
    private String updatedDate;

    @Column(name = "Old_CRF_No", length = 100)
    private String oldCrfNo;

    @Column(name = "activity", length = 50)
    private String activity;

    @Column(name = "typeOfInd", length = 50)
    private String typeOfInd;

    @Column(name = "panRemarks", length = 100)
    private String panRemarks;

    @Column(name = "CINNo", length = 50)
    private String cinNo;

    @Column(name = "IECCode", length = 50)
    private String iecCode;

    @Column(name = "picempid", length = 10)
    private String picEmpId;

    @Column(name = "picname", length = 50)
    private String picName;

    @Column(name = "picbranch", length = 30)
    private String picBranch;

    @Column(name = "cnameUpdatedBy", length = 10)
    private Integer cnameUpdatedBy;

    @Column(name = "cfpicname", length = 50)
    private String cfPicName;

    @Column(name = "cfpicno", length = 20)
    private String cfPicNo;

    @Column(name = "cfpicmail", length = 50)
    private String cfPicMail;

    @Column(name = "contactPerson3Name", length = 50)
    private String contactPerson3Name;

    @Column(name = "contactPerson3ContactNo", length = 50)
    private String contactPerson3ContactNo;

    @Column(name = "contactPerson3EmailID", length = 200)
    private String contactPerson3EmailId;

    @Column(name = "creditPeriod", length = 45)
    private String creditPeriod;

    @Column(name = "creditAmount", length = 45)
    private String creditAmount;

    @Column(name = "registeredCountryName", length = 100)
    private String registeredCountryName;

    @Column(name = "finalApprovedStatusCRFF", length = 45)
    private String finalApprovedStatusCrff = "0";

    @Column(name = "billingCountryName", length = 100)
    private String billingCountryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getIdIndex() {
        return idIndex;
    }

    public void setIdIndex(int idIndex) {
        this.idIndex = idIndex;
    }

    public String getCrfFormType() {
        return crfFormType;
    }

    public void setCrfFormType(String crfFormType) {
        this.crfFormType = crfFormType;
    }

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPanNo() {
        return customerPanNo;
    }

    public void setCustomerPanNo(String customerPanNo) {
        this.customerPanNo = customerPanNo;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMajorProductService() {
        return majorProductService;
    }

    public void setMajorProductService(String majorProductService) {
        this.majorProductService = majorProductService;
    }

    public Date getDoRegistration() {
        return doRegistration;
    }

    public void setDoRegistration(Date doRegistration) {
        this.doRegistration = doRegistration;
    }

    public String getFarmType() {
        return farmType;
    }

    public void setFarmType(String farmType) {
        this.farmType = farmType;
    }

    public String getDetailsOfDirector() {
        return detailsOfDirector;
    }

    public void setDetailsOfDirector(String detailsOfDirector) {
        this.detailsOfDirector = detailsOfDirector;
    }

    public String getContactPerson1Name() {
        return contactPerson1Name;
    }

    public void setContactPerson1Name(String contactPerson1Name) {
        this.contactPerson1Name = contactPerson1Name;
    }

    public String getContactPerson1ContactNo() {
        return contactPerson1ContactNo;
    }

    public void setContactPerson1ContactNo(String contactPerson1ContactNo) {
        this.contactPerson1ContactNo = contactPerson1ContactNo;
    }

    public String getContactPerson1EmailID() {
        return contactPerson1EmailID;
    }

    public void setContactPerson1EmailID(String contactPerson1EmailID) {
        this.contactPerson1EmailID = contactPerson1EmailID;
    }

    public String getContactPerson2Name() {
        return contactPerson2Name;
    }

    public void setContactPerson2Name(String contactPerson2Name) {
        this.contactPerson2Name = contactPerson2Name;
    }

    public String getContactPerson2ContactNo() {
        return contactPerson2ContactNo;
    }

    public void setContactPerson2ContactNo(String contactPerson2ContactNo) {
        this.contactPerson2ContactNo = contactPerson2ContactNo;
    }

    public String getContactPerson2EmailID() {
        return contactPerson2EmailID;
    }

    public void setContactPerson2EmailID(String contactPerson2EmailID) {
        this.contactPerson2EmailID = contactPerson2EmailID;
    }

    public String getBillingCityName() {
        return billingCityName;
    }

    public void setBillingCityName(String billingCityName) {
        this.billingCityName = billingCityName;
    }

    public String getBillingStatName() {
        return billingStatName;
    }

    public void setBillingStatName(String billingStatName) {
        this.billingStatName = billingStatName;
    }

    public String getBillingPINCode() {
        return billingPINCode;
    }

    public void setBillingPINCode(String billingPINCode) {
        this.billingPINCode = billingPINCode;
    }

    public String getBillingContactNo() {
        return billingContactNo;
    }

    public void setBillingContactNo(String billingContactNo) {
        this.billingContactNo = billingContactNo;
    }

    public String getCorrespondanceCityName() {
        return correspondanceCityName;
    }

    public void setCorrespondanceCityName(String correspondanceCityName) {
        this.correspondanceCityName = correspondanceCityName;
    }

    public String getCorrespondanceStatName() {
        return correspondanceStatName;
    }

    public void setCorrespondanceStatName(String correspondanceStatName) {
        this.correspondanceStatName = correspondanceStatName;
    }

    public String getCorrespondancePINCode() {
        return correspondancePINCode;
    }

    public void setCorrespondancePINCode(String correspondancePINCode) {
        this.correspondancePINCode = correspondancePINCode;
    }

    public String getCorrespondanceContactNo() {
        return correspondanceContactNo;
    }

    public void setCorrespondanceContactNo(String correspondanceContactNo) {
        this.correspondanceContactNo = correspondanceContactNo;
    }

    public String getRegisteredCityName() {
        return registeredCityName;
    }

    public void setRegisteredCityName(String registeredCityName) {
        this.registeredCityName = registeredCityName;
    }

    public String getRegisteredStatName() {
        return registeredStatName;
    }

    public void setRegisteredStatName(String registeredStatName) {
        this.registeredStatName = registeredStatName;
    }

    public String getRegisteredPINCode() {
        return registeredPINCode;
    }

    public void setRegisteredPINCode(String registeredPINCode) {
        this.registeredPINCode = registeredPINCode;
    }

    public String getRegisteredContactNo() {
        return registeredContactNo;
    }

    public void setRegisteredContactNo(String registeredContactNo) {
        this.registeredContactNo = registeredContactNo;
    }

    public String getTanNo() {
        return tanNo;
    }

    public void setTanNo(String tanNo) {
        this.tanNo = tanNo;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getBriefDetailProBussiness() {
        return briefDetailProBussiness;
    }

    public void setBriefDetailProBussiness(String briefDetailProBussiness) {
        this.briefDetailProBussiness = briefDetailProBussiness;
    }

    public String getCreditFacilityDay() {
        return creditFacilityDay;
    }

    public void setCreditFacilityDay(String creditFacilityDay) {
        this.creditFacilityDay = creditFacilityDay;
    }

    public int getCloseFormStatus() {
        return closeFormStatus;
    }

    public void setCloseFormStatus(int closeFormStatus) {
        this.closeFormStatus = closeFormStatus;
    }

    public String getCreaterEmpId() {
        return createrEmpId;
    }

    public void setCreaterEmpId(String createrEmpId) {
        this.createrEmpId = createrEmpId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getBillAddress() {
        return billAddress;
    }

    public void setBillAddress(String billAddress) {
        this.billAddress = billAddress;
    }

    public String getCorresAddress() {
        return corresAddress;
    }

    public void setCorresAddress(String corresAddress) {
        this.corresAddress = corresAddress;
    }

    public String getRegisAddress() {
        return regisAddress;
    }

    public void setRegisAddress(String regisAddress) {
        this.regisAddress = regisAddress;
    }

    public String getUpdatedEmpId() {
        return updatedEmpId;
    }

    public void setUpdatedEmpId(String updatedEmpId) {
        this.updatedEmpId = updatedEmpId;
    }

    public String getUpdatedEmpName() {
        return updatedEmpName;
    }

    public void setUpdatedEmpName(String updatedEmpName) {
        this.updatedEmpName = updatedEmpName;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getOldCrfNo() {
        return oldCrfNo;
    }

    public void setOldCrfNo(String oldCrfNo) {
        this.oldCrfNo = oldCrfNo;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getTypeOfInd() {
        return typeOfInd;
    }

    public void setTypeOfInd(String typeOfInd) {
        this.typeOfInd = typeOfInd;
    }

    public String getPanRemarks() {
        return panRemarks;
    }

    public void setPanRemarks(String panRemarks) {
        this.panRemarks = panRemarks;
    }

    public String getCinNo() {
        return cinNo;
    }

    public void setCinNo(String cinNo) {
        this.cinNo = cinNo;
    }

    public String getIecCode() {
        return iecCode;
    }

    public void setIecCode(String iecCode) {
        this.iecCode = iecCode;
    }

    public String getPicEmpId() {
        return picEmpId;
    }

    public void setPicEmpId(String picEmpId) {
        this.picEmpId = picEmpId;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getPicBranch() {
        return picBranch;
    }

    public void setPicBranch(String picBranch) {
        this.picBranch = picBranch;
    }

    public Integer getCnameUpdatedBy() {
        return cnameUpdatedBy;
    }

    public void setCnameUpdatedBy(Integer cnameUpdatedBy) {
        this.cnameUpdatedBy = cnameUpdatedBy;
    }

    public String getCfPicName() {
        return cfPicName;
    }

    public void setCfPicName(String cfPicName) {
        this.cfPicName = cfPicName;
    }

    public String getCfPicNo() {
        return cfPicNo;
    }

    public void setCfPicNo(String cfPicNo) {
        this.cfPicNo = cfPicNo;
    }

    public String getCfPicMail() {
        return cfPicMail;
    }

    public void setCfPicMail(String cfPicMail) {
        this.cfPicMail = cfPicMail;
    }

    public String getContactPerson3Name() {
        return contactPerson3Name;
    }

    public void setContactPerson3Name(String contactPerson3Name) {
        this.contactPerson3Name = contactPerson3Name;
    }

    public String getContactPerson3ContactNo() {
        return contactPerson3ContactNo;
    }

    public void setContactPerson3ContactNo(String contactPerson3ContactNo) {
        this.contactPerson3ContactNo = contactPerson3ContactNo;
    }

    public String getContactPerson3EmailId() {
        return contactPerson3EmailId;
    }

    public void setContactPerson3EmailId(String contactPerson3EmailId) {
        this.contactPerson3EmailId = contactPerson3EmailId;
    }

    public String getCreditPeriod() {
        return creditPeriod;
    }

    public void setCreditPeriod(String creditPeriod) {
        this.creditPeriod = creditPeriod;
    }

    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getRegisteredCountryName() {
        return registeredCountryName;
    }

    public void setRegisteredCountryName(String registeredCountryName) {
        this.registeredCountryName = registeredCountryName;
    }

    public String getFinalApprovedStatusCrff() {
        return finalApprovedStatusCrff;
    }

    public void setFinalApprovedStatusCrff(String finalApprovedStatusCrff) {
        this.finalApprovedStatusCrff = finalApprovedStatusCrff;
    }

    public String getBillingCountryName() {
        return billingCountryName;
    }

    public void setBillingCountryName(String billingCountryName) {
        this.billingCountryName = billingCountryName;
    }
}
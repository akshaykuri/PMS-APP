package com.nipppon.p2p.web.app.dto.VRF;

import java.util.Date;

public class VendorDTO{
    private int id;
    private String vrfId;
    private String vendorName;
    private String groupName;
    private String vendorAddress;
    private String vendorPhone;
    private String vendorEmail;
    private String website;
    private String contactPersonName;
    private String contactPersonDesignation;
    private String contactPersonPhone;
    private String contactPersonEmail;
    private String branchId;
    private String branchName;
    private String oneTimeVendor = "0";
    private String natureOfService;
    private Double appAmtOfPaymentPerMonth;
    private Integer msmed;
    private String panNumber;
    private String panHolderName;
    private String partnerOrProprietorName;
    private Integer vrfReceived;
    private Integer transportDeclarationReceived;
    private Integer vendorDeclarationReceived;
    private Integer msmedFormReceived;
    private Integer panCopyReceived;
    private Integer addressProofReceived;
    private Integer agreementReceived;
    private String remarks;
    private String vendorNewinsCode;
    private String creditPeriod;
    private String tdsSection;
    private Double tdsPercentage;
    private Date codeCreatedDate;
    private String newinCodeCreatedBy;
    private String msmedNumber;
    private String additionalDetails;
    private String approvalStatus;
    private Date vrfEnteredDate = new Date();
    private String initiator;
    private String lastModifiedBy;
    private Integer allDocReceived;
    private String segmentOrDept;
    private String serviceLocation;
    private String taxVat;
    private String vat;
    private String gst;
    private String gstProvisionalId;
    private String vendorType;
    private String companyPartnershipProprietorship;
    private String ciNumber;
    private Double approxAmountPerMonth;
    private String loiPoContract;
    private String slNumberOfPo;
    private String slNumberOfContract;
    private String openClosePeriodOneTime;
    private String fromDate;
    private String toDate;
    private String poReceived;
    private String state;
    private String pincode;
    private String proprietorDeclaration;
    private String gstApplicationNo;
    private String gstinRegAckDocs;
    private String loiReceived;
    private String gstReg;
    private String reasonForNonReg;
    private String gstRegState;
    private String regAddress;
    private String regService;
    private String hsnSacCode;
    private String hsnCode;
    private String legalName;
    private String nameOfProduct;
    private String tradeName;
    private String apType;
    private Integer initiatorBranch;
    private String gstr1Anx1Filing;
    private String hoCode;
    private String hoCodeCreatedDate;
    private String hoCodeUpdatedBy;
    private String businessType;
    private String enterprisType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVrfId() {
        return vrfId;
    }

    public void setVrfId(String vrfId) {
        this.vrfId = vrfId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getVendorPhone() {
        return vendorPhone;
    }

    public void setVendorPhone(String vendorPhone) {
        this.vendorPhone = vendorPhone;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonDesignation() {
        return contactPersonDesignation;
    }

    public void setContactPersonDesignation(String contactPersonDesignation) {
        this.contactPersonDesignation = contactPersonDesignation;
    }

    public String getContactPersonPhone() {
        return contactPersonPhone;
    }

    public void setContactPersonPhone(String contactPersonPhone) {
        this.contactPersonPhone = contactPersonPhone;
    }

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }

    public void setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getOneTimeVendor() {
        return oneTimeVendor;
    }

    public void setOneTimeVendor(String oneTimeVendor) {
        this.oneTimeVendor = oneTimeVendor;
    }

    public String getNatureOfService() {
        return natureOfService;
    }

    public void setNatureOfService(String natureOfService) {
        this.natureOfService = natureOfService;
    }

    public Double getAppAmtOfPaymentPerMonth() {
        return appAmtOfPaymentPerMonth;
    }

    public void setAppAmtOfPaymentPerMonth(Double appAmtOfPaymentPerMonth) {
        this.appAmtOfPaymentPerMonth = appAmtOfPaymentPerMonth;
    }

    public Integer getMsmed() {
        return msmed;
    }

    public void setMsmed(Integer msmed) {
        this.msmed = msmed;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getPanHolderName() {
        return panHolderName;
    }

    public void setPanHolderName(String panHolderName) {
        this.panHolderName = panHolderName;
    }

    public String getPartnerOrProprietorName() {
        return partnerOrProprietorName;
    }

    public void setPartnerOrProprietorName(String partnerOrProprietorName) {
        this.partnerOrProprietorName = partnerOrProprietorName;
    }

    public Integer getVrfReceived() {
        return vrfReceived;
    }

    public void setVrfReceived(Integer vrfReceived) {
        this.vrfReceived = vrfReceived;
    }

    public Integer getTransportDeclarationReceived() {
        return transportDeclarationReceived;
    }

    public void setTransportDeclarationReceived(Integer transportDeclarationReceived) {
        this.transportDeclarationReceived = transportDeclarationReceived;
    }

    public Integer getVendorDeclarationReceived() {
        return vendorDeclarationReceived;
    }

    public void setVendorDeclarationReceived(Integer vendorDeclarationReceived) {
        this.vendorDeclarationReceived = vendorDeclarationReceived;
    }

    public Integer getMsmedFormReceived() {
        return msmedFormReceived;
    }

    public void setMsmedFormReceived(Integer msmedFormReceived) {
        this.msmedFormReceived = msmedFormReceived;
    }

    public Integer getPanCopyReceived() {
        return panCopyReceived;
    }

    public void setPanCopyReceived(Integer panCopyReceived) {
        this.panCopyReceived = panCopyReceived;
    }

    public Integer getAddressProofReceived() {
        return addressProofReceived;
    }

    public void setAddressProofReceived(Integer addressProofReceived) {
        this.addressProofReceived = addressProofReceived;
    }

    public Integer getAgreementReceived() {
        return agreementReceived;
    }

    public void setAgreementReceived(Integer agreementReceived) {
        this.agreementReceived = agreementReceived;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getVendorNewinsCode() {
        return vendorNewinsCode;
    }

    public void setVendorNewinsCode(String vendorNewinsCode) {
        this.vendorNewinsCode = vendorNewinsCode;
    }

    public String getCreditPeriod() {
        return creditPeriod;
    }

    public void setCreditPeriod(String creditPeriod) {
        this.creditPeriod = creditPeriod;
    }

    public String getTdsSection() {
        return tdsSection;
    }

    public void setTdsSection(String tdsSection) {
        this.tdsSection = tdsSection;
    }

    public Double getTdsPercentage() {
        return tdsPercentage;
    }

    public void setTdsPercentage(Double tdsPercentage) {
        this.tdsPercentage = tdsPercentage;
    }

    public Date getCodeCreatedDate() {
        return codeCreatedDate;
    }

    public void setCodeCreatedDate(Date codeCreatedDate) {
        this.codeCreatedDate = codeCreatedDate;
    }

    public String getNewinCodeCreatedBy() {
        return newinCodeCreatedBy;
    }

    public void setNewinCodeCreatedBy(String newinCodeCreatedBy) {
        this.newinCodeCreatedBy = newinCodeCreatedBy;
    }

    public String getMsmedNumber() {
        return msmedNumber;
    }

    public void setMsmedNumber(String msmedNumber) {
        this.msmedNumber = msmedNumber;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Date getVrfEnteredDate() {
        return vrfEnteredDate;
    }

    public void setVrfEnteredDate(Date vrfEnteredDate) {
        this.vrfEnteredDate = vrfEnteredDate;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Integer getAllDocReceived() {
        return allDocReceived;
    }

    public void setAllDocReceived(Integer allDocReceived) {
        this.allDocReceived = allDocReceived;
    }

    public String getSegmentOrDept() {
        return segmentOrDept;
    }

    public void setSegmentOrDept(String segmentOrDept) {
        this.segmentOrDept = segmentOrDept;
    }

    public String getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public String getTaxVat() {
        return taxVat;
    }

    public void setTaxVat(String taxVat) {
        this.taxVat = taxVat;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getGstProvisionalId() {
        return gstProvisionalId;
    }

    public void setGstProvisionalId(String gstProvisionalId) {
        this.gstProvisionalId = gstProvisionalId;
    }

    public String getVendorType() {
        return vendorType;
    }

    public void setVendorType(String vendorType) {
        this.vendorType = vendorType;
    }

    public String getCompanyPartnershipProprietorship() {
        return companyPartnershipProprietorship;
    }

    public void setCompanyPartnershipProprietorship(String companyPartnershipProprietorship) {
        this.companyPartnershipProprietorship = companyPartnershipProprietorship;
    }

    public String getCiNumber() {
        return ciNumber;
    }

    public void setCiNumber(String ciNumber) {
        this.ciNumber = ciNumber;
    }

    public Double getApproxAmountPerMonth() {
        return approxAmountPerMonth;
    }

    public void setApproxAmountPerMonth(Double approxAmountPerMonth) {
        this.approxAmountPerMonth = approxAmountPerMonth;
    }

    public String getLoiPoContract() {
        return loiPoContract;
    }

    public void setLoiPoContract(String loiPoContract) {
        this.loiPoContract = loiPoContract;
    }

    public String getSlNumberOfPo() {
        return slNumberOfPo;
    }

    public void setSlNumberOfPo(String slNumberOfPo) {
        this.slNumberOfPo = slNumberOfPo;
    }

    public String getSlNumberOfContract() {
        return slNumberOfContract;
    }

    public void setSlNumberOfContract(String slNumberOfContract) {
        this.slNumberOfContract = slNumberOfContract;
    }

    public String getOpenClosePeriodOneTime() {
        return openClosePeriodOneTime;
    }

    public void setOpenClosePeriodOneTime(String openClosePeriodOneTime) {
        this.openClosePeriodOneTime = openClosePeriodOneTime;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getPoReceived() {
        return poReceived;
    }

    public void setPoReceived(String poReceived) {
        this.poReceived = poReceived;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getProprietorDeclaration() {
        return proprietorDeclaration;
    }

    public void setProprietorDeclaration(String proprietorDeclaration) {
        this.proprietorDeclaration = proprietorDeclaration;
    }

    public String getGstApplicationNo() {
        return gstApplicationNo;
    }

    public void setGstApplicationNo(String gstApplicationNo) {
        this.gstApplicationNo = gstApplicationNo;
    }

    public String getGstinRegAckDocs() {
        return gstinRegAckDocs;
    }

    public void setGstinRegAckDocs(String gstinRegAckDocs) {
        this.gstinRegAckDocs = gstinRegAckDocs;
    }

    public String getLoiReceived() {
        return loiReceived;
    }

    public void setLoiReceived(String loiReceived) {
        this.loiReceived = loiReceived;
    }

    public String getGstReg() {
        return gstReg;
    }

    public void setGstReg(String gstReg) {
        this.gstReg = gstReg;
    }

    public String getReasonForNonReg() {
        return reasonForNonReg;
    }

    public void setReasonForNonReg(String reasonForNonReg) {
        this.reasonForNonReg = reasonForNonReg;
    }

    public String getGstRegState() {
        return gstRegState;
    }

    public void setGstRegState(String gstRegState) {
        this.gstRegState = gstRegState;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    public String getRegService() {
        return regService;
    }

    public void setRegService(String regService) {
        this.regService = regService;
    }

    public String getHsnSacCode() {
        return hsnSacCode;
    }

    public void setHsnSacCode(String hsnSacCode) {
        this.hsnSacCode = hsnSacCode;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getApType() {
        return apType;
    }

    public void setApType(String apType) {
        this.apType = apType;
    }

    public Integer getInitiatorBranch() {
        return initiatorBranch;
    }

    public void setInitiatorBranch(Integer initiatorBranch) {
        this.initiatorBranch = initiatorBranch;
    }

    public String getGstr1Anx1Filing() {
        return gstr1Anx1Filing;
    }

    public void setGstr1Anx1Filing(String gstr1Anx1Filing) {
        this.gstr1Anx1Filing = gstr1Anx1Filing;
    }

    public String getHoCode() {
        return hoCode;
    }

    public void setHoCode(String hoCode) {
        this.hoCode = hoCode;
    }

    public String getHoCodeCreatedDate() {
        return hoCodeCreatedDate;
    }

    public void setHoCodeCreatedDate(String hoCodeCreatedDate) {
        this.hoCodeCreatedDate = hoCodeCreatedDate;
    }

    public String getHoCodeUpdatedBy() {
        return hoCodeUpdatedBy;
    }

    public void setHoCodeUpdatedBy(String hoCodeUpdatedBy) {
        this.hoCodeUpdatedBy = hoCodeUpdatedBy;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getEnterprisType() {
        return enterprisType;
    }

    public void setEnterprisType(String enterprisType) {
        this.enterprisType = enterprisType;
    }
}
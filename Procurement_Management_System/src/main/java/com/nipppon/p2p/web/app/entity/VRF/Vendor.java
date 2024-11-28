package com.nipppon.p2p.web.app.entity.VRF;

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
@Table(name="vrf_master")
public class Vendor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "vrf_id", unique = true, length = 80)
    private String vrfId;

    @Column(name = "vendor_name", nullable = false, length = 300)
    private String vendorName;

    @Column(name = "group_name", length = 100)
    private String groupName;

    @Column(name = "vendor_address", length = 800)
    private String vendorAddress;

    @Column(name = "vendor_phone", length = 60)
    private String vendorPhone;

    @Column(name = "vendor_email", length = 100)
    private String vendorEmail;

    @Column(name = "website", length = 50)
    private String website;

    @Column(name = "cont_Person_name", length = 50)
    private String contactPersonName;

    @Column(name = "cont_person_designation", length = 100)
    private String contactPersonDesignation;

    @Column(name = "cont_person_phone", length = 30)
    private String contactPersonPhone;

    @Column(name = "cont_person_email", length = 50)
    private String contactPersonEmail;

    @Column(name = "branch_id", length = 20, nullable = false)
    private String branchId;

    @Column(name = "branch_Name", length = 20, nullable = false)
    private String branchName;

    @Column(name = "oneTimeVendor", length = 20, nullable = false)
    private String oneTimeVendor = "0";

    @Column(name = "natureOfService", length = 200)
    private String natureOfService;

    @Column(name = "appAmtOfPaymentPerMonth")
    private Double appAmtOfPaymentPerMonth;

    @Column(name = "MSMED")
    private Integer msmed;

    @Column(name = "PAN_Number", length = 20, nullable = false)
    private String panNumber;

    @Column(name = "PAN_holderName", length = 100)
    private String panHolderName;

    @Column(name = "partner/proprietorName", length = 200)
    private String partnerOrProprietorName;

    @Column(name = "vrfrecvd")
    private Integer vrfReceived;

    @Column(name = "transportDeclarationRecvd")
    private Integer transportDeclarationReceived;

    @Column(name = "vendorDeclarationRecvd")
    private Integer vendorDeclarationReceived;

    @Column(name = "MSMEDForm_Recvd")
    private Integer msmedFormReceived;

    @Column(name = "PAN_Copy_Recvd")
    private Integer panCopyReceived;

    @Column(name = "AddressProof_Recvd")
    private Integer addressProofReceived;

    @Column(name = "Agreement_Recvd")
    private Integer agreementReceived;

    @Column(name = "remarks", length = 800)
    private String remarks;

    @Column(name = "vendorNewinsCode", length = 20)
    private String vendorNewinsCode;

    @Column(name = "creditPeriod", length = 30)
    private String creditPeriod;

    @Column(name = "TDSSection", length = 20)
    private String tdsSection;

    @Column(name = "TDS%")
    private Double tdsPercentage;

    @Column(name = "codeCreatedDate")
    @Temporal(TemporalType.DATE)
    private Date codeCreatedDate;

    @Column(name = "newinCodeCreatedBy", length = 20)
    private String newinCodeCreatedBy;

    @Column(name = "MSMED_Number", length = 100)
    private String msmedNumber;

    @Column(name = "additionalDetails", length = 100)
    private String additionalDetails;

    @Column(name = "approvalStatus", length = 20, nullable = false)
    private String approvalStatus;

    @Column(name = "vrf_entered_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date vrfEnteredDate = new Date();

    @Column(name = "Initiator", length = 30, nullable = false)
    private String initiator;

    @Column(name = "lastModifiedBy", length = 20)
    private String lastModifiedBy;

    @Column(name = "AllDocRecvd")
    private Integer allDocReceived;

    @Column(name = "segmentordept", length = 200)
    private String segmentOrDept;

    @Column(name = "servicelocation", length = 500)
    private String serviceLocation;

    @Column(name = "taxvat", length = 30)
    private String taxVat;

    @Column(name = "vat", length = 20)
    private String vat;

    @Column(name = "gst", length = 50)
    private String gst;

    @Column(name = "gstprovisional_id", length = 30)
    private String gstProvisionalId;

    @Column(name = "vendortype", length = 30)
    private String vendorType;

    @Column(name = "co/partnership/proprietarship", length = 30)
    private String companyPartnershipProprietorship;

    @Column(name = "ci_number", length = 30)
    private String ciNumber;

    @Column(name = "approxamtpm")
    private Double approxAmountPerMonth;

    @Column(name = "loi_po_contract", length = 20)
    private String loiPoContract;

    @Column(name = "slnumberof_po", length = 40)
    private String slNumberOfPo;

    @Column(name = "slnumberof_contract", length = 250)
    private String slNumberOfContract;

    @Column(name = "open_close_period_onetime", length = 20)
    private String openClosePeriodOneTime;

    @Column(name = "fromdate", length = 20)
    private String fromDate;

    @Column(name = "todate", length = 20)
    private String toDate;

    @Column(name = "po_Recvd", length = 10)
    private String poReceived;

    @Column(name = "state", length = 50)
    private String state;

    @Column(name = "pincode", length = 20)
    private String pincode;

    @Column(name = "proprietor_Declaration", length = 20)
    private String proprietorDeclaration;

    @Column(name = "gst_Application_No", length = 40)
    private String gstApplicationNo;

    @Column(name = "gSTIN_Reg_ack_docs", length = 50)
    private String gstinRegAckDocs;

    @Column(name = "loi_recv", length = 20)
    private String loiReceived;

    @Column(name = "gst_reg", length = 100)
    private String gstReg;

    @Column(name = "reason_for_nonreg", length = 300)
    private String reasonForNonReg;

    @Column(name = "gst_reg_state", length = 50)
    private String gstRegState;

    @Column(name = "reg_address", length = 1000)
    private String regAddress;

    @Column(name = "reg_service", length = 250)
    private String regService;

    @Column(name = "hsn_sac_code", length = 200)
    private String hsnSacCode;

    @Column(name = "hsn_code", length = 200)
    private String hsnCode;

    @Column(name = "legal_Name", length = 100)
    private String legalName;

    @Column(name = "nameOfProduct", length = 250)
    private String nameOfProduct;

    @Column(name = "trade_Name", length = 100)
    private String tradeName;

    @Column(name = "aptype", length = 40)
    private String apType;

    @Column(name = "initiatorBranch")
    private Integer initiatorBranch;

    @Column(name = "gSTR1_ANX1_Filing", length = 40)
    private String gstr1Anx1Filing;

    @Column(name = "ho_code", length = 50)
    private String hoCode;

    @Column(name = "ho_code_createdDate", length = 20)
    private String hoCodeCreatedDate;

    @Column(name = "ho_codeUpdatedBy", length = 100)
    private String hoCodeUpdatedBy;

    @Column(name = "businessType", length = 20)
    private String businessType;

    @Column(name = "EnterprisType", length = 20)
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
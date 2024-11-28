import React, { useEffect, useRef, useState } from 'react';
import { getFormattedCreatedOn } from '../../../utils/DateUtils';
import { CButton, CCol, CForm, CRow, CNav, CNavItem, CNavLink, CTabContent, CTabPane, CCard, CCardTitle, CCardBody, CHeaderDivider, CSpinner } from '@coreui/react';
import { getDecryptedSessionItem, setEncryptedSessionItem } from '../../../utils/sessionUtils';
import { toast, ToastContainer } from 'react-toastify';
import PrimaryDetails from './PrimaryDetails';
import CIcon from '@coreui/icons-react';
import { cilBalanceScale, cilInfo, cilShieldAlt } from '@coreui/icons';
import BackButton from '../../SupportPages/BackButton';
import { useNavigate } from 'react-router-dom';
import ProductDetails from './ProductDetails';
import axios from 'axios';
import MRPlanPreview from './MRPlanPreview';
import { FaRegQuestionCircle } from 'react-icons/fa';

const AddMRPlanReq = () => {
  const BASE_URL_P2P            = import.meta.env.VITE_P2P_API_URL;
  const BASE_URL_HRMS           = import.meta.env.VITE_HRMS_API_URL;
  const formattedMRPCreatedOn   = getFormattedCreatedOn();
  const jwtToken                = getDecryptedSessionItem('token');
  const reqBy                   = getDecryptedSessionItem('emp_id');
  const branchId                = getDecryptedSessionItem('branch_id');
  const empRo                   = getDecryptedSessionItem('user_ro1');
  const navigate                = useNavigate();

  const [toggleSummary , setToggleSummary ]   = useState('');
  const [appDetails, setAppDetails]           = useState('');
  const [loading, setLoading]                 = useState(false);

  const fetchApprover = async () => {
    axios.get(`${BASE_URL_HRMS}masters/getEmpDetails/${empRo}`, {
      headers: {
        Authorization: `Bearer ${jwtToken}`,
        'Content-Type': 'application/json',
      },
    })
    .then(response => {
      const appDetails = response.data;
      setAppDetails(appDetails);
    })
    .catch(error => {
      console.error('Error fetching Approver details', error);
      toast.error('Failed to load Approver details');
    })
  }

  useEffect(() => {
    if(appDetails === ''){
      fetchApprover();
    }
  }, [BASE_URL_HRMS, jwtToken, empRo]);

// for Primary Details
  const locRef                            = useRef('');
  const deptRef                           = useRef('');
  const divRef                            = useRef('');
  const typeRef                           = useRef('');
  const [locId, setLocId]                 = useState('');
  const [deptId, setDeptId]               = useState('');
  const [divId, setDivId]                 = useState('');
  const [purchaseType, setPurchaseType]   = useState('');
  
// for Product Details
  const itemRef                           = useRef('');
  const descRef                           = useRef('');
  const qtyRef                            = useRef('');
  const uomRef                            = useRef('');
  const priceRef                          = useRef('');
  const totRef                            = useRef('');
  const [itemId, setItemId]               = useState('');
  const [desc, setDesc]                   = useState('');
  const [qty, setQty]                     = useState('');
  const [uomId, setUomId]                 = useState('');
  const [price, setPrice]                 = useState('');
  const [total, setTotal]                 = useState('');

// for Remarks
  const remarksRef                        = useRef('');
  const [remarks, setRemarks]             = useState('');
  
  const [addedProducts, setAddedProducts] = useState([]);
  const noOfItems                         = addedProducts.length;
  const totalMRPValue                     = (addedProducts.reduce((sum, product) => sum + parseFloat(product.total || 0), 0)).toFixed(2);

  let prdCode='',prdName='',prdHsn='',prdDiv='',prdPT='',prdDesc='',prdManu='',prdMod='',prdCat='',prdSubCat='',prdUom='',prdPrice='';

  if(itemId !== ''){
    prdCode     = itemId.prodInfo.prdDetails.prdCode;
    prdName     = itemId.prodInfo.prdDetails.prdName;
    prdHsn      = itemId.prodInfo.prdHsn.label;
    prdDiv      = itemId.prodInfo.prdDiv.label;
    prdPT       = itemId.prodInfo.prdDetails.prdPur;
    prdDesc     = itemId.prodInfo.prdDetails.prdDesc;
    prdManu     = itemId.prodInfo.prdDetails.prdManu;
    prdMod      = itemId.prodInfo.prdDetails.prdMod;
    prdCat      = itemId.prodInfo.prdCat.label;
    prdSubCat   = itemId.prodInfo.prdSub.label;
    prdUom      = itemId.prodInfo.prdUom.label;
    prdPrice    = itemId.prodInfo.prdDetails.prdPrice;
  }

  const [activeTab, setActiveTab] = useState(0); // State for active tab
  const totalTabs = 3; // Total number of tabs

  const validateFirstTab = () => {
    if (!locId) {
      toast.error("Please select Office Location");
      locRef.current.focus();
      return false;
    } else if(!deptId) {
      toast.error("Please select Department");
      deptRef.current.focus();
      return false;
    } else if(!divId) {
      toast.error("Please select Division");
      divRef.current.focus();
      return false;
    } else if(!purchaseType) {
      toast.error("Please select Purchase Type");
      typeRef.current.focus();
      return false;
    } else {
      return true
    }
  };

  const validateSecondTab = () => {
    if(addedProducts.length === 0){
      toast.error("Please fill Product Details.");
      itemRef.current.focus();
      return false;
    }else{
      return true;
    }
  };

  const validateThirdTab = () => {
    if(!remarks){
      toast.error('Please enter Remarks.');
      remarksRef.current.focus();
      return false;
    }else{
      return true;
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateFirstTab()) return;
    if (!validateSecondTab()) return;
    if (!validateThirdTab()) return;

    const mrPlanDto = {
      officeLocId: locId.value,
      departId: deptId.value,
      divId: divId.value,
      purType: purchaseType.value,
      reqBy: reqBy,
      branchId,
      branchCode: locId.bNo,
      reqOn: formattedMRPCreatedOn,
      noOfItems,
      totalMRPValue,
      remarks,
    };

    const transformAddedProducts  = (addedProducts) => {
      return addedProducts.map(product => ({
        productId: product?.itemId?.value,
        productDesc: product?.desc,
        qty: product?.qty,
        uomId: product?.itemId?.prodInfo?.prdUom?.value,
        unitPrice: product?.price,
        totalPrice: product?.total
      }));
    };

    const transformedPayload = transformAddedProducts(addedProducts);

    const payLoad = {
      request: mrPlanDto,
      items: transformedPayload,
      approverId: empRo,
      appLevelNo: 1,
    }

    setLoading(true);
    await axios.post(`${BASE_URL_P2P}mrPlan/submit`, payLoad, {
      headers: {
        Authorization: `Bearer ${jwtToken}`,
        "Content-Type": 'application/json',
      }
    })
    .then( response => {
      if(response.status === 201){
        const reqNumber    = response.data.reqNumber;
        const reqRefNumber = response.data.reqRefNumber;
        setEncryptedSessionItem('reqNumber', reqNumber);
        setEncryptedSessionItem('reqRefNumber', reqRefNumber);

        toast.success('MR Plan request submitted successfully.');
        navigate("/mrPlan/mrPlanDetails");
      }
    })
    .catch(error => {
      console.error('Error submitting the MR Plan request: ', error);
      toast.error('Failed to submit MR Plan request.');
    })
    .finally(() => {
      setLoading(false);
    });
  };

  const handleSave = async (e) => {
    e.preventDefault();
    if (!validateFirstTab()) return;
    if(addedProducts.length === 0){
      toast.error("Please add atleast One Product.");
      itemRef.current.focus();
      return false;
    }else{
      const mrPlanDto = {
        officeLocId: locId.value,
        departId: deptId.value,
        divId: divId.value,
        purType: purchaseType.value,
        reqBy: reqBy,
        branchId,
        branchCode: locId.bNo,
        reqOn: formattedMRPCreatedOn,
        noOfItems,
        totalMRPValue,
        remarks,
      };

      const transformAddedProducts  = (addedProducts) => {
        return addedProducts.map(product => ({
          productId: product?.itemId?.value,
          productDesc: product?.desc,
          qty: product?.qty,
          uomId: product?.itemId?.prodInfo?.prdUom?.value,
          unitPrice: product?.price,
          totalPrice: product?.total
        }));
      };

      const transformedPayload = transformAddedProducts(addedProducts);

      const payLoad = {
        request: mrPlanDto,
        items: transformedPayload,
      }

      setLoading(true);
      await axios.post(`${BASE_URL_P2P}mrPlan/save`, payLoad, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          "Content-Type": 'application/json',
        }
      })
      .then( response => {
        if(response.status === 201){
          const reqNumber    = response.data.reqNumber;
          const reqRefNumber = response.data.reqRefNumber;
          setEncryptedSessionItem('reqNumber', reqNumber);
          setEncryptedSessionItem('reqRefNumber', reqRefNumber);

          toast.success('MR Plan request saved successfully.');
          navigate("/mrPlan/mrPlanDetails");
        }
      })
      .catch(error => {
        console.error('Error saving the MR Plan request: ', error);
        toast.error('Failed to save MR Plan request.');
      })
      .finally(() => {
        setLoading(false);
      });
    }
  };

  const handleNext = () => {
    if (activeTab === 0 && !validateFirstTab()) return;
    if (activeTab === 1 && !validateSecondTab()) return;

    if (activeTab < totalTabs - 1) {
      setActiveTab((prev) => prev + 1);
    }
  };

  const handlePrevious = () => {
    if (activeTab > 0) setActiveTab((prev) => prev - 1);
  };

  return (
    <>
      <ToastContainer position="top-right" autoClose={3000} />

      <CCard>
        <CCardTitle>
          <CRow className='d-flex justify-content-between align-items-center'>
            <CCol>New MR Plan</CCol>
            <CCol className='d-flex gap-2 justify-content-end'>
              <BackButton routePath={'/mrPlan'} />
            </CCol>
          </CRow>
        </CCardTitle>
      </CCard>

      <CForm>
        <CCard style={{height: '100%'}}>
          <CCardBody className='pt-1'>
            <CRow>
              <CCol xs={12} md={toggleSummary && activeTab !== 2 ? 9 : 12}>
                <CRow>
                  <CCol xs={12} sm={3} className='border-r border-gray-300 min-h-[68vh]'>
                    <CNav variant="pills" className="flex-column">
                      <CNavItem className='mt-2 mb-2'>
                        <CNavLink active={activeTab === 0} onClick={() => setActiveTab(0)} className={`cursor-pointer text-md px-4 py-2 ${activeTab === 0 ? 'active-tab' : 'text-black bg-slate-200 hover:bg-slate-300'}`}>
                          <span className='d-flex align-items-center'>
                            <CIcon icon={cilShieldAlt} className='text-primary' size='xxl' />
                            <div className="ms-2">
                              <div>Primary Details</div>
                              {/* <span className="text-gray-600 text-sm">Please fill the Primary Details</span> */}
                            </div>
                          </span>
                          {activeTab === 0 && <span className="arrow-effect" />}
                        </CNavLink>
                      </CNavItem>
                      <CNavItem className='mt-2 mb-2'>
                        <CNavLink active={activeTab === 1} onClick={() => validateFirstTab() && setActiveTab(1)} className={`cursor-pointer text-md px-4 py-2 ${activeTab === 1 ? 'active-tab' : 'text-black bg-slate-200 hover:bg-slate-300'}`}>
                          <span className='d-flex align-items-center'>
                            <CIcon icon={cilBalanceScale} className='text-primary' size='xxl' />
                            <div className="ms-2">
                              <div>Product Details</div>
                              {/* <span className="text-gray-600 text-sm">Please fill the Product Details</span> */}
                            </div>
                          </span>
                          {activeTab === 1 && <span className="arrow-effect" />}
                        </CNavLink>
                      </CNavItem>
                      <CNavItem className='mt-2 mb-2'>
                        <CNavLink active={activeTab === 2} onClick={() => validateFirstTab() && validateSecondTab() && setActiveTab(2)} className={`cursor-pointer text-md px-4 py-2 ${activeTab === 2 ? 'active-tab' : 'text-black bg-slate-200 hover:bg-slate-300'}`}>
                          <span className='d-flex align-items-center'>
                            <CIcon icon={cilInfo} className='text-primary' size='xxl' />
                            <div className="ms-2">
                              <div>Preview</div>
                              {/* <span className="text-gray-600 text-sm">Please Preview MRP Details</span> */}
                            </div>
                          </span>
                          {activeTab === 2 && <span className="arrow-effect" />}
                        </CNavLink>
                      </CNavItem>
                    </CNav>
                  </CCol>

                  <CCol xs={12} sm={9} className={toggleSummary && activeTab !== 2 ? (`border-r border-gray-300 min-h-[68vh]`) : (``)}>
                    {activeTab !== 2 ? (
                      <div className="d-flex justify-content-end mb-1">
                        <FaRegQuestionCircle onClick={() => setToggleSummary(!toggleSummary)} title='View MR Plan Summary' className='text-info text-lg cursor-pointer' />
                      </div>
                    ) : ''}
                    <CTabContent>
                      <CTabPane visible={activeTab === 0}>
                        <PrimaryDetails locRef={locRef} locId={locId} setLocId={setLocId} deptRef={deptRef} deptId={deptId} setDeptId={setDeptId} divRef={divRef} divId={divId} setDivId={setDivId} typeRef={typeRef} purchaseType={purchaseType} setPurchaseType={setPurchaseType} />
                      </CTabPane>
                      <CTabPane visible={activeTab === 1}>
                        <ProductDetails divId={divId} purchaseType={purchaseType} itemRef={itemRef} itemId={itemId} setItemId={setItemId} descRef={descRef} desc={desc} setDesc={setDesc} qtyRef={qtyRef} qty={qty} setQty={setQty} uomRef={uomRef} uomId={uomId} setUomId={setUomId} priceRef={priceRef} price={price} setPrice={setPrice} totRef={totRef} total={total} setTotal={setTotal} addedProducts={addedProducts} setAddedProducts={setAddedProducts} />
                      </CTabPane>
                      <CTabPane visible={activeTab === 2}>
                        <MRPlanPreview locId={locId} deptId={deptId} divId={divId} purchaseType={purchaseType} addedProducts={addedProducts} appDetails={appDetails} remarksRef={remarksRef} remarks={remarks} setRemarks={setRemarks} />
                      </CTabPane>
                    </CTabContent>
                  </CCol>
                </CRow>
              </CCol>

              {toggleSummary && activeTab !== 2 ? (
                <>
                  <CCol xs={12} sm={3}>
                    <CRow>
                      <CCol>
                        <div className="relative mb-6 mt-1">
                          <h5 className="pb-1">MR Plan Summary</h5>
                          <span className="absolute left-0 bottom-0 w-full h-[1px] bg-gray-300"></span>
                        </div>
                      </CCol>
                    </CRow>
{/* Primary Details */}
                    <CRow>
                      <CCol>
                        <div className="relative mb-2 text-primary group">
                          <span className="inline-block fw-semibold">Primary Details</span>
                          <span className="underline-span text-primary"></span>
                        </div>
                      </CCol>
                    </CRow>
                    <CRow className="mb-1">
                      <CCol className="text-gray-600 font-small">Office Location : <span className="text-black ms-1">{locId.label || ''}</span></CCol>
                    </CRow>
                    <CRow className="mb-1">
                      <CCol className="text-gray-600 font-small">Department : <span className="text-black ms-1">{deptId.label || ''}</span></CCol>
                    </CRow>
                    <CRow className="mb-1">
                      <CCol className="text-gray-600 font-small">Division : <span className="text-black ms-1">{divId.label || ''}</span></CCol>
                    </CRow>
                    <CRow className="mb-1">
                      <CCol className="text-gray-600 font-small">Purchase Type : <span className="text-black ms-1">{purchaseType.label || ''}</span></CCol>
                    </CRow>
{/* Product Details */}
                    {activeTab === 1 ? (
                      <>
                        <CRow className='mt-2'>
                          <CCol>
                            {/* Dotted line */}
                            <CHeaderDivider className='mb-2 dotted-line' />
                            <div className="relative mb-2 text-primary group">
                              <span className="inline-block fw-semibold">Product Details</span>
                              <span className="underline-span text-primary"></span>
                            </div>
                          </CCol>
                        </CRow>
                        <CRow className="mb-1">
                          <CCol className="text-gray-600 font-small">Product Code : <span className="text-black ms-1">{prdCode || ''}</span></CCol>
                        </CRow>
                        <CRow className="mb-1">
                          <CCol className="text-gray-600 font-small">HSN/SAC Code : <span className="text-black ms-1">{prdHsn || ''}</span></CCol>
                        </CRow>
                        <CRow className="mb-1">
                          <CCol className="text-gray-600 font-small">Division : <span className="text-black ms-1">{prdDiv || ''}</span></CCol>
                        </CRow>
                        <CRow className="mb-1">
                          <CCol className="text-gray-600 font-small">Purchase Type : <span className="text-black ms-1">{prdPT || ''}</span></CCol>
                        </CRow>
                        <CRow className="mb-1">
                          <CCol className="text-gray-600 font-small">Name : <span className="text-black ms-1">{prdName || ''}</span></CCol>
                        </CRow>
                        <CRow className="mb-1">
                          <CCol className="text-gray-600 font-small">Description : <span className="text-black ms-1">{prdDesc || ''}</span></CCol>
                        </CRow>
                        <CRow className="mb-1">
                          <CCol className="text-gray-600 font-small">Manufacturer : <span className="text-black ms-1">{prdManu || ''}</span></CCol>
                        </CRow>
                        <CRow className="mb-1">
                          <CCol className="text-gray-600 font-small">Model : <span className="text-black ms-1">{prdMod || ''}</span></CCol>
                        </CRow>
                        <CRow className="mb-1">
                          <CCol className="text-gray-600 font-small">Category : <span className="text-black ms-1">{prdCat || ''}</span></CCol>
                        </CRow>
                        <CRow className="mb-1">
                          <CCol className="text-gray-600 font-small">Sub-Category : <span className="text-black ms-1">{prdSubCat || ''}</span></CCol>
                        </CRow>
                        <CRow className="mb-1">
                          <CCol className="text-gray-600 font-small">UOM : <span className="text-black ms-1">{prdUom || ''}</span></CCol>
                        </CRow>
                        <CRow className="mb-1">
                          <CCol className="text-gray-600 font-small">Price : <span className="text-black ms-1">{prdPrice || ''}</span></CCol>
                        </CRow>
                      </>
                    ) : (
                      null
                    )}
{/* Approver Details */}
                    <CRow className='mt-2'>
                      <CCol>
                        {/* Dotted line */}
                        <CHeaderDivider className='mb-2 dotted-line' />
                        <div className="relative mb-2 text-primary group">
                          <span className="inline-block fw-semibold">Approver Details</span>
                          <span className="underline-span text-primary"></span>
                        </div>
                      </CCol>
                    </CRow>
                    <CRow className="mb-1">
                      <CCol className="text-gray-600 font-small">Name : <span className="text-black ms-1">{appDetails.fullName || ''}</span></CCol>
                    </CRow>
                    <CRow className="mb-1">
                      <CCol className="text-gray-600 font-small">Mail : <span className="text-black ms-1">{appDetails.email || ''}</span></CCol>
                    </CRow>
                  </CCol>
                </>
              ) : (
                ''
              )}
            </CRow>

            <CRow className='pt-2 mt-auto'>
              <CCol xs={12} md={12} className="d-flex justify-content-end gap-1">
                <CButton type='button' color="secondary" hidden={activeTab === 0} onClick={handlePrevious}>Previous</CButton> {/* Previous button */}
                <CButton type='button' color="primary" hidden={activeTab === totalTabs - 1} onClick={handleNext}>Next</CButton> {/* Next button */}
                <CButton type='button' hidden={activeTab === 0} color="info" onClick={handleSave}>Save</CButton> {/* Save button */}
                {activeTab === totalTabs - 1 && ( <CButton type="submit" color="success" disabled={loading} onClick={handleSubmit}>{loading ? (<CSpinner size='sm' />) : ('Submit')}</CButton> )} {/* Submit button */}
              </CCol>
            </CRow>

          </CCardBody>
        </CCard>
      </CForm>
    </>
  );
};

export default AddMRPlanReq;
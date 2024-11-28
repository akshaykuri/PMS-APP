import { CButton, CCol, CFormText, CModal, CModalBody, CModalFooter, CModalHeader, CRow } from '@coreui/react';
import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { draggableModal } from '../../../SupportPages/draggableModal';
import { formatDate } from '../../../../utils/DateFormatter';
import { getDecryptedSessionItem } from '../../../../utils/sessionUtils';

const ViewProduct = ({ addViewVisible, setAddViewVisible, prodId = null }) => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const jwtToken = getDecryptedSessionItem('token');

  const { modalRef, handleMouseDown, handleMouseMove, handleMouseUp } = draggableModal();

  const [productCode, setProductCode]               = useState('');
  const [hsnsacCode, setHsnsacCode]                 = useState(0);
  const [productName, setProductName]               = useState('');
  const [productDesc, setProductDesc]               = useState('');
  const [categoryName, setCategoryName]             = useState(0);
  const [subCategoryName, setSubCategoryName]       = useState(0);
  const [minQty, setMinQty]                         = useState('');
  const [maxQty, setMaxQty]                         = useState('');
  const [prodDivName, setProdDivName]               = useState(0);
  const [purchaseType, setPurchaseType]             = useState('');
  const [uomName, setUomName]                       = useState(0);
  const [price, setPrice]                           = useState(0);
  const [manufacturer, setManufacturer]             = useState('');
  const [model, setModel]                           = useState('');
  const [createdBy, setCreatedBy]                   = useState('');
  const [createdOn, setCreatedOn]                   = useState('');

  useEffect(() => {
    axios.get(`${BASE_URL_P2P}company/viewProduct/${prodId}`, {
    headers: {
        Authorization: `Bearer ${jwtToken}`,
        'Content-Type': 'application/json',
    }
    })
    .then(response => {
    const prodData = response.data;
    setProductCode(prodData.productCode);
    setHsnsacCode(prodData.hsnSacName);
    setProductName(prodData.productName);
    setProductDesc(prodData.productDesc);
    setCategoryName(prodData.categoryName);
    setSubCategoryName(prodData.subCategoryName);
    setMinQty(prodData.minQty);
    setMaxQty(prodData.maxQty);
    setProdDivName(prodData.divisionName);
    setPurchaseType(prodData.purchaseType);
    setUomName(prodData.uomName);
    setPrice(parseFloat(prodData.price).toFixed(2));
    setManufacturer(prodData.manufacturer);
    setModel(prodData.model);
    setCreatedBy(prodData.createdByName);
    setCreatedOn(formatDate(prodData.createdOn));
    })
    .catch(error => {
    console.error('Error fetching Product Details', error);
    toast.error('Failed to load Product Details');
    })
  }, [prodId, BASE_URL_P2P, jwtToken]);

  const toggleVisibility = (isVisible) => {
    if(!isVisible){
      setProductCode('');setHsnsacCode(0);setProductName('');setProductDesc('');setCategoryName(0);setSubCategoryName(0);setMinQty(0);setMaxQty(0);setProdDivName(0);setPurchaseType('');setUomName(0);setPrice(0);setManufacturer('');setModel('');
    }
    setAddViewVisible(isVisible);
  };

  return (
    <>
      <CModal backdrop className='pt-2' visible={addViewVisible} size='lg' onClose={() => toggleVisibility(false)} ref={modalRef}>
        <CModalHeader onClose={() => toggleVisibility(false)} style={{ cursor: 'move' }} onMouseMove={handleMouseMove} onMouseUp={handleMouseUp} onMouseLeave={handleMouseUp} onMouseDown={handleMouseDown}>
          <div className="font-semibold text-base">Product Details</div>
        </CModalHeader>
        <CModalBody>
          <CRow className='mt-2 mb-2'>
            <CCol xs={12} md={2}>Product Code : </CCol>
            <CCol xs={12} md={4}><CFormText>{productCode}</CFormText></CCol>
            <CCol xs={12} md={2}>HSN/SAC Code : </CCol>
            <CCol xs={12} md={4}><CFormText>{hsnsacCode}</CFormText></CCol>
          </CRow>
          <CRow className='mb-2'>
            <CCol xs={12} md={2}>Name : </CCol>
            <CCol xs={12} md={4}><CFormText>{productName}</CFormText></CCol>
            <CCol xs={12} md={2}>Description : </CCol>
            <CCol xs={12} md={4}><CFormText>{productDesc}</CFormText></CCol>
          </CRow>
          <CRow className='mb-2'>
            <CCol xs={12} md={2}>Manufacturer : </CCol>
            <CCol xs={12} md={4}><CFormText>{manufacturer}</CFormText></CCol>
            <CCol xs={12} md={2}>Model : </CCol>
            <CCol xs={12} md={4}><CFormText>{model}</CFormText></CCol>
          </CRow>
          <CRow className='mb-2'>
            <CCol xs={12} md={2}>Category : </CCol>
            <CCol xs={12} md={4}><CFormText>{categoryName}</CFormText></CCol>
            <CCol xs={12} md={2}>Sub-Category : </CCol>
            <CCol xs={12} md={4}><CFormText>{subCategoryName}</CFormText></CCol>
          </CRow>
          <CRow className='mb-2'>
            <CCol xs={12} md={2}>Division : </CCol>
            <CCol xs={12} md={4}><CFormText>{prodDivName}</CFormText></CCol>
            <CCol xs={12} md={2}>Purchase Type : </CCol>
            <CCol xs={12} md={4}><CFormText>{purchaseType}</CFormText></CCol>
          </CRow>
          <CRow className='mb-2'>
            <CCol xs={12} md={2}>UOM : </CCol>
            <CCol xs={12} md={4}><CFormText>{uomName}</CFormText></CCol>
            <CCol xs={12} md={2}>Price : </CCol>
            <CCol xs={12} md={4}><CFormText>{price}</CFormText></CCol>
          </CRow>
          <CRow className='mb-2'>
            <CCol xs={12} md={2}>Minimum Qty. : </CCol>
            <CCol xs={12} md={4}><CFormText>{minQty}</CFormText></CCol>
            <CCol xs={12} md={2}>Maximum Qty. : </CCol>
            <CCol xs={12} md={4}><CFormText>{maxQty}</CFormText></CCol>
          </CRow>
          <CRow className='mb-4'>
            <CCol xs={12} md={2}>Created By : </CCol>
            <CCol xs={12} md={4}><CFormText>{createdBy}</CFormText></CCol>
            <CCol xs={12} md={2}>Created On : </CCol>
            <CCol xs={12} md={4}><CFormText>{createdOn}</CFormText></CCol>
          </CRow>
          <CModalFooter>
            <CButton color="warning" onClick={() => toggleVisibility(false)}>Close</CButton>
          </CModalFooter>
        </CModalBody>
      </CModal>
    </>
  )
}

export default ViewProduct
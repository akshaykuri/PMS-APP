import React, { useEffect, useRef, useState } from 'react'
import { draggableModal } from '../SupportPages/draggableModal';
import { getDecryptedSessionItem } from '../../utils/sessionUtils';
import { CButton, CCol, CFormText, CModal, CModalBody, CModalFooter, CModalHeader, CRow, CTable, CTableBody, CTableDataCell, CTableHead, CTableHeaderCell, CTableRow } from '@coreui/react';
import axios from 'axios';
import { formatDate } from '../../utils/DateFormatter';
import { toast } from 'react-toastify';

const ViewMRPlanDetails = ({ viewVisible, setViewVisible, mrpId = null }) => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const jwtToken = getDecryptedSessionItem('token');

  const printRef = useRef();
  const [mrpDetails, setMrpDetails] = useState('');
  const { modalRef, handleMouseDown, handleMouseMove, handleMouseUp } = draggableModal();

  useEffect(() => {
    axios.get(`${BASE_URL_P2P}mrPlan/getMrpDetails/${mrpId}`, {
      headers: {
          Authorization: `Bearer ${jwtToken}`,
          'Content-Type': 'application/json',
      }
      })
      .then(response => {
      const mrpData = {
        ...response.data,
        reqOn: formatDate(response.data.reqOn),
        totalMRPValue: response.data.totalMRPValue.toFixed(2),
      };
      setMrpDetails(mrpData);
      })
      .catch(error => {
        console.error('Error fetching MR Plan Details', error);
        toast.error('Failed to load MR Plan Details');
      })
  }, [mrpId]);

  const toggleVisibility = (isVisible) => {
    if(!isVisible){
      setMrpDetails('');
    }
    setViewVisible(isVisible);
  };

  const handlePrint = () => {
    const printContent = printRef.current;
    const originalContent = document.body.innerHTML;
    document.body.innerHTML = printContent.innerHTML;
    window.print();
    document.body.innerHTML = originalContent;
    window.location.reload(); // Reload to restore original page content
  };

  return (
    <>
      <CModal backdrop className='pt-2' visible={viewVisible} size='xl' onClose={() => toggleVisibility(false)} ref={modalRef}>
        <div ref={printRef}>
          <CModalHeader onClose={() => toggleVisibility(false)} style={{ cursor: 'move' }} onMouseMove={handleMouseMove} onMouseUp={handleMouseUp} onMouseLeave={handleMouseUp} onMouseDown={handleMouseDown}>
            <div className="font-semibold text-base">MR Plan Details</div>
          </CModalHeader>
          <CModalBody>
            {/* Primary Details Section */}
            <section className="mb-3">
              <div className="ps-3 pb-2 pe-3 pt-1.5 bg-white border rounded-bottom">
                <CModalHeader closeButton={false} className='mb-2 font-semibold relative'>
                  Primary Details
                  <span className="absolute left-0 bottom-0 w-full h-[1px] bg-gray-300"></span>
                </CModalHeader>
                <CRow className='mt-2 mb-2'>
                  <CCol xs={12} md={2}>Reference Number : </CCol>
                  <CCol xs={12} md={4}><CFormText>{mrpDetails.reqRefNumber}</CFormText></CCol>
                  <CCol xs={12} md={2}>Request Number : </CCol>
                  <CCol xs={12} md={4}><CFormText>{mrpDetails.reqNumber}</CFormText></CCol>
                </CRow>
                <CRow className='mb-2'>
                  <CCol xs={12} md={2}>Requested By : </CCol>
                  <CCol xs={12} md={4}><CFormText>{mrpDetails.reqByName}</CFormText></CCol>
                  <CCol xs={12} md={2}>Requested On : </CCol>
                  <CCol xs={12} md={4}><CFormText>{mrpDetails.reqOn}</CFormText></CCol>
                </CRow>
                <CRow className='mt-2 mb-2'>
                  <CCol xs={12} md={2}>Office Location : </CCol>
                  <CCol xs={12} md={4}><CFormText>{mrpDetails.offLocName}</CFormText></CCol>
                  <CCol xs={12} md={2}>Department : </CCol>
                  <CCol xs={12} md={4}><CFormText>{mrpDetails.deptName}</CFormText></CCol>
                </CRow>
                <CRow className='mb-2'>
                  <CCol xs={12} md={2}>Division : </CCol>
                  <CCol xs={12} md={4}><CFormText>{mrpDetails.divName}</CFormText></CCol>
                  <CCol xs={12} md={2}>Purchase Type : </CCol>
                  <CCol xs={12} md={4}><CFormText>{mrpDetails.purType}</CFormText></CCol>
                </CRow>
                <CRow className='mb-2'>
                  <CCol xs={12} md={2}>No. Of Items : </CCol>
                  <CCol xs={12} md={4}><CFormText>{mrpDetails.noOfItems}</CFormText></CCol>
                  <CCol xs={12} md={2}>Grand Total : </CCol>
                  <CCol xs={12} md={4}><CFormText>{mrpDetails.totalMRPValue}</CFormText></CCol>
                </CRow>
              </div>
            </section>

            {/* Product Details Section */}
            <section className="mb-3">
              <div className="ps-3 pb-2 pe-3 pt-1.5 bg-white border rounded-bottom">
                <CModalHeader closeButton={false} className='mb-2 font-semibold relative'>
                  Product Details
                  <span className="absolute left-0 bottom-0 w-full h-[1px] bg-gray-300"></span>
                </CModalHeader>
                {mrpDetails.itemDTOs && mrpDetails.itemDTOs.length > 0 ? (
                  <CTable hover borderless responsive align='middle'>
                    <CTableHead>
                      <CTableRow>
                        <CTableHeaderCell className='border-r border-r-gray-300'>#</CTableHeaderCell>
                        <CTableHeaderCell className='border-r border-r-gray-300'>Product Name</CTableHeaderCell>
                        <CTableHeaderCell className='border-r border-r-gray-300'>Description</CTableHeaderCell>
                        <CTableHeaderCell className='border-r border-r-gray-300'>Qty</CTableHeaderCell>
                        <CTableHeaderCell className='border-r border-r-gray-300'>UOM</CTableHeaderCell>
                        <CTableHeaderCell className='border-r border-r-gray-300'>Price</CTableHeaderCell>
                        <CTableHeaderCell>Total</CTableHeaderCell>
                      </CTableRow>
                    </CTableHead>
                    <CTableBody>
                      {mrpDetails.itemDTOs.map((product, index) => {
                        return(
                          <CTableRow key={index}>
                            <CTableDataCell className='border-r border-r-gray-300'>{ index + 1 }</CTableDataCell>
                            <CTableDataCell className='border-r border-r-gray-300'>{ product.productName || 'N/A' }</CTableDataCell>
                            <CTableDataCell className='border-r border-r-gray-300'>{ product.productDesc || 'N/A' }</CTableDataCell>
                            <CTableDataCell className='border-r border-r-gray-300'>{ product.qty || '0' }</CTableDataCell>
                            <CTableDataCell className='border-r border-r-gray-300'>{ product.uomName || 'N/A' }</CTableDataCell>
                            <CTableDataCell className='border-r border-r-gray-300 text-right'>{ product.unitPrice || '0.00' }</CTableDataCell>
                            <CTableDataCell className='text-right'>{ product.totalPrice || '0.00' }</CTableDataCell>
                          </CTableRow>
                        );
                      })}

                      <CTableRow>
                        <CTableDataCell colSpan={6} className='text-right font-semibold'>Grand Total</CTableDataCell>
                        <CTableDataCell className='text-right font-semibold'>{mrpDetails.totalMRPValue}</CTableDataCell>
                      </CTableRow>
                    </CTableBody>
                  </CTable>
                ) : (
                  <p className="text-gray-600">No Products to display.</p>
                )}
              </div>
            </section>

            {/* Routing Details Section */}
            <section className="mb-3">
              <div className="ps-3 pb-2 pe-3 pt-1.5 bg-white border rounded-bottom">
                <CModalHeader closeButton={false} className='mb-2 font-semibold relative'>
                  Routing Details
                  <span className="absolute left-0 bottom-0 w-full h-[1px] bg-gray-300"></span>
                </CModalHeader>
                {mrpDetails.routingDTOs && mrpDetails.routingDTOs.length > 0 ? (
                  <CTable hover borderless responsive align='middle'>
                    <CTableHead>
                      <CTableRow>
                        <CTableHeaderCell className='border-r border-r-gray-300'>#</CTableHeaderCell>
                        <CTableHeaderCell className='border-r border-r-gray-300'>Name</CTableHeaderCell>
                        <CTableHeaderCell className='border-r border-r-gray-300'>Action</CTableHeaderCell>
                        <CTableHeaderCell className='border-r border-r-gray-300'>Action On</CTableHeaderCell>
                        <CTableHeaderCell className='border-r border-r-gray-300'>Remarks</CTableHeaderCell>
                      </CTableRow>
                    </CTableHead>
                    <CTableBody>
                      {mrpDetails.routingDTOs.map((routing, index) => {
                        return(
                          <CTableRow key={index}>
                            <CTableDataCell className='border-r border-r-gray-300'>{ index + 1 }</CTableDataCell>
                            <CTableDataCell className='border-r border-r-gray-300'>{ routing.recEmpName || 'N/A' }</CTableDataCell>
                            <CTableDataCell className='border-r border-r-gray-300'>{ routing.status || 'N/A' }</CTableDataCell>
                            <CTableDataCell className='border-r border-r-gray-300'>{ formatDate(routing.processDate) || '0' }</CTableDataCell>
                            <CTableDataCell className='border-r border-r-gray-300'>{ routing.mrpRemarks || 'N/A' }</CTableDataCell>
                          </CTableRow>
                        );
                      })}
                    </CTableBody>
                  </CTable>
                ) : (
                  <p className="text-gray-600">No Routings to display.</p>
                )}
              </div>
            </section>
            <CModalFooter className='mb-0 pb-0 d-flex justify-content-end no-print'>
              <CButton color="warning" onClick={() => toggleVisibility(false)}>Close</CButton>
              <CButton color='info' onClick={handlePrint}>Print</CButton>
            </CModalFooter>
          </CModalBody>
        </div>
      </CModal>
    </>
  )
}

export default ViewMRPlanDetails
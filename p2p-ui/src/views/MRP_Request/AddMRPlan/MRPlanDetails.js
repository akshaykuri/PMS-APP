import React from 'react';
import { getDecryptedSessionItem } from '../../../utils/sessionUtils';
import { CCard, CCardBody, CCardTitle, CRow, CCol } from '@coreui/react';
import BackButton from '../../SupportPages/BackButton';
import { ToastContainer } from 'react-toastify';

const MRPlanDetails = () => {
  const reqNumber = getDecryptedSessionItem('reqNumber') === 'null' || null ? '-' : getDecryptedSessionItem('reqNumber');
  const reqRefNumber = getDecryptedSessionItem('reqRefNumber');

  return (
    <>
      <ToastContainer position="top-right" autoClose={3000} />

      <CCard>
        <CCardTitle>
          <CRow className="d-flex justify-content-between align-items-center">
            <CCol>MR Plan Details</CCol>
            <CCol className="d-flex gap-2 justify-content-end">
              <BackButton routePath={'/mrPlan'} />
            </CCol>
          </CRow>
        </CCardTitle>
      </CCard>
      <CCard style={{height: '75vh'}}>
        <CCardBody>
          {reqRefNumber && reqNumber ? (
            <div className="text-gray-600">
              <CRow className="mb-3">
                <CCol xs={12} md={12} className='text-gray-900'>MR Plan request has been succesfully created with below details.</CCol>
              </CRow>
              <CRow className="mb-3">
                <CCol xs={12} md={2} className='fw-semibold'>Reference Number : </CCol>
                <CCol xs={12} md={10}><span className="text-primary">{reqRefNumber}</span></CCol>
              </CRow>
              <CRow>
                <CCol xs={12} md={2} className='fw-semibold'>Request Number : </CCol>
                <CCol xs={12} md={10}><span className="text-primary">{reqNumber}</span></CCol>
              </CRow>
            </div>
          ) : (
            <p className="text-danger font-weight-bold">No data available. Please ensure the request was successfully created.</p>
          )}
        </CCardBody>
      </CCard>
    </>
  );
};

export default MRPlanDetails;
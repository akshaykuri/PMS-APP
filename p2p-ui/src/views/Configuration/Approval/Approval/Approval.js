import { CCard, CCardBody, CCardTitle, CCol, CRow } from '@coreui/react'
import React from 'react'
import { ToastContainer } from 'react-toastify'
import BackButton from '../../../SupportPages/BackButton'

const Approval = () => {
  return (
    <>
    <ToastContainer position='top-right' autoClose={3000} />

    <CCard>
      <CCardTitle>
        <CRow className='d-flex justify-content-between align-items-center'>
          <CCol>Approver</CCol>
          <CCol className='d-flex gap-2 justify-content-end'>
            <BackButton routePath={'/masters'} />
          </CCol>
        </CRow>
      </CCardTitle>

      <CCardBody>
        {/* <Table data={userTypes} columns={columns} actions={actions} downloadName={downloadName} /> */}
      </CCardBody>
    </CCard>
    </>
  )
}

export default Approval
import { CCol, CHeader, CModalHeader, CRow, CTable, CTableHead, CTableRow, CTableHeaderCell, CTableBody, CTableDataCell, CFormTextarea } from '@coreui/react';
import React from 'react';

const MRPlanPreview = ({ locId, deptId, divId, purchaseType, addedProducts, appDetails, remarksRef, remarks, setRemarks }) => {
  const grandTotal = addedProducts.reduce((sum, product) => sum + parseFloat(product.total || 0), 0);

  return (
    <>
      <CHeader className="mb-3 fs-4 text-center pb-2">Preview MR Plan</CHeader>

      {/* Primary Details Section */}
      <section className="mb-3">
        <div className="ps-3 pb-3 pe-3 pt-1.5 bg-white border rounded-bottom">
          <CModalHeader closeButton={false} className='mb-2 font-semibold relative'>
            Primary Details
            <span className="absolute left-0 bottom-0 w-full h-[1px] bg-gray-300"></span>
          </CModalHeader>
          <CRow className="mb-2">
            <CCol xs={12} md={5}>
              <span className="fw-semibold text-gray-600">Office Location:</span> {locId.label}
            </CCol>
            <CCol xs={12} md={5}>
              <span className="fw-semibold text-gray-600">Department:</span> {deptId.label}
            </CCol>
          </CRow>
          <CRow>
            <CCol xs={12} md={5}>
              <span className="fw-semibold text-gray-600">Division:</span> {divId.label}
            </CCol>
            <CCol xs={12} md={5}>
              <span className="fw-semibold text-gray-600">Purchase Type:</span> {purchaseType.label}
            </CCol>
          </CRow>
        </div>
      </section>

      {/* Product Details Section */}
      <section className="mb-3">
        <div className="ps-3 pb-3 pe-3 pt-1.5 bg-white border rounded-bottom">
          <CModalHeader closeButton={false} className='mb-2 font-semibold relative'>
            Product Details
            <span className="absolute left-0 bottom-0 w-full h-[1px] bg-gray-300"></span>
          </CModalHeader>
          {addedProducts && addedProducts.length > 0 ? (
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
                {addedProducts.map((product, index) => {
                  return(
                    <CTableRow key={index}>
                      <CTableDataCell className='border-r border-r-gray-300'>{ index + 1 }</CTableDataCell>
                      <CTableDataCell className='border-r border-r-gray-300'>{ product?.itemId?.prodInfo?.prdDetails?.prdName || 'N/A' }</CTableDataCell>
                      <CTableDataCell className='border-r border-r-gray-300'>{ product.desc || 'N/A' }</CTableDataCell>
                      <CTableDataCell className='border-r border-r-gray-300'>{ product.qty || '0' }</CTableDataCell>
                      <CTableDataCell className='border-r border-r-gray-300'>{ product.uomId.label || 'N/A' }</CTableDataCell>
                      <CTableDataCell className='border-r border-r-gray-300 text-right'>{ product.price || '0.00' }</CTableDataCell>
                      <CTableDataCell className='text-right'>{ product.total || '0.00' }</CTableDataCell>
                    </CTableRow>
                  );
                })}

                <CTableRow>
                  <CTableDataCell colSpan={6} className='text-right font-semibold'>Grand Total</CTableDataCell>
                  <CTableDataCell className='text-right font-semibold'>{grandTotal.toFixed(2)}</CTableDataCell>
                </CTableRow>
              </CTableBody>
            </CTable>
          ) : (
            <p className="text-gray-600">No Products added to display.</p>
          )}
        </div>
      </section>

      {/* Approver Section */}
      <section className='mb-3'>
        <div className="ps-3 pb-3 pe-3 pt-1.5 bg-white border rounded-bottom">
          <CModalHeader closeButton={false} className='mb-2 font-semibold relative'>
            Approver
            <span className="absolute left-0 bottom-0 w-full h-[1px] bg-gray-300"></span>
          </CModalHeader>
          <CRow>
            <CCol xs={12}>
              <span className="fw-semibold text-gray-600">Name:&nbsp;</span>{appDetails.fullName}
            </CCol>
          </CRow>
          <CRow>
            <CCol xs={12}>
              <span className="fw-semibold text-gray-600">Email:&nbsp;&nbsp;</span>
              <span className="text-success">{appDetails.email}</span>
            </CCol>
          </CRow>
        </div>
      </section>

      {/* Remarks Section */}
      <section>
        <div className="ps-3 pb-3 pe-3 pt-1.5 bg-white border rounded-bottom">
          <CRow>
            <CCol xs={12}>
              <div className="floating-label">
                <CFormTextarea value={remarks} size="sm" placeholder=" " onChange={(e) => setRemarks(e.target.value)} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={remarksRef} />
                <label>Remarks</label>
              </div>
            </CCol>
          </CRow>
        </div>
      </section>
    </>
  );
};

export default MRPlanPreview;
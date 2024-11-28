import React, { useState } from 'react'
import OfficeLocation_DP from '../../DropDowns/OfficeLocation_DP'
import Department_DP from '../../DropDowns/Department_DP'
import DivisionsForPR_DP from '../../DropDowns/DivisionsForPR_DP'
import PurchaseTypeForPR_DP from '../../DropDowns/PurchaseTypeForPR_DP'
import { CCol, CHeader, CRow } from '@coreui/react'

const PrimaryDetails = ({ locRef, locId, setLocId, deptRef, deptId, setDeptId, divRef, divId, setDivId, typeRef, purchaseType, setPurchaseType }) => {
  const [locations, setLocations]                 = useState([]);
  const [departments, setDepartments]             = useState([]);
  const [divisions, setDivisions]                 = useState([]);
  const [purchaseTypes, setPurchaseTypes]         = useState([]);

  return (
    <>
      <CHeader className="mb-3 fs-4 text-center pb-2">Primary Details</CHeader>
      <CRow>
        <CCol xs={12} md={6}>
          {/* for Office-Location Drop-Down */}
          <OfficeLocation_DP locRef={locRef} locations={locations} locId={locId?.value} setLocations={setLocations} setLocId={setLocId} />
        </CCol>
        <CCol xs={12} md={6}>
          {/* for Department Drop-Down */}
          <Department_DP deptRef={deptRef} departments={departments} deptId={deptId?.value} setDepartments={setDepartments} setDeptId={setDeptId} />
        </CCol>
      </CRow>
      <CRow>
        <CCol xs={12} md={6}>
          {/* for Divisions Drop Down */}
          <DivisionsForPR_DP divRef={divRef} divisions={divisions} divId={divId?.value} setDivisions={setDivisions} setDivId={setDivId} />
        </CCol>
        <CCol xs={12} md={6}>
          {/* for Purchase Type Drop-Down */}
          <PurchaseTypeForPR_DP typeRef={typeRef} purchaseTypes={purchaseTypes} purchaseType={purchaseType?.value} setPurchaseTypes={setPurchaseTypes} setPurchaseType={setPurchaseType} />
        </CCol>
      </CRow>
    </>
  )
}

export default PrimaryDetails
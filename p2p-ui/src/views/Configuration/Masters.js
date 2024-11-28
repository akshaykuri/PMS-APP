import React from 'react';
import { CRow, CCol, CCard, CCardBody, CCardTitle, CCardSubtitle } from '@coreui/react';
import { CIcon } from '@coreui/icons-react';
import { cilTag, cilTags, cilBalanceScale, cilCalculator, cilBuilding, cilBarcode, cilUserPlus, cilSettings, cilNoteAdd, cilCheckCircle, cilObjectGroup } from '@coreui/icons';
import { useNavigate } from 'react-router-dom';

const Masters = () => {
  const navigate = useNavigate();

  const redirectPage = (value) => {
    switch (value) {
      case "User":
        navigate('/masters/userRole');
        break;
      case "Role":
        navigate('/masters/userType');
        break;
      case "RoleMgmt":
        navigate('/masters/role');
        break;
      case "Division":
        navigate('/masters/division');
        break;
      case "Cat":
        navigate('/masters/category');
        break;
      case "SubCat":
        navigate('/masters/subCategory');
        break;
      case "UOM":
        navigate('/masters/uom');
        break;
      case "HSN":
        navigate('/masters/hsn');
        break;
      case "Product":
        navigate('/masters/product');
        break;
      case "DeptHead":
        navigate('/masters/deptHead');
        break;
      case "Approve":
        navigate('/masters/approval');
        break;
      default:
        navigate('/masters');
        break;
    }
  };

  return (
    <>
      <CCard>
        <CCardTitle>Masters</CCardTitle>
      </CCard>

      <CCard>
        <CCardSubtitle>Administration</CCardSubtitle>
        <CCardBody>
          <CRow xs={{ gutterX: 3, gutterY: 3 }} style={{height: '90px'}}>
            {/* Roles Master Card */}
            <CCol xs="12" sm="6" md="2">
              <CCard className="text-center" style={{ backgroundColor: '#f5f7ff', height: '80px' }}>
                <CCardBody>
                  <CIcon icon={cilNoteAdd} size="xl" className='cursor-pointer' onClick={() => redirectPage("Role")} />
                  <CCardTitle className='text-sm'>Roles</CCardTitle>
                </CCardBody>
              </CCard>
            </CCol>
            {/* Role-Management Master Card */}
            <CCol xs="12" sm="6" md="2">
              <CCard className="text-center" style={{ backgroundColor: '#f5f7ff', height: '80px' }}>
                <CCardBody>
                  <CIcon icon={cilSettings} size="xl" className='cursor-pointer' onClick={() => redirectPage("RoleMgmt")} />
                  <CCardTitle className='text-sm'>Role Mgmt.</CCardTitle>
                </CCardBody>
              </CCard>
            </CCol>
            {/* User Master Card */}
            <CCol xs="12" sm="6" md="2">
              <CCard className="text-center" style={{ backgroundColor: '#f5f7ff', height: '80px' }}>
                <CCardBody>
                  <CIcon icon={cilUserPlus} size="xl" className='cursor-pointer' onClick={() => redirectPage("User")} />
                  <CCardTitle className='text-sm'>Users</CCardTitle>
                </CCardBody>
              </CCard>
            </CCol>
          </CRow>
        </CCardBody>
      </CCard>

      <CCard className='mt-2'>
        <CCardSubtitle>Other Details</CCardSubtitle>
        <CCardBody>
          <CRow xs={{ gutterX: 3, gutterY: 3 }} style={{height: '90px'}}>
            {/* Division Master Card */}
            <CCol xs="12" sm="6" md="2">
              <CCard className="text-center" style={{ backgroundColor: '#f5f7ff', height: '80px' }}>
                <CCardBody>
                  <CIcon icon={cilBuilding} size="xl" className='cursor-pointer' onClick={() => redirectPage("Division")} />
                  <CCardTitle className='text-sm'>Division</CCardTitle>
                </CCardBody>
              </CCard>
            </CCol>
            {/* Category Master Card */}
            <CCol xs="12" sm="6" md="2">
              <CCard className="text-center" style={{ backgroundColor: '#f5f7ff', height: '80px' }}>
                <CCardBody>
                  <CIcon icon={cilTag} size="xl" className='cursor-pointer' onClick={() => redirectPage("Cat")} />
                  <CCardTitle className='text-sm'>Category</CCardTitle>
                </CCardBody>
              </CCard>
            </CCol>
            {/* Sub-Category Master Card */}
            <CCol xs="12" sm="6" md="2">
              <CCard className="text-center" style={{ backgroundColor: '#f5f7ff', height: '80px' }}>
                <CCardBody>
                  <CIcon icon={cilTags} size="xl" className='cursor-pointer' onClick={() => redirectPage("SubCat")} />
                  <CCardTitle className='text-sm'>Sub Category</CCardTitle>
                </CCardBody>
              </CCard>
            </CCol>
            {/* UOM Master Card */}
            <CCol xs="12" sm="6" md="2">
              <CCard className="text-center" style={{ backgroundColor: '#f5f7ff', height: '80px' }}>
                <CCardBody>
                  <CIcon icon={cilCalculator} size="xl" className='cursor-pointer' onClick={() => redirectPage("UOM")} />
                  <CCardTitle className='text-sm'>UOM</CCardTitle>
                </CCardBody>
              </CCard>
            </CCol>
            {/* HSN/SAC Master Card */}
            <CCol xs="12" sm="6" md="2">
              <CCard className="text-center" style={{ backgroundColor: '#f5f7ff', height: '80px' }}>
                <CCardBody>
                  <CIcon icon={cilBarcode} size="xl" className='cursor-pointer' onClick={() => redirectPage("HSN")} />
                  <CCardTitle className='text-sm'>HSN/SAC</CCardTitle>
                </CCardBody>
              </CCard>
            </CCol>
            {/* Product Master Card */}
            <CCol xs="12" sm="6" md="2">
              <CCard className="text-center" style={{ backgroundColor: '#f5f7ff', height: '80px' }}>
                <CCardBody>
                  <CIcon icon={cilBalanceScale} size="xl" className='cursor-pointer' onClick={() => redirectPage("Product")} />
                  <CCardTitle className='text-sm'>Product</CCardTitle>
                </CCardBody>
              </CCard>
            </CCol>
          </CRow>
        </CCardBody>
      </CCard>

      <CCard className='mt-2'>
        <CCardSubtitle>Configuration</CCardSubtitle>
        <CCardBody>
          <CRow xs={{ gutterX: 3, gutterY: 3 }} style={{height: '90px'}}>
            {/* Department Head Master Card */}
            <CCol xs="12" sm="6" md="2">
              <CCard className="text-center" style={{ backgroundColor: '#f5f7ff', height: '80px' }}>
                <CCardBody>
                  <CIcon icon={cilObjectGroup} size="xl" className='cursor-pointer' onClick={() => redirectPage("DeptHead")} />
                  <CCardTitle className='text-sm'>Dept. Head</CCardTitle>
                </CCardBody>
              </CCard>
            </CCol>
            {/* Approver Master Card */}
            <CCol xs="12" sm="6" md="2">
              <CCard className="text-center" style={{ backgroundColor: '#f5f7ff', height: '80px' }}>
                <CCardBody>
                  <CIcon icon={cilCheckCircle} size="xl" className='cursor-pointer' onClick={() => redirectPage("Approve")} />
                  <CCardTitle className='text-sm'>Approver</CCardTitle>
                </CCardBody>
              </CCard>
            </CCol>
          </CRow>
        </CCardBody>
      </CCard>
    </>
  );
};

export default Masters;
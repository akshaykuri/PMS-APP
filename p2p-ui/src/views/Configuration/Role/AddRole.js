import { CButton, CCol, CForm, CFormInput, CFormTextarea, CModal, CModalBody, CModalFooter, CModalHeader, CRow, CSpinner } from '@coreui/react';
import axios from 'axios';
import React, { useRef, useState } from 'react'
import { toast } from 'react-toastify';
import { getFormattedCreatedOn } from '../../../utils/DateUtils';
import { draggableModal } from '../../SupportPages/draggableModal';
import { getDecryptedSessionItem } from '../../../utils/sessionUtils';

const AddRole = ({ addVisible, setAddVisible, refreshUserTypes }) => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const formattedRoleCreatedOn = getFormattedCreatedOn();

  const { modalRef, handleMouseDown, handleMouseMove, handleMouseUp } = draggableModal();

  const [loading, setLoading] = useState(false);

  const roleRef     = useRef('');
  const descRef     = useRef('');

  const jwtToken = getDecryptedSessionItem('token');

  const [roleName, setRoleName]                 = useState('');
  const [roleDescription, setRoleDescription]   = useState('');
  const roleCreatedBy                           = getDecryptedSessionItem('emp_id');
  const roleDeleteStatus                        = 0;

  const toggleVisibility = (isVisible) => {
    if(!isVisible){
      setRoleName('');
      setRoleDescription('');
    }
    setAddVisible(isVisible);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if(!roleName){
      toast.error('Please enter Role');
      roleRef.current.focus();
      return false;
    }else if(!roleDescription){
      toast.error('Please enter Role Description');
      descRef.current.focus();
      return false;
    }else{
      setLoading(true);
      const roleDTO = {
        roleName,
        roleDescription,
        roleCreatedBy,
        roleCreatedOn: formattedRoleCreatedOn,
        roleDeleteStatus
      };
      try{
        await axios.post(`${BASE_URL_P2P}role/roles`, roleDTO, {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
            'Content-Type': 'application/json',
          }
        });
        toggleVisibility(false);
        toast.success('Role added successfully.');
        refreshUserTypes();//Call the function to refresh user types
      }catch(error){
        if(error.response && error.response.status === 409){
          toast.error('Role already exists.');
        }else{
          toast.error('Something went wrong.');
        }
        console.error('Error====>', error.message);
      }finally{
        setLoading(false);
      }
    }
  };

  return (
    <>
      <CModal backdrop className='pt-5' visible={addVisible} onClose={() => toggleVisibility(false)} ref={modalRef}>
        <CModalHeader onClose={() => toggleVisibility(false)} style={{ cursor: 'move' }} onMouseMove={handleMouseMove} onMouseUp={handleMouseUp} onMouseLeave={handleMouseUp} onMouseDown={handleMouseDown}>
          <div className="font-semibold text-base">Role Master</div>
        </CModalHeader>
        <CModalBody>
          <CForm onSubmit={handleSubmit}>
            <CRow>
              <CCol xs={12}>
                <div className="floating-label">
                  <CFormInput size="sm" placeholder=" " onChange={(e) => setRoleName(e.target.value)} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={roleRef} />
                  <label>User Type</label>
                </div>
              </CCol>
            </CRow>
            <CRow>
              <CCol xs={12}>
                <div className="floating-label">
                  <CFormTextarea size="sm" placeholder=" " onChange={(e) => setRoleDescription(e.target.value)} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={descRef} />
                  <label>Description</label>
                </div>
              </CCol>
            </CRow>
            <CModalFooter>
              <CButton type='submit' color="primary" disabled={loading}>{loading ? (<CSpinner size='sm' />) : ('Submit')}</CButton>
              <CButton color="warning" onClick={() => toggleVisibility(false)}>Close</CButton>
            </CModalFooter>
          </CForm>
        </CModalBody>
      </CModal>
    </>
  )
}

export default AddRole
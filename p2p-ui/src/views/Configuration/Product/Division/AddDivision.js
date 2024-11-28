import { CButton, CCol, CForm, CFormInput, CFormTextarea, CModal, CModalBody, CModalFooter, CModalHeader, CRow, CSpinner } from '@coreui/react';
import axios from 'axios';
import React, { useRef, useState } from 'react'
import { toast } from 'react-toastify';
import { getFormattedCreatedOn } from '../../../../utils/DateUtils';
import { draggableModal } from '../../../SupportPages/draggableModal';
import { getDecryptedSessionItem } from '../../../../utils/sessionUtils';

const AddDivision = ({ addVisible, setAddVisible, refreshDivisions }) => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const formattedDivisionCreatedOn = getFormattedCreatedOn();

  const { modalRef, handleMouseDown, handleMouseMove, handleMouseUp } = draggableModal();

  const [loading, setLoading] = useState(false);

  const divisionRef = useRef('');
  const descRef     = useRef('');

  const jwtToken = getDecryptedSessionItem('token');

  const [divisionName, setDivisionName]                 = useState('');
  const [divisionDesc, setDivisionDesc]                 = useState('');
  const createdBy                                       = getDecryptedSessionItem('emp_id');
  const divDeleteStatus                                 = 0;

  const toggleVisibility = (isVisible) => {
    if(!isVisible){
      setDivisionName('');
      setDivisionDesc('');
    }
    setAddVisible(isVisible);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if(!divisionName){
      toast.error('Please enter Division');
      divisionRef.current.focus();
      return false;
    }else if(!divisionDesc){
      toast.error('Please enter Division Description');
      descRef.current.focus();
      return false;
    }else{
      setLoading(true);
      const divisionDTO = {
        divisionName,
        divisionDesc,
        createdBy,
        createdOn: formattedDivisionCreatedOn,
        divDeleteStatus
      };
      try{
        await axios.post(`${BASE_URL_P2P}company/addDiv`, divisionDTO, {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
            'Content-Type': 'application/json',
          }
        });
        toggleVisibility(false);
        toast.success('Division added successfully.');
        refreshDivisions();
      }catch(error){
        if(error.response && error.response.status === 409){
          toast.error('Division already exists.');
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
          <div className="font-semibold text-base">Division Master</div>
        </CModalHeader>
        <CModalBody>
          <CForm onSubmit={handleSubmit}>
            <CRow>
              <CCol xs={12}>
                <div className="floating-label">
                  <CFormInput size="sm" placeholder=" " onChange={(e) => setDivisionName(e.target.value)} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={divisionRef} />
                  <label>Division</label>
                </div>
              </CCol>
            </CRow>
            <CRow>
              <CCol xs={12}>
                <div className="floating-label">
                  <CFormTextarea size="sm" placeholder=" " onChange={(e) => setDivisionDesc(e.target.value)} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={descRef} />
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

export default AddDivision
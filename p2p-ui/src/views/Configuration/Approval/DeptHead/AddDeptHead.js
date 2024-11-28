import { CButton, CCol, CForm, CFormInput, CModal, CModalBody, CModalFooter, CModalHeader, CRow, CSpinner } from '@coreui/react';
import axios from 'axios';
import React, { useEffect, useRef, useState } from 'react';
import { toast } from 'react-toastify';
import { getFormattedCreatedOn } from '../../../../utils/DateUtils';
import { draggableModal } from '../../../SupportPages/draggableModal';
import { getDecryptedSessionItem } from '../../../../utils/sessionUtils';
import Department_DP from '../../../DropDowns/Department_DP';
import Branch_DP from '../../../DropDowns/Branch_DP';
import Employees_DP from '../../../DropDowns/Employees_DP';

const AddDeptHead = ({ addVisible, setAddVisible, refreshDeptHeads, editMode = false, deptId = null }) => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const formattedDeptHeadCreatedOn = getFormattedCreatedOn();

  const { modalRef, handleMouseDown, handleMouseMove, handleMouseUp } = draggableModal();

  const [loading, setLoading] = useState(false);

  const branchRef       = useRef('');
  const deptRef         = useRef('');
  const deptIdRef       = useRef('');

  const jwtToken = getDecryptedSessionItem('token');

  const [branchId, setBranchId]                     = useState('');
  const [branches, setBranches]                     = useState([]);
  const [departmentId, setDepartmentId]             = useState('');
  const [departments, setDepartments]               = useState([]);
  const [departmentHeadId, setDepartmentHeadId]     = useState('');
  const [departmentHeads, setDepartmentHeads]       = useState([]);
  const createdBy                                   = getDecryptedSessionItem('emp_id');
  const deptHeadDeleteStatus                        = 0;

  useEffect(() => {
    if (editMode && deptId) {
      axios.get(`${BASE_URL_P2P}company/getDeptHead/${deptId}`, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          'Content-Type': 'application/json',
        }
      })
      .then(response => {
        const deptData = response.data;
        setBranchId(deptData.branchId);
        setDepartmentId(deptData.deptId);
        setDepartmentHeadId(deptData.deptHeadId);
      })
      .catch(error => {
        console.error('Error fetching Department Head details', error);
        toast.error('Failed to load Department Head details');
      })
    }
  }, [editMode, deptId, BASE_URL_P2P, jwtToken]);

  const toggleVisibility = (isVisible) => {
    if(!isVisible){
      setBranchId('');
      setDepartmentId('');
      setDepartmentHeadId('');
    }
    setAddVisible(isVisible);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
      if(!branchId){
        toast.error('Please select Branch');
        branchRef.current.focus();
        return false;
      }else if(!departmentId){
        toast.error('Please select Department');
        deptRef.current.focus();
        return false;
      }else if(!departmentHeadId){
        toast.error('Please select Department Head');
        deptIdRef.current.focus();
        return false;
      }else{
        setLoading(true);
        const deptHeadDto = {
          branchId: branchId.value ? branchId.value : branchId,
          deptId: departmentId.value ? departmentId.value : departmentId,
          deptHeadId: departmentHeadId.value ? departmentHeadId.value : departmentHeadId,
          createdBy,
          createdOn: formattedDeptHeadCreatedOn,
          deptHeadDeleteStatus
        };        
        try{
          if(editMode){
            await axios.put(`${BASE_URL_P2P}company/updateDeptHead/${deptId}`, deptHeadDto, {
              headers: {
                Authorization: `Bearer ${jwtToken}`,
                'Content-Type': 'application/json',
              }
            });
            toast.success('Department Head updated Successfully.');
          }else{
            await axios.post(`${BASE_URL_P2P}company/addDeptHead`, deptHeadDto, {
              headers: {
                Authorization: `Bearer ${jwtToken}`,
                'Content-Type': 'application/json',
              }
            });
            toast.success('Department Head added Successfully.');
          }
          toggleVisibility(false);
          refreshDeptHeads();
        }catch(error){
          if(error.response && error.response.status === 409){
            toast.error('Department Head already exists.');
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
          <div className="font-semibold text-base">{editMode ? 'Update Department Head' :'Department Head Master'}</div>
        </CModalHeader>
        <CModalBody>
          <CForm onSubmit={handleSubmit}>
            <CRow>
              <CCol>
                <Branch_DP branchRef={branchRef} branches={branches} branchId={branchId} setBranches={setBranches} setBranchId={setBranchId} editMode={editMode} />
              </CCol>
            </CRow>
            <CRow>
              <CCol>
                <Department_DP deptRef={deptRef} departments={departments} deptId={departmentId} setDepartments={setDepartments} setDeptId={setDepartmentId} editMode={editMode} />
              </CCol>
            </CRow>
            <CRow>
              <CCol>
                <Employees_DP deptIdRef={deptIdRef} departmentHeads={departmentHeads} departmentHeadId={departmentHeadId} setDepartmentHeads={setDepartmentHeads} setDepartmentHeadId={setDepartmentHeadId} />
              </CCol>
            </CRow>
            <CModalFooter>
              <CButton type='submit' color="primary" disabled={loading}>{loading ? (<CSpinner size='sm' />) : (editMode ? 'Update' :'Submit')}</CButton>
              <CButton color="warning" onClick={() => toggleVisibility(false)} disabled={loading}>Close</CButton>
            </CModalFooter>
          </CForm>
        </CModalBody>
      </CModal>
    </>
  )
}

export default AddDeptHead
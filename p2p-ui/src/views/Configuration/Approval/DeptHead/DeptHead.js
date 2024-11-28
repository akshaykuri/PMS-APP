import { CButton, CCard, CCardBody, CCardTitle, CCol, CRow, CSpinner, CTooltip } from '@coreui/react';
import React, { useEffect, useState } from 'react'
import { toast, ToastContainer } from 'react-toastify';
import BackButton from '../../../SupportPages/BackButton';
import Table from '../../../SupportPages/Table';
import { FaPlus } from 'react-icons/fa';
import AddDeptHead from './AddDeptHead';
import axios from 'axios';
import { formatDate } from '../../../../utils/DateFormatter';
import { getDecryptedSessionItem } from '../../../../utils/sessionUtils';

const DeptHead = () => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const downloadName = "Department Head Master";
  const bId = getDecryptedSessionItem('branch_id');

  const [addVisible, setAddVisible]   = useState(false);
  const [editMode, setEditMode]       = useState(false);
  const [selectedId, setSelectedId]   = useState(null);
  const [loading, setLoading]         = useState(false);//for loading
  const [error, setError]             = useState(null);//for storing errors
  const [deptHeads, setDeptHeads]     = useState([]);

  const jwtToken = getDecryptedSessionItem('token');

  const handleAddClick = () => {
    setEditMode(false);
    setSelectedId(null);
    setAddVisible(true);
  }

  const fetchDeptHeads = async () => {
    try{
      const response = await axios.get(`${BASE_URL_P2P}company/deptHeads/${bId}`, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          'Content-Type': 'application/json',
        }
      });
      const formattedData = response.data.map(deptHead => ({
        ...deptHead,
        createdOn: formatDate(deptHead.createdOn)
      }));

      setDeptHeads(formattedData);
    }catch(error){
      setError(error);
    }finally{
      setLoading(false);
    }
  }

  useEffect(() => {
    setLoading(true);
    fetchDeptHeads();
  }, []);

  const handleDelete = async (deptHead) => {
    if(window.confirm(`Are you sure of Deleting the Department Head - ${deptHead.deptHeadName}?`)){
      try{
        await axios.put(`${BASE_URL_P2P}company/deleteDeptHead/${deptHead.id}`, {}, {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
            'Content-Type': 'application/json',
          }
        });
        toast.warning('Department Head deleted Successfully.');
        fetchDeptHeads();
      }catch(error){
        console.error('Error', error.message);
        toast.error('Something went Wrong.');
        fetchDeptHeads();
      }
    }
  };

  const handleUpdate = (deptHead) => {
    setEditMode(true);
    setSelectedId(deptHead.id);
    setAddVisible(true);
  }

  const columns = [
    { 
      key: 'id',
      label: 'Sl No.',
      render: (_, __, index) => index + 1, //Generate serial number based on the index
    },
    { key: 'branchName', label: 'Branch' },
    { key: 'deptName', label: 'Department' },
    { key: 'deptHeadId', label: 'Department Head - ID' },
    { key: 'deptHeadName', label: 'Department Head - Name' },
    { key: 'createdByName', label: 'Created By' },
    { key: 'createdOn', label: 'Created On' },
  ];

  const actions = [
    { label: 'Update', onClick: handleUpdate },
    { label: 'Delete', onClick: handleDelete },
  ];

  if(loading) return <div className="pt-3 text-center"><CSpinner color="primary" variant="grow" /></div>;//loader
  if(error) return <div className="pt-3 text-center">Error loading Department Heads : {error.message}</div>;//error message

  return (
    <>
      <ToastContainer position='top-right' autoClose={3000} />

      <CCard>
        <CCardTitle>
          <CRow className='d-flex justify-content-between align-items-center'>
            <CCol>Department Head</CCol>
            <CCol className='d-flex gap-2 justify-content-end'>
              <BackButton routePath={'/masters'} />
              <CTooltip content="Add Department" placement="bottom">
                <CButton color='success' shape='rounded-pill' onClick={handleAddClick}><FaPlus /></CButton>
              </CTooltip>
            </CCol>
          </CRow>
        </CCardTitle>

        <CCardBody>
          <Table data={deptHeads} columns={columns} actions={actions} downloadName={downloadName} />
        </CCardBody>
      </CCard>

      {addVisible && <AddDeptHead addVisible={addVisible} setAddVisible={setAddVisible} refreshDeptHeads={fetchDeptHeads} editMode={editMode} deptId={selectedId} />}
    </>
  )
}

export default DeptHead
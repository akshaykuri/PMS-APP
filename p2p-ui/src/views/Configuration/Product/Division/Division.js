import { CButton, CCard, CCardBody, CCardTitle, CCol, CRow, CSpinner, CTooltip } from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { toast, ToastContainer } from 'react-toastify'
import BackButton from '../../../SupportPages/BackButton'
import { FaPlus } from 'react-icons/fa'
import AddDivision from './AddDivision'
import axios from 'axios'
import { formatDate } from '../../../../utils/DateFormatter'
import Table from '../../../SupportPages/Table'
import { getDecryptedSessionItem } from '../../../../utils/sessionUtils'

const Division = () => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const downloadName = "Division Master";

  const [addVisible, setAddVisible] = useState(false);
  const [loading, setLoading] = useState(false);//for loading
  const [error, setError] = useState(null);//for storing errors
  const [divisions, setDivisions] = useState([]);

  const jwtToken = getDecryptedSessionItem('token');

  const handleAddClick = () => {
    setAddVisible(true);
  }

  const fetchDivisions = async () => {
    try{
      const response = await axios.get(`${BASE_URL_P2P}company/divisions`, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          'Content-Type': 'application/json',
        }
      });
      const formattedData = response.data.map(div => ({
        ...div,
        createdOn: formatDate(div.createdOn)
      }));

      setDivisions(formattedData);
    }catch(error){
      setError(error);
    }finally{
      setLoading(false);
    }
  };

  useEffect(() => {
    setLoading(true);
    fetchDivisions();
  }, []);

  const handleDelete = async (division) => {
    if(window.confirm(`Are you sure of Deleting the Division - ${division.divisionName}?`)){
      try{
        await axios.put(`${BASE_URL_P2P}company/deleteDiv/${division.id}`, {}, {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
            'Content-Type': 'application/json',
          }
        });
        toast.warning('Division deleted Successfully.');
        fetchDivisions();
      }catch(error){
        console.error('Error', error.message);
        toast.error('Something went Wrong.');
        fetchDivisions();
      }
    }
  };

  const columns = [
    { 
      key: 'id',
      label: 'Sl No.',
      render: (_, __, index) => index + 1, //Generate serial number based on the index
    },
    { key: 'divisionName', label: 'Division' },
    { key: 'divisionDesc', label: 'Description' },
    { key: 'createdByName', label: 'Created By' },
    { key: 'createdOn', label: 'Created On' },
  ];

  const actions = [
    { label: 'Delete', onClick: handleDelete },
  ];

  if(loading) return <div className="pt-3 text-center"><CSpinner color="primary" variant="grow" /></div>;//loader
  if(error) return <div className="pt-3 text-center">Error loading Divisions: {error.message}</div>;//error message

  return (
    <>
    <ToastContainer position='top-right' autoClose={3000} />

    <CCard>
      <CCardTitle>
        <CRow className='d-flex justify-content-between align-items-center'>
          <CCol>Division</CCol>
          <CCol className='d-flex gap-2 justify-content-end'>
            <BackButton routePath={'/masters'} />
            <CTooltip content="Add Division" placement="bottom">
              <CButton color='success' shape='rounded-pill' onClick={handleAddClick}><FaPlus /></CButton>
            </CTooltip>
          </CCol>
        </CRow>
      </CCardTitle>

      <CCardBody>
        <Table data={divisions} columns={columns} actions={actions} downloadName={downloadName} />
      </CCardBody>
    </CCard>

    {addVisible && <AddDivision addVisible={addVisible} setAddVisible={setAddVisible} refreshDivisions={fetchDivisions} />}
    </>
  )
}

export default Division
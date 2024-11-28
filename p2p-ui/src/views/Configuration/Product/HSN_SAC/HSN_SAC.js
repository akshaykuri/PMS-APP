import { CButton, CCard, CCardBody, CCardTitle, CCol, CRow, CSpinner, CTooltip } from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { toast, ToastContainer } from 'react-toastify'
import BackButton from '../../../SupportPages/BackButton'
import Table from '../../../SupportPages/Table'
import { formatDate } from '../../../../utils/DateFormatter'
import axios from 'axios'
import { FaPlus } from 'react-icons/fa'
import ADD_HSN_SAC from './ADD_HSN_SAC'
import { getDecryptedSessionItem } from '../../../../utils/sessionUtils'

const HSN_SAC = () => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const downloadName = "HSN_SAC Master";

  const [addVisible, setAddVisible] = useState(false);
  const [loading, setLoading] = useState(false);//for loading
  const [error, setError] = useState(null);//for storing errors
  const [hsn, setHsn] = useState([]);

  const jwtToken = getDecryptedSessionItem('token');

  const handleAddClick = () => {
    setAddVisible(true);
  }

  const fetchHSN = async () => {
    try{
      const response = await axios.get(`${BASE_URL_P2P}company/hsns`, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          'Content-Type': 'application/json',
        }
      });
      const formattedData = response.data.map(hsn => ({
        ...hsn,
        createdOn: formatDate(hsn.createdOn)
      }));

      setHsn(formattedData);
    }catch(error){
      setError(error);
    }finally{
      setLoading(false);
    }
  };

  useEffect(() => {
    setLoading(true);
    fetchHSN();
  }, []);

  const handleDelete = async (hsn) => {
    if(window.confirm(`Are you sure of Deleting the HSN/SAC - ${hsn.hsnCode}?`)){
      try{
        await axios.put(`${BASE_URL_P2P}company/deleteHsn/${hsn.id}`, {}, {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
            'Content-Type': 'application/json',
          }
        });
        toast.warning('HSN/SAC Code deleted Successfully.');
        fetchHSN();
      }catch(error){
        console.error('Error', error.message);
        toast.error('Something went Wrong.');
        fetchHSN();
      }
    }
  };

  const columns = [
    { 
      key: 'id',
      label: 'Sl No.',
      render: (_, __, index) => index + 1, //Generate serial number based on the index
    },
    { key: 'hsnCode', label: 'HSN/SAC Code' },
    { key: 'hsnDesc', label: 'Description' },
    { key: 'createdByName', label: 'Created By' },
    { key: 'createdOn', label: 'Created On' },
  ];

  const actions = [
    { label: 'Delete', onClick: handleDelete },
  ];

  if(loading) return <div className="pt-3 text-center"><CSpinner color="primary" variant="grow" /></div>;//loader
  if(error) return <div className="pt-3 text-center">Error loading HSN/SAC Codes: {error.message}</div>;//error message

  return (
    <>
    <ToastContainer position='top-right' autoClose={3000} />

    <CCard>
      <CCardTitle>
        <CRow className='d-flex justify-content-between align-items-center'>
          <CCol>HSN/SAC Code</CCol>
          <CCol className='d-flex gap-2 justify-content-end'>
            <BackButton routePath={'/masters'} />
            <CTooltip content="Add HSN/SAC" placement="bottom">
              <CButton color='success' shape='rounded-pill' onClick={handleAddClick}><FaPlus /></CButton>
            </CTooltip>
          </CCol>
        </CRow>
      </CCardTitle>

      <CCardBody>
        <Table data={hsn} columns={columns} actions={actions} downloadName={downloadName} />
      </CCardBody>
    </CCard>

    {addVisible && <ADD_HSN_SAC addVisible={addVisible} setAddVisible={setAddVisible} refreshHSN={fetchHSN} />}
    </>
  )
}

export default HSN_SAC
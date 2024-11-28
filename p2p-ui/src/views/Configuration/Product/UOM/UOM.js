import { CButton, CCard, CCardBody, CCardTitle, CCol, CRow, CSpinner, CTooltip } from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { toast, ToastContainer } from 'react-toastify'
import BackButton from '../../../SupportPages/BackButton'
import Table from '../../../SupportPages/Table'
import { formatDate } from '../../../../utils/DateFormatter'
import axios from 'axios'
import { FaPlus } from 'react-icons/fa'
import AddUOM from './AddUOM'
import { getDecryptedSessionItem } from '../../../../utils/sessionUtils'

const UOM = () => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const downloadName = "UOM Master";

  const [addVisible, setAddVisible] = useState(false);
  const [loading, setLoading] = useState(false);//for loading
  const [error, setError] = useState(null);//for storing errors
  const [uom, setUom] = useState([]);

  const jwtToken = getDecryptedSessionItem('token');

  const handleAddClick = () => {
    setAddVisible(true);
  }

  const fetchUOM = async () => {
    try{
      const response = await axios.get(`${BASE_URL_P2P}company/uoms`, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          'Content-Type': 'application/json',
        }
      });
      const formattedData = response.data.map(uom => ({
        ...uom,
        createdOn: formatDate(uom.createdOn)
      }));

      setUom(formattedData);
    }catch(error){
      setError(error);
    }finally{
      setLoading(false);
    }
  };

  useEffect(() => {
    setLoading(true);
    fetchUOM();
  }, []);

  const handleDelete = async (uom) => {
    if(window.confirm(`Are you sure of Deleting the UOM - ${uom.uomName}?`)){
      try{
        await axios.put(`${BASE_URL_P2P}company/deleteUom/${uom.id}`, {}, {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
            'Content-Type': 'application/json',
          }
        });
        toast.warning('UOM deleted Successfully.');
        fetchUOM();
      }catch(error){
        console.error('Error', error.message);
        toast.error('Something went Wrong.');
        fetchUOM();
      }
    }
  };

  const columns = [
    { 
      key: 'id',
      label: 'Sl No.',
      render: (_, __, index) => index + 1, //Generate serial number based on the index
    },
    { key: 'uomName', label: 'UOM' },
    { key: 'uomDesc', label: 'Description' },
    { key: 'createdByName', label: 'Created By' },
    { key: 'createdOn', label: 'Created On' },
  ];

  const actions = [
    { label: 'Delete', onClick: handleDelete },
  ];

  if(loading) return <div className="pt-3 text-center"><CSpinner color="primary" variant="grow" /></div>;//loader
  if(error) return <div className="pt-3 text-center">Error loading UOM: {error.message}</div>;//error message

  return (
    <>
      <ToastContainer position='top-right' autoClose={3000} />

      <CCard>
        <CCardTitle>
          <CRow className='d-flex justify-content-between align-items-center'>
            <CCol>UOM (Unit Of Measurements)</CCol>
            <CCol className='d-flex gap-2 justify-content-end'>
              <BackButton routePath={'/masters'} />
              <CTooltip content="Add UOM" placement="bottom">
                <CButton color='success' shape='rounded-pill' onClick={handleAddClick}><FaPlus /></CButton>
              </CTooltip>
            </CCol>
          </CRow>
        </CCardTitle>

        <CCardBody>
          <Table data={uom} columns={columns} actions={actions} downloadName={downloadName} />
        </CCardBody>
      </CCard>

      {addVisible && <AddUOM addVisible={addVisible} setAddVisible={setAddVisible} refreshUOM={fetchUOM} />}
    </>
  )
}

export default UOM
import { CButton, CCard, CCardBody, CCardTitle, CCol, CRow, CSpinner, CTooltip } from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { toast, ToastContainer } from 'react-toastify'
import BackButton from '../../../SupportPages/BackButton'
import Table from '../../../SupportPages/Table'
import { FaPlus } from 'react-icons/fa'
import AddCategory from './AddCategory'
import axios from 'axios'
import { formatDate } from '../../../../utils/DateFormatter'
import { getDecryptedSessionItem } from '../../../../utils/sessionUtils'

const Category = () => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const downloadName = "Category Master";

  const [addVisible, setAddVisible] = useState(false);
  const [loading, setLoading] = useState(false);//for loading
  const [error, setError] = useState(null);//for storing errors
  const [categories, setCategories] = useState([]);

  const jwtToken = getDecryptedSessionItem('token');

  const handleAddClick = () => {
    setAddVisible(true);
  }

  const fetchCategories = async () => {
    try{
      const response = await axios.get(`${BASE_URL_P2P}company/cats`, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          'Content-Type': 'application/json',
        }
      });
      const formattedData = response.data.map(cat => ({
        ...cat,
        createdOn: formatDate(cat.createdOn)
      }));

      setCategories(formattedData);
    }catch(error){
      setError(error);
    }finally{
      setLoading(false);
    }
  }

  useEffect(() => {
    setLoading(true);
    fetchCategories();
  }, []);

  const handleDelete = async (category) => {
    if(window.confirm(`Are you sure of Deleting the Category - ${category.categoryName}?`)){
      try{
        await axios.put(`${BASE_URL_P2P}company/deleteCat/${category.id}`, {}, {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
            'Content-Type': 'application/json',
          }
        });
        toast.warning('Category deleted Successfully.');
        fetchCategories();
      }catch(error){
        console.error('Error', error.message);
        toast.error('Something went Wrong.');
        fetchCategories();
      }
    }
  };

  const columns = [
    { 
      key: 'id',
      label: 'Sl No.',
      render: (_, __, index) => index + 1, //Generate serial number based on the index
    },
    { key: 'categoryName', label: 'Category' },
    { key: 'categoryDesc', label: 'Description' },
    { key: 'createdByName', label: 'Created By' },
    { key: 'createdOn', label: 'Created On' },
  ];

  const actions = [
    { label: 'Delete', onClick: handleDelete },
  ];

  if(loading) return <div className="pt-3 text-center"><CSpinner color="primary" variant="grow" /></div>;//loader
  if(error) return <div className="pt-3 text-center">Error loading Categories: {error.message}</div>;//error message

  return (
    <>
    <ToastContainer position='top-right' autoClose={3000} />

    <CCard>
      <CCardTitle>
        <CRow className='d-flex justify-content-between align-items-center'>
          <CCol>Category</CCol>
          <CCol className='d-flex gap-2 justify-content-end'>
            <BackButton routePath={'/masters'} />
            <CTooltip content="Add Category" placement="bottom">
              <CButton color='success' shape='rounded-pill' onClick={handleAddClick}><FaPlus /></CButton>
            </CTooltip>
          </CCol>
        </CRow>
      </CCardTitle>

      <CCardBody>
        <Table data={categories} columns={columns} actions={actions} downloadName={downloadName} />
      </CCardBody>
    </CCard>

    {addVisible && <AddCategory addVisible={addVisible} setAddVisible={setAddVisible} refreshCategories={fetchCategories} />}
    </>
  )
}

export default Category
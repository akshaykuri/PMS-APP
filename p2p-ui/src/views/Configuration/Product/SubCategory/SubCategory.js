import { CButton, CCard, CCardBody, CCardTitle, CCol, CRow, CSpinner, CTooltip } from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { toast, ToastContainer } from 'react-toastify'
import BackButton from '../../../SupportPages/BackButton'
import Table from '../../../SupportPages/Table'
import { FaPlus } from 'react-icons/fa'
import AddSubCategory from './AddSubCategory'
import axios from 'axios'
import { formatDate } from '../../../../utils/DateFormatter'
import { getDecryptedSessionItem } from '../../../../utils/sessionUtils'

const SubCategory = () => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const downloadName = "Sub-Category Master";

  const [addVisible, setAddVisible] = useState(false);
  const [loading, setLoading] = useState(false);//for loading
  const [error, setError] = useState(null);//for storing errors
  const [subcategories, setSubCategories] = useState([]);

  const jwtToken = getDecryptedSessionItem('token');

  const handleAddClick = () => {
    setAddVisible(true);
  }

  const fetchSubCategories = async () => {
    try{
      const response = await axios.get(`${BASE_URL_P2P}company/subCats`, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          'Content-Type': 'application/json',
        }
      });
      const formattedData = response.data.map(subCat => ({
        ...subCat,
        createdOn: formatDate(subCat.createdOn)
      }));

      setSubCategories(formattedData);
    }catch(error){
      setError(error);
    }finally{
      setLoading(false);
    }
  }

  useEffect(() => {
    setLoading(true);
    fetchSubCategories();
  }, []);

  const handleDelete = async (subCategory) => {
    if(window.confirm(`Are you sure of Deleting the Sub-Category - ${subCategory.subCategoryName}?`)){
      try{
        await axios.put(`${BASE_URL_P2P}company/deleteSubCat/${subCategory.id}`, {}, {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
            'Content-Type': 'application/json',
          }
        });
        toast.warning('Sub-Category deleted Successfully.');
        fetchSubCategories();
      }catch(error){
        console.error('Error', error.message);
        toast.error('Something went Wrong.');
        fetchSubCategories();
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
    { key: 'subCategoryName', label: 'Sub-Category' },
    { key: 'subCategoryDesc', label: 'Description' },
    { key: 'createdByName', label: 'Created By' },
    { key: 'createdOn', label: 'Created On' },
  ];

  const actions = [
    { label: 'Delete', onClick: handleDelete },
  ];

  if(loading) return <div className="pt-3 text-center"><CSpinner color="primary" variant="grow" /></div>;//loader
  if(error) return <div className="pt-3 text-center">Error loading Sub-Categories: {error.message}</div>;//error message

  return (
    <>
      <ToastContainer position='top-right' autoClose={3000} />

      <CCard>
        <CCardTitle>
          <CRow className='d-flex justify-content-between align-items-center'>
            <CCol>Sub Category</CCol>
            <CCol className='d-flex gap-2 justify-content-end'>
              <BackButton routePath={'/masters'} />
              <CTooltip content="Add Sub-Category" placement="bottom">
                <CButton color='success' shape='rounded-pill' onClick={handleAddClick}><FaPlus /></CButton>
              </CTooltip>
            </CCol>
          </CRow>
        </CCardTitle>

        <CCardBody>
          <Table data={subcategories} columns={columns} actions={actions} downloadName={downloadName} />
        </CCardBody>
      </CCard>

      {addVisible && <AddSubCategory addVisible={addVisible} setAddVisible={setAddVisible} refreshSubCategories={fetchSubCategories} />}
    </>
  )
}

export default SubCategory
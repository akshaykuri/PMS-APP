import { CButton, CCard, CCardBody, CCardTitle, CCol, CRow, CSpinner, CTooltip } from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { toast, ToastContainer } from 'react-toastify'
import BackButton from '../../../SupportPages/BackButton'
import { formatDate } from '../../../../utils/DateFormatter'
import axios from 'axios'
import { FaPlus } from 'react-icons/fa'
import AddProduct from './AddProduct'
import FilterableTable from '../../../SupportPages/FilterableTable'
import ViewProduct from './ViewProduct'
import { getDecryptedSessionItem } from '../../../../utils/sessionUtils'

const Product = () => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const downloadName = "Product Master";

  const [addVisible, setAddVisible] = useState(false);
  const [addViewVisible, setAddViewVisible] = useState(false);
  const [editMode, setEditMode] = useState(false);
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [loading, setLoading] = useState(false);//for loading
  const [error, setError] = useState(null);//for storing errors
  const [products, setProducts] = useState([]);

  const jwtToken = getDecryptedSessionItem('token');

  const handleAddClick = () => {
    setEditMode(false);
    setSelectedProduct(null);
    setAddVisible(true);
  }

  const fetchProducts = async () => {
    try{
      const response = await axios.get(`${BASE_URL_P2P}company/products`, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          'Content-Type': 'application/json',
        }
      });
      const formattedData = response.data.map(pro => ({
        ...pro,
        price: Number(pro.price).toLocaleString('en-IN', {minimumFractionDigits: 2, maximumFractionDigits: 2}),
        createdOn: formatDate(pro.createdOn)
      }));

      setProducts(formattedData);
    }catch(error){
      setError(error);
    }finally{
      setLoading(false);
    }
  };

  useEffect(() => {
    setLoading(true);
    fetchProducts();
  }, []);

  const handleDelete = async (product) => {
    if(window.confirm(`Are you sure of Deleting the Product - ${product.productName}?`)){
      try{
        await axios.put(`${BASE_URL_P2P}company/deleteProduct/${product.id}`, {}, {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
            'Content-Type': 'application/json',
          }
        });
        toast.warning('Product deleted Successfully.');
        fetchProducts();
      }catch(error){
        console.error('Error', error.message);
        toast.error('Something went Wrong.');
        fetchProducts();
      }
    }
  };

  const handleView = (product) => {
    setSelectedProduct(product.id)
    setAddViewVisible(true);
  }

  const handleUpdate = (product) => {
    setEditMode(true);
    setSelectedProduct(product.id);
    setAddVisible(true);
  }

  const columns = [
    { 
      key: 'id',
      label: 'Sl No.',
      render: (_, __, index) => index + 1, //Generate serial number based on the index
    },
    { key: 'productCode', label: 'Product Code' },
    { key: 'productName', label: 'Product Name' },
    { key: 'productDesc', label: 'Description' },
    { key: 'categoryName', label: 'Category' },
    { key: 'subCategoryName', label: 'Sub-Category' },
    { key: 'divisionName', label: 'Division' },
    { key: 'price', label: 'Purchase Type' },
    // { key: 'createdByName', label: 'Created By' },
    // { key: 'createdOn', label: 'Created On' },
  ];

  const actions = [
    { label: 'View', onClick: handleView },
    { label: 'Update', onClick: handleUpdate },
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
            <CCol>Product</CCol>
            <CCol className='d-flex gap-2 justify-content-end'>
              <BackButton routePath={'/masters'} />
              <CTooltip content="Add Product" placement="bottom">
                <CButton color='success' shape='rounded-pill' onClick={handleAddClick}><FaPlus /></CButton>
              </CTooltip>
            </CCol>
          </CRow>
        </CCardTitle>

        <CCardBody>
          <FilterableTable data={products} columns={columns} actions={actions} downloadName={downloadName} />
        </CCardBody>
      </CCard>

      {addVisible && <AddProduct addVisible={addVisible} setAddVisible={setAddVisible} refreshProduct={fetchProducts} editMode={editMode} prodId={selectedProduct} />}
      {addViewVisible && <ViewProduct addViewVisible={addViewVisible} setAddViewVisible={setAddViewVisible} prodId={selectedProduct} />}
    </>
  )
}

export default Product
import { CButton, CCol, CForm, CFormInput, CFormTextarea, CModal, CModalBody, CModalFooter, CModalHeader, CRow, CSpinner } from '@coreui/react';
import axios from 'axios';
import React, { useEffect, useRef, useState } from 'react'
import { toast } from 'react-toastify';
import { getFormattedCreatedOn } from '../../../../utils/DateUtils';
import { draggableModal } from '../../../SupportPages/draggableModal';
import HSNSAC_DP from '../../../DropDowns/HSNSAC_DP';
import Category_DP from '../../../DropDowns/Category_DP';
import SubCategory_DP from '../../../DropDowns/SubCategory_DP';
import Divisions_DP from '../../../DropDowns/Divisions_DP';
import UOM_DP from '../../../DropDowns/UOM_DP';
import PurchaseType_DP from '../../../DropDowns/PurchaseType_DP';
import { handleDecimalInput, handleSpecialCharacters, handleWholeNumberInput } from '../../../SupportPages/handleInput';
import { getDecryptedSessionItem } from '../../../../utils/sessionUtils';

const AddProduct = ({ addVisible, setAddVisible, refreshProduct, editMode = false, prodId = null }) => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const formattedProductCreatedOn = getFormattedCreatedOn();

  const { modalRef, handleMouseDown, handleMouseMove, handleMouseUp } = draggableModal();

  const [loading, setLoading] = useState(false);

  const prodCodeRef      = useRef('');
  const hsnCodeRef       = useRef('');
  const prodNameRef      = useRef('');
  const descRef          = useRef('');
  const catRef           = useRef('');
  const subCatRef        = useRef('');
  const minRef           = useRef(0);
  const maxRef           = useRef(0);
  const divRef           = useRef('');
  const typeRef          = useRef('');
  const uomRef           = useRef('');
  const priceRef         = useRef('');
  const manuRef          = useRef('');
  const modelRef         = useRef('');
  
  const jwtToken = getDecryptedSessionItem('token');

  const [hsns, setHsns]                       = useState([]);
  const [categories, setCategories]           = useState([]);
  const [subCategories, setSubCategories]     = useState([]);
  const [divisions, setDivisions]             = useState([]);
  const [uoms, setUoms]                       = useState([]);
  const [purchaseTypes, setPurchaseTypes]     = useState([]);

  const [productCode, setProductCode]         = useState('');
  const [hsnsacId, setHsnsacId]               = useState(0);
  const [productName, setProductName]         = useState('');
  const [productDesc, setProductDesc]         = useState('');
  const [categoryId, setCategoryId]           = useState(0);
  const [subCategoryId, setSubCategoryId]     = useState(0);
  const [minQty, setMinQty]                   = useState('');
  const [maxQty, setMaxQty]                   = useState('');
  const [prodDivId, setProdDivId]             = useState(0);
  const [purchaseType, setPurchaseType]       = useState('');
  const [uomId, setUomId]                     = useState(0);
  const [price, setPrice]                     = useState(0);
  const [manufacturer, setManufacturer]       = useState('');
  const [model, setModel]                     = useState('');
  const createdBy                             = getDecryptedSessionItem('emp_id');
  const prodDeleteStatus                      = 0;

  useEffect(() => {
    if (editMode && prodId) {
      axios.get(`${BASE_URL_P2P}company/getProduct/${prodId}`, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          'Content-Type': 'application/json',
        }
      })
      .then(response => {
        const prodData = response.data;
        setProductCode(prodData.productCode);
        setHsnsacId(prodData.hsnsacId);
        setProductName(prodData.productName);
        setProductDesc(prodData.productDesc);
        setCategoryId(prodData.categoryId);
        setSubCategoryId(prodData.subCategoryId);
        setMinQty(prodData.minQty);
        setMaxQty(prodData.maxQty);
        setProdDivId(prodData.prodDivId);
        setPurchaseType(prodData.purchaseType);
        setUomId(prodData.uomId);
        setPrice(parseFloat(prodData.price).toFixed(2));
        setManufacturer(prodData.manufacturer);
        setModel(prodData.model);
      })
      .catch(error => {
        console.error('Error fetching Product Details', error);
        toast.error('Failed to load Product Details');
      })
    }
  }, [editMode, prodId, BASE_URL_P2P, jwtToken]);

  const toggleVisibility = (isVisible) => {
    if(!isVisible){
      setProductCode('');setHsnsacId(0);setProductName('');setProductDesc('');setCategoryId(0);setSubCategoryId(0);setMinQty(0);setMaxQty(0);setProdDivId(0);setPurchaseType('');setUomId(0);setPrice(0);setManufacturer('');setModel('');
    }
    setAddVisible(isVisible);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if(!prodDivId){
      toast.error('Please select Division');
      divRef.current.focus();
      return false;
    }else if(!purchaseType){
      toast.error('Please select Purchase Type');
      typeRef.current.focus();
      return false;
    }else if(!productCode){
      toast.error('Please enter Product Code');
      prodCodeRef.current.focus();
      return false;
    }else if(!categoryId){
      toast.error('Please select Category');
      catRef.current.focus();
      return false;
    }else if(!subCategoryId){
      toast.error('Please select Sub-Category');
      subCatRef.current.focus();
      return false;
    }else if(!productName){
      toast.error('Please enter Product Name');
      prodNameRef.current.focus();
      return false;
    }else if(!productDesc){
      toast.error('Please enter Product Description');
      descRef.current.focus();
      return false;
    }else if(!uomId){
      toast.error('Please select UOM (Unit Of Measurement)');
      uomRef.current.focus();
      return false;
    }else{
      setLoading(true);
      const productDTO = {
        productCode,
        hsnsacId: hsnsacId,
        productName,
        productDesc,
        categoryId: categoryId.value ? categoryId.value : categoryId,
        subCategoryId: subCategoryId.value ? subCategoryId.value : subCategoryId,
        minQty,
        maxQty,
        prodDivId: prodDivId.value ? prodDivId.value : prodDivId,
        purchaseType: purchaseType.value ? purchaseType.value : purchaseType,
        uomId: uomId.value ? uomId.value : uomId,
        price,
        manufacturer,
        model,
        createdBy,
        createdOn: formattedProductCreatedOn,
        prodDeleteStatus
      };
      try{
        if(editMode){
          await axios.put(`${BASE_URL_P2P}company/updateProducts/${prodId}`, productDTO, {
            headers: {
              Authorization: `Bearer ${jwtToken}`,
              'Content-Type': 'application/json',
            }
          });
          toast.success('Product Details updated successfully.');
        }else{
          await axios.post(`${BASE_URL_P2P}company/addProduct`, productDTO, {
            headers: {
              Authorization: `Bearer ${jwtToken}`,
              'Content-Type': 'application/json',
            }
          });
          toast.success('Product Details added successfully.');
        }
        toggleVisibility(false);
        refreshProduct();
      }catch(error){
        if(error.response && error.response.status === 409){
          toast.error('Product Details already exists for selected Categories.');
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
      <CModal backdrop className='pt-2' visible={addVisible} size='lg' onClose={() => toggleVisibility(false)} ref={modalRef}>
        <CModalHeader onClose={() => toggleVisibility(false)} style={{ cursor: 'move' }} onMouseMove={handleMouseMove} onMouseUp={handleMouseUp} onMouseLeave={handleMouseUp} onMouseDown={handleMouseDown}>
          <div className="font-semibold text-base">{editMode ? 'Update Product Details' : 'Product Master'}</div>
        </CModalHeader>
        <CModalBody>
          <CForm onSubmit={handleSubmit}>
            <CRow>
              <CCol xs={12} md={6}>
                {/* for Divisions Drop-Down */}
                <Divisions_DP divRef={divRef} divisions={divisions} prodDivId={prodDivId} setDivisions={setDivisions} setProdDivId={setProdDivId} setProductCode={setProductCode} editMode={editMode} />
              </CCol>
              <CCol xs={12} md={6}>
                {/* for Purchase Type Drop-Down */}
                <PurchaseType_DP typeRef={typeRef} purchaseTypes={purchaseTypes} purchaseType={purchaseType} setPurchaseTypes={setPurchaseTypes} setPurchaseType={setPurchaseType} categoryId={categoryId} setCategoryId={setCategoryId} />
              </CCol>
            </CRow>
            <CRow>
              <CCol xs={12} md={6}>
                <div className="floating-label">
                  <CFormInput size="sm" placeholder=" " value={productCode} onChange={(e) => setProductCode(e.target.value)} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={prodCodeRef} readOnly />
                  <label>Product Code</label>
                </div>
              </CCol>
              <CCol xs={12} md={6}>
                {/* for HSN/SAC Code Drop-Down */}
                <HSNSAC_DP hsnCodeRef={hsnCodeRef} hsns={hsns} hsnsacId={hsnsacId} setHsns={setHsns} setHsnsacId={setHsnsacId} />
              </CCol>
            </CRow>
            <CRow>
              <CCol xs={12} md={6}>
                {/* for Category Drop-Down */}
                <Category_DP catRef={catRef} categories={categories} categoryId={categoryId} setCategories={setCategories} setCategoryId={setCategoryId} purchaseType={purchaseType} setSubCategoryId={setSubCategoryId} />
              </CCol>
              <CCol xs={12} md={6}>
                {/* for Sub-Category Drop-Down */}
                <SubCategory_DP subCatRef={subCatRef} subCategories={subCategories} subCategoryId={subCategoryId} setSubCategories={setSubCategories} setSubCategoryId={setSubCategoryId} catId={categoryId} />
              </CCol>
            </CRow>
            <CRow>
              <CCol xs={12} md={6}>
                <div className="floating-label">
                  <CFormInput size="sm" placeholder=" " value={productName} onChange={(e) => setProductName(e.target.value)} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={prodNameRef} />
                  <label>Product Name</label>
                </div>
              </CCol>
              <CCol xs={12} md={6}>
                <div className="floating-label">
                <CFormTextarea size="sm" placeholder=" " value={productDesc} onPaste={(e) => handleSpecialCharacters(e.target.value, setProductDesc)} onChange={(e) => handleSpecialCharacters(e.target.value, setProductDesc)} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={descRef} />
                  <label>Product Description</label>
                </div>
              </CCol>
            </CRow>
            <CRow>
              <CCol xs={12} md={6}>
                <div className="floating-label">
                  <CFormInput size="sm" placeholder=" " value={manufacturer} onChange={(e) => setManufacturer(e.target.value)} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={manuRef} />
                  <label>Manufacturer</label>
                </div>
              </CCol>
              <CCol xs={12} md={6}>
                <div className="floating-label">
                  <CFormInput size="sm" placeholder=" " value={model} onChange={(e) => setModel(e.target.value)} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={modelRef} />
                  <label>Model</label>
                </div>
              </CCol>
            </CRow>
            <CRow>
              <CCol xs={12} md={6}>
                {/* for UOM's Drop-Down */}
                <UOM_DP uomRef={uomRef} uoms={uoms} uomId={uomId} setUoms={setUoms} setUomId={setUomId} />
              </CCol>
              <CCol xs={12} md={6}>
                <div className="floating-label">
                  <CFormInput size="sm" placeholder=" " value={price} onChange={(e) => handleDecimalInput(e.target.value, setPrice)} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={priceRef} />
                  <label>Price</label>
                </div>
              </CCol>
            </CRow>
            <CRow>
              <CCol xs={12} md={6}>
                <div className="floating-label">
                  <CFormInput size="sm" placeholder=" " value={minQty} onChange={(e) => handleWholeNumberInput(e.target.value, setMinQty)} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={minRef} />
                  <label>Minimum Qty.</label>
                </div>
              </CCol>
              <CCol xs={12} md={6}>
                <div className="floating-label">
                  <CFormInput size="sm" placeholder=" " value={maxQty} onChange={(e) => handleWholeNumberInput(e.target.value, setMaxQty)} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={maxRef} />
                  <label>Maximum Qty.</label>
                </div>
              </CCol>
            </CRow>
            <CModalFooter>
                <CButton type='submit' color="primary" disabled={loading}>{loading ? (<CSpinner size='sm' />) : (editMode ? 'Update' : 'Submit')}</CButton>
                <CButton color="warning" onClick={() => toggleVisibility(false)}>Close</CButton>
            </CModalFooter>
          </CForm>
        </CModalBody>
      </CModal>
    </>
  )
}

export default AddProduct
import { CButton, CCol, CForm, CFormInput, CFormTextarea, CModal, CModalBody, CModalFooter, CModalHeader, CRow, CSpinner } from '@coreui/react';
import axios from 'axios';
import React, { useRef, useState } from 'react';
import { toast } from 'react-toastify';
import { getFormattedCreatedOn } from '../../../../utils/DateUtils';
import { draggableModal } from '../../../SupportPages/draggableModal';
import Category_DP from '../../../DropDowns/Category_DP';
import { getDecryptedSessionItem } from '../../../../utils/sessionUtils';
import PurchaseType_DP from '../../../DropDowns/PurchaseType_DP';

const AddSubCategory = ({ addVisible, setAddVisible, refreshSubCategories }) => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const formattedSubCategoryCreatedOn = getFormattedCreatedOn();

  const { modalRef, handleMouseDown, handleMouseMove, handleMouseUp } = draggableModal();

  const [loading, setLoading] = useState(false);

  const typeRef      = useRef('');
  const catRef      = useRef('');
  const subCatRef   = useRef('');
  const descRef     = useRef('');

  const jwtToken = getDecryptedSessionItem('token');

  const [categoryId, setCategoryId]                     = useState('');
  const [categories, setCategories]                     = useState([]);
  const [purchaseType, setPurchaseType]                 = useState('');
  const [purchaseTypes, setPurchaseTypes]               = useState([]);
  const [subCategoryName, setSubCategoryName]           = useState('');
  const [subCategoryDesc, setSubCategoryDesc]           = useState('');
  const createdBy                                       = getDecryptedSessionItem('emp_id');
  const subCatDeleteStatus                              = 0;

  const toggleVisibility = (isVisible) => {
    if(!isVisible){
      setCategoryId('');
      setSubCategoryName('');
      setSubCategoryDesc('');
    }
    setAddVisible(isVisible);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if(!categoryId){
      toast.error('Please select Category');
      catRef.current.focus();
      return false;
    }else if(!subCategoryName){
      toast.error('Please enter Sub-Category');
      subCatRef.current.focus();
      return false;
    }else if(!subCategoryDesc){
      toast.error('Please enter Description');
      descRef.current.focus();
      return false;
    }else{
      setLoading(true);
      const subCategoryDto = {
        categoryId: categoryId?.value,
        subCategoryName,
        subCategoryDesc,
        createdBy,
        createdOn: formattedSubCategoryCreatedOn,
        subCatDeleteStatus
      };
      try{
        await axios.post(`${BASE_URL_P2P}company/addSubCat`, subCategoryDto, {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
            'Content-Type': 'application/json',
          }
        });
        toggleVisibility(false);
        toast.success('Sub-Category added Successfully.');
        refreshSubCategories();
      }catch(error){
        if(error.response && error.response.status === 409){
          toast.error('Sub-Category already exists.');
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
          <div className="font-semibold text-base">Sub-Category Master</div>
        </CModalHeader>
        <CModalBody>
          <CForm onSubmit={handleSubmit}>
            <CRow>
              <CCol xs={12}>
                <PurchaseType_DP typeRef={typeRef} purchaseTypes={purchaseTypes} purchaseType={purchaseType} setPurchaseTypes={setPurchaseTypes} setPurchaseType={setPurchaseType} categoryId={categoryId} setCategoryId={setCategoryId} />
              </CCol>
            </CRow>
            <CRow>
              <CCol xs={12}>
                <Category_DP catRef={catRef} categories={categories} categoryId={categoryId} setCategories={setCategories} setCategoryId={setCategoryId} purchaseType={purchaseType} setSubCategoryId={setSubCategoryName} />
              </CCol>
            </CRow>
            <CRow>
              <CCol xs={12}>
                <div className="floating-label">
                  <CFormInput size="sm" placeholder=" " onChange={(e) => setSubCategoryName(e.target.value)} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={subCatRef} />
                  <label>Sub-Category</label>
                </div>
              </CCol>
            </CRow>
            <CRow>
              <CCol xs={12}>
                <div className="floating-label">
                  <CFormTextarea size="sm" placeholder=" " onChange={(e) => setSubCategoryDesc(e.target.value)} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={descRef} />
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

export default AddSubCategory
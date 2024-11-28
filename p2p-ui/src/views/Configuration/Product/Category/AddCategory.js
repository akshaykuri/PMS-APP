import { CButton, CCol, CForm, CFormInput, CFormTextarea, CModal, CModalBody, CModalFooter, CModalHeader, CRow, CSpinner } from '@coreui/react';
import axios from 'axios';
import React, { useRef, useState } from 'react'
import { toast } from 'react-toastify';
import { getFormattedCreatedOn } from '../../../../utils/DateUtils';
import { draggableModal } from '../../../SupportPages/draggableModal';
import PurchaseType_DP from '../../../DropDowns/PurchaseType_DP';
import { getDecryptedSessionItem } from '../../../../utils/sessionUtils';

const AddCategory = ({ addVisible, setAddVisible, refreshCategories }) => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const formattedCategoryCreatedOn = getFormattedCreatedOn();

  const { modalRef, handleMouseDown, handleMouseMove, handleMouseUp } = draggableModal();

  const [loading, setLoading] = useState(false);

  const typeRef     = useRef('');
  const catRef      = useRef('');
  const descRef     = useRef('');

  const [purchaseTypes, setPurchaseTypes]       = useState([]);

  const jwtToken = getDecryptedSessionItem('token');

  const [purchaseType, setPurchaseType]                 = useState('');
  const [categoryName, setCategoryName]                 = useState('');
  const [categoryDesc, setCategoryDesc]                 = useState('');
  const createdBy                                       = getDecryptedSessionItem('emp_id');
  const catDeleteStatus                                 = 0;

  const toggleVisibility = (isVisible) => {
    if(!isVisible){
      setCategoryName('');
      setCategoryDesc('');
    }
    setAddVisible(isVisible);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if(!purchaseType){
      toast.error('Please select Purchase Type');
      typeRef.current.focus();
      return false;
    }else if(!categoryName){
      toast.error('Please enter Category');
      catRef.current.focus();
      return false;
    }else if(!categoryDesc){
      toast.error('Please enter Description');
      descRef.current.focus();
      return false;
    }else{
      setLoading(true);
      const categoryDTO = {
        categoryName,
        categoryDesc,
        purchaseType: purchaseType.value,
        createdBy,
        createdOn: formattedCategoryCreatedOn,
        catDeleteStatus
      };
      try{
        await axios.post(`${BASE_URL_P2P}company/addCat`, categoryDTO, {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
            'Content-Type': 'application/json',
          }
        });
        toggleVisibility(false);
        toast.success('Category added successfully.');
        refreshCategories();
      }catch(error){
        if(error.response && error.response.status === 409){
          toast.error('Category already exists.');
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
          <div className="font-semibold text-base">Category Master</div>
        </CModalHeader>
        <CModalBody>
          <CForm onSubmit={handleSubmit}>
            <CRow>
              <CCol xs={12}>
                <PurchaseType_DP typeRef={typeRef} purchaseTypes={purchaseTypes} purchaseType={purchaseType} setPurchaseTypes={setPurchaseTypes} setPurchaseType={setPurchaseType} />
              </CCol>
            </CRow>
            <CRow>
              <CCol xs={12}>
                <div className="floating-label">
                  <CFormInput size="sm" placeholder=" " onChange={(e) => setCategoryName(e.target.value)} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={catRef} />
                  <label>Category</label>
                </div>
              </CCol>
            </CRow>
            <CRow>
              <CCol xs={12}>
                <div className="floating-label">
                  <CFormTextarea size="sm" placeholder=" " onChange={(e) => setCategoryDesc(e.target.value)} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={descRef} />
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

export default AddCategory
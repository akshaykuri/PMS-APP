import axios from 'axios';
import React, { useEffect } from 'react'
import Select from 'react-select';
import { toast } from 'react-toastify';
import { getDecryptedSessionItem } from '../../utils/sessionUtils';

const Category_DP = ({ catRef, categories, categoryId, setCategories, setCategoryId, purchaseType, setSubCategoryId }) => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const jwtToken = getDecryptedSessionItem('token');

  if(purchaseType.value){
    purchaseType = purchaseType.value
  }else{
    purchaseType = purchaseType;
  }

  const fetchCategories = async () => {
    axios.get(`${BASE_URL_P2P}company/getCatsOnPurType/${purchaseType}`, {
      headers: {
        Authorization: `Bearer ${jwtToken}`,
        'Content-Type': 'application/json',
      },
    })
    .then(response => {
      const catsData = response.data.map((cat) => ({
        label: cat.categoryName,
        value: cat.id,
      }))
      setCategories(catsData);
    })
    .catch(error => {
      console.error('Error fetching Category details', error);
      toast.error('Failed to load Category details');
    })
  }

  useEffect(() => {
    if(purchaseType){
      fetchCategories();
    }
  },[purchaseType]);

  return (
    <div className="floating-label">
      <Select id='Cat-select' value={categories.find(cat => cat.value === categoryId)} onChange={(e) => { setCategoryId(e); setSubCategoryId(0)}} onFocus={e => e.target.classList.add('focused')} onBlur={(e) => !categoryId && e.target.classList.remove('focused')} options={categories} placeholder="" ref={catRef} isMulti={false} isSearchable />
      <label htmlFor='Cat-select' className={categoryId ? 'active' : 'floating'}>Category</label>
    </div>
  )
}

export default Category_DP
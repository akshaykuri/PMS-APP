import axios from 'axios';
import React, { useEffect } from 'react'
import Select from 'react-select';
import { toast } from 'react-toastify';
import { getDecryptedSessionItem } from '../../utils/sessionUtils';

const SubCategory_DP = ({ subCatRef, subCategories, subCategoryId, setSubCategories, setSubCategoryId, catId }) => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const jwtToken = getDecryptedSessionItem('token');

  const fetchSubCategories = async () => {
    if(catId.value){
      catId = catId.value;
    }else{
      catId = catId;
    }

    axios.get(`${BASE_URL_P2P}company/getSubCats/${catId}`, {
      headers: {
        Authorization: `Bearer ${jwtToken}`,
        'Content-Type': 'application/json',
      },
    })
    .then(response => {
      const subCatsData = response.data.map((subCat) => ({
        label: subCat.subCategoryName,
        value: subCat.id,
      }))
      setSubCategories(subCatsData);
    })
    .catch(error => {
      console.error('Error fetching Sub-Category details', error);
      toast.error('Failed to load Sub-Category details');
    })
  }

  useEffect(() => {
    if(catId){
      fetchSubCategories();
    }
  },[catId]);

  return (
    <div className="floating-label">
      <Select id='subCat-select' value={subCategories.find(subCat => subCat.value === subCategoryId)} onChange={(e) => setSubCategoryId(e)} onFocus={e => e.target.classList.add('focused')} onBlur={(e) => !subCategoryId && e.target.classList.remove('focused')} options={subCategories} placeholder="" ref={subCatRef} isMulti={false} isSearchable />
      <label htmlFor='subCat-select' className={subCategoryId ? 'active' : 'floating'}>Sub-Category</label>
    </div>
  )
}

export default SubCategory_DP
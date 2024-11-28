import axios from 'axios';
import React, { useEffect } from 'react'
import Select from 'react-select';
import { toast } from 'react-toastify';
import { getDecryptedSessionItem } from '../../utils/sessionUtils';

const PurchaseTypeForPR_DP = ({ typeRef, purchaseTypes, purchaseType, setPurchaseTypes, setPurchaseType }) => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const jwtToken = getDecryptedSessionItem('token');

  const fetchPurchaseTypes = async () => {
    axios.get(`${BASE_URL_P2P}company/getPurTypes`, {
      headers: {
        Authorization: `Bearer ${jwtToken}`,
        'Content-Type': 'application/json',
      },
    })
    .then(response => {
      const purchaseTypeData = response.data.map((pt) => ({
        label: pt.purchaseType,
        value: pt.purchaseType,
      }))
      setPurchaseTypes(purchaseTypeData);
    })
    .catch(error => {
      console.error('Error fetching Purchase Type details', error);
      toast.error('Failed to load Purchase Type details');
    })
  }

  useEffect(() => {
    fetchPurchaseTypes();
  },[]);

  return (
    <div className="floating-label w-auto">
      <Select id='Purchase-Type-select' value={purchaseTypes.find(pur => pur.value === purchaseType)} onChange={(e) => setPurchaseType(e)} onFocus={e => e.target.classList.add('focused')} onBlur={(e) => !purchaseType && e.target.classList.remove('focused')} options={purchaseTypes} placeholder="" ref={typeRef} isMulti={false} isSearchable />
      <label htmlFor='Purchase-Type-select' className={purchaseType ? 'active' : 'floating'}>Purchase Type</label>
    </div>
  )
}

export default PurchaseTypeForPR_DP
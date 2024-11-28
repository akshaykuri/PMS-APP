import axios from 'axios';
import React, { useEffect } from 'react'
import Select from 'react-select';
import { toast } from 'react-toastify';
import { getDecryptedSessionItem } from '../../utils/sessionUtils';

const HSNSAC_DP = ({ hsnCodeRef, hsns, hsnsacId, setHsns, setHsnsacId }) => {

  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const jwtToken = getDecryptedSessionItem('token');

  const fetchHsnSacCodes = async () => {
    axios.get(`${BASE_URL_P2P}company/getHsns`, {
        headers: {
        Authorization: `Bearer ${jwtToken}`,
        'Content-Type': 'application/json',
        },
    })
    .then(response => {
        const hsnsData = response.data.map((hsn) => ({
        label: hsn.hsnCode,
        value: hsn.id,
        }))
        setHsns(hsnsData);
    })
    .catch(error => {
        console.error('Error fetching HSN/SAC Codes', error);
        toast.error('Failed to load HSN details');
    })
  }

  useEffect(() => {
    fetchHsnSacCodes();
  },[]);

  return (
    <div className="floating-label">
      <Select id='Hsn-select' value={hsns.find(hsn => hsn.value === hsnsacId) || null} onChange={(e) => setHsnsacId(e.value)} onFocus={e => e.target.classList.add('focused')} onBlur={(e) => !hsnsacId && e.target.classList.remove('focused')} options={hsns} ref={hsnCodeRef} placeholder="" isMulti={false} isSearchable />
      <label htmlFor='Hsn-select' className={hsnsacId ? 'active' : 'floating'}>HSN/SAC Code</label>
    </div>
  )
}

export default HSNSAC_DP
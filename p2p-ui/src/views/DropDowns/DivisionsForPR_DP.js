import axios from 'axios';
import React, { useEffect } from 'react'
import Select from 'react-select';
import { toast } from 'react-toastify';
import { getDecryptedSessionItem } from '../../utils/sessionUtils';

const DivisionsForPR_DP = ({ divRef, divisions, divId, setDivisions, setDivId }) => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const jwtToken = getDecryptedSessionItem('token');

  const fetchDivisions = async () => {
    axios.get(`${BASE_URL_P2P}company/getDivs`, {
      headers: {
        Authorization: `Bearer ${jwtToken}`,
        'Content-Type': 'application/json',
      },
    })
    .then(response => {
      const divsData = response.data.map((div) => ({
        label: div.divisionName,
        value: div.id,
      }))
      setDivisions(divsData);
    })
    .catch(error => {
      console.error('Error fetching Division details', error);
      toast.error('Failed to load Division details');
    })
  }

  useEffect(() => {
    fetchDivisions();
  },[]);

  return (
    <div className="floating-label w-auto">
      <Select id='Div-select' value={divisions.find(div => div.value === divId)} onChange={(e) => setDivId(e)} onFocus={e => e.target.classList.add('focused')} onBlur={(e) => !divId && e.target.classList.remove('focused')} options={divisions} placeholder="" ref={divRef} isMulti={false} isSearchable />
      <label htmlFor='Div-select' className={divId ? 'active' : 'floating'}>Division</label>
    </div>
  )
}

export default DivisionsForPR_DP
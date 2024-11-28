import axios from 'axios';
import React, { useEffect } from 'react'
import Select from 'react-select';
import { toast } from 'react-toastify';
import { getDecryptedSessionItem } from '../../utils/sessionUtils';

const Divisions_DP = ({ divRef, divisions, prodDivId, setDivisions, setProdDivId, setProductCode, editMode = false }) => {
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

  const handleDivisionChange = (divId) => {
    setProdDivId(divId);
    axios.get(`${BASE_URL_P2P}company/getProductCode/${divId?.value}`, {
      headers: {
        Authorization: `Bearer ${jwtToken}`,
        'Content-Type': 'application/json',
      },
    })
    .then(response => {
      setProductCode(response.data);
    })
    .catch(error => {
      console.error('Error fetching Product Code', error);
      toast.error('Failed to load Product Code');
    })
  }

  useEffect(() => {
    fetchDivisions();
  },[]);

  return (
    <div className="floating-label">
      <Select id='Div-select' value={divisions.find(div => div.value === prodDivId)} onChange={handleDivisionChange} onFocus={e => e.target.classList.add('focused')} onBlur={(e) => !prodDivId && e.target.classList.remove('focused')} options={divisions} placeholder="" ref={divRef} isMulti={false} isSearchable isDisabled={editMode && (prodDivId !== 0)} />
      <label htmlFor='Div-select' className={prodDivId ? 'active' : 'floating'}>Division</label>
    </div>
  )
}

export default Divisions_DP
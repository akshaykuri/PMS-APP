import axios from 'axios';
import React, { useEffect } from 'react'
import Select from 'react-select';
import { toast } from 'react-toastify';
import { getDecryptedSessionItem } from '../../utils/sessionUtils';

const UOM_DP = ({ uomRef, uoms, uomId, setUoms, setUomId }) => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const jwtToken = getDecryptedSessionItem('token');

  const fetchUOM = async () => {
    axios.get(`${BASE_URL_P2P}company/getUoms`, {
      headers: {
        Authorization: `Bearer ${jwtToken}`,
        'Content-Type': 'application/json',
      },
    })
    .then(response => {
      const uomsData = response.data.map((uom) => ({
        label: uom.uomName,
        value: uom.id,
      }))
      setUoms(uomsData);
    })
    .catch(error => {
      console.error('Error fetching UOM details', error);
      toast.error('Failed to load UOM details');
    })
  }

  useEffect(() => {
    fetchUOM();
  },[]);

  return (
    <div className="floating-label">
      <Select id='Uom-select' value={uoms.find(uom => uom.value === uomId)} onChange={(e) => setUomId(e)} onFocus={e => e.target.classList.add('focused')} onBlur={(e) => !uomId && e.target.classList.remove('focused')} options={uoms} placeholder="" ref={uomRef} isMulti={false} isSearchable />
      <label htmlFor='Uom-select' className={uomId ? 'active' : 'floating'}>UOM (Unit of Measurement)</label>
    </div>
  )
}

export default UOM_DP
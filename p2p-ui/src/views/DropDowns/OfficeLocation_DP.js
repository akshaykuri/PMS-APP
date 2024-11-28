import axios from 'axios';
import React, { useEffect } from 'react'
import Select from 'react-select';
import { toast } from 'react-toastify';
import { getDecryptedSessionItem } from '../../utils/sessionUtils';

const OfficeLocation_DP = ({ locRef, locations, locId, setLocations, setLocId }) => {
  const BASE_URL_HRMS = import.meta.env.VITE_HRMS_API_URL;
  const jwtToken = getDecryptedSessionItem('token');
  const bNo = getDecryptedSessionItem('branch_type_code');

  const fetchOfficeLocations = async () => {
    axios.get(`${BASE_URL_HRMS}masters/getOfficeLocs/${bNo}`, {
      headers: {
        Authorization: `Bearer ${jwtToken}`,
        'Content-Type': 'application/json',
      },
    })
    .then(response => {
      const locsData = response.data.map((locs) => ({
        label: locs.bName,
        value: locs.id,
        bNo: locs.bNo,
      }))
      setLocations(locsData);
    })
    .catch(error => {
      console.error('Error fetching Office Location details', error);
      toast.error('Failed to load Office Location details');
    })
  }

  const handleLocationChange = (locationId) => {
    setLocId(locationId);
  }

  useEffect(() => {
    fetchOfficeLocations();
  },[]);

  return (
    <div className="floating-label w-auto">
      <Select id='Loc-select' value={locations.find(loc => loc.value === locId)} onChange={handleLocationChange} onFocus={e => e.target.classList.add('focused')} onBlur={(e) => !locId && e.target.classList.remove('focused')} options={locations} placeholder="" ref={locRef} isMulti={false} isSearchable />
      <label htmlFor='Loc-select' className={locId ? 'active' : 'floating'}>Office Location</label>
    </div>
  )
}

export default OfficeLocation_DP
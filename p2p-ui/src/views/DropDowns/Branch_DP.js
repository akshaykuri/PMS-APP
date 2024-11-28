import axios from 'axios';
import React, { useEffect } from 'react'
import Select from 'react-select';
import { toast } from 'react-toastify';
import { getDecryptedSessionItem } from '../../utils/sessionUtils';

const Branch_DP = ({ branchRef, branches, branchId, setBranches, setBranchId, editMode = false }) => {
  const BASE_URL_HRMS = import.meta.env.VITE_HRMS_API_URL;
  const jwtToken = getDecryptedSessionItem('token');
  const bNo = getDecryptedSessionItem('branch_type_code');

  const fetchBranches = async () => {
    axios.get(`${BASE_URL_HRMS}masters/getBranches/${bNo}`, {
      headers: {
        Authorization: `Bearer ${jwtToken}`,
        'Content-Type': 'application/json',
      },
    })
    .then(response => {
      const brData = response.data.map((brs) => ({
        label: brs.branchName,
        value: brs.branchId,
      }))
      setBranches(brData);
    })
    .catch(error => {
      console.error('Error fetching Branch details', error);
      toast.error('Failed to load Branch details');
    })
  }

  const handleBranchChange = (brId) => {
    setBranchId(brId);
  }

  useEffect(() => {
    fetchBranches();
  },[]);

  return (
    <div className="floating-label w-auto">
      <Select id='Branch-select' value={branches.find(brc => brc.value === branchId)} onChange={handleBranchChange} onFocus={e => e.target.classList.add('focused')} onBlur={(e) => !branchId && e.target.classList.remove('focused')} options={branches} placeholder="" ref={branchRef} isMulti={false} isSearchable isDisabled={editMode && (branchId !== 0)} />
      <label htmlFor='Branch-select' className={branchId ? 'active' : 'floating'}>Branch</label>
    </div>
  )
}

export default Branch_DP
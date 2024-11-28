import axios from 'axios';
import React, { useEffect } from 'react'
import Select from 'react-select';
import { toast } from 'react-toastify';
import { getDecryptedSessionItem } from '../../utils/sessionUtils';

const Department_DP = ({ deptRef, departments, deptId, setDepartments, setDeptId, editMode = false }) => {
  const BASE_URL_HRMS = import.meta.env.VITE_HRMS_API_URL;
  const jwtToken = getDecryptedSessionItem('token');

  const fetchDepartments = async () => {
    axios.get(`${BASE_URL_HRMS}masters/getDepartments`, {
      headers: {
        Authorization: `Bearer ${jwtToken}`,
        'Content-Type': 'application/json',
      },
    })
    .then(response => {
      const deptsData = response.data.map((depts) => ({
        label: depts.departmentName,
        value: depts.departmentId,
      }))
      setDepartments(deptsData);
    })
    .catch(error => {
      console.error('Error fetching Department details', error);
      toast.error('Failed to load Department details');
    })
  }

  const handleDepartmentChange = (deptId) => {
    setDeptId(deptId);
  }

  useEffect(() => {
    fetchDepartments();
  },[]);

  return (
    <div className="floating-label w-auto">
      <Select id='Dept-select' value={departments.find(dept => dept.value === deptId)} onChange={handleDepartmentChange} onFocus={e => e.target.classList.add('focused')} onBlur={(e) => !deptId && e.target.classList.remove('focused')} options={departments} placeholder="" ref={deptRef} isMulti={false} isSearchable isDisabled={editMode && (deptId !== 0)} />
      <label htmlFor='Dept-select' className={deptId ? 'active' : 'floating'}>Department</label>
    </div>
  )
}

export default Department_DP
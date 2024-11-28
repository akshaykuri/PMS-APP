import axios from 'axios';
import React, { useEffect } from 'react'
import Select from 'react-select';
import { toast } from 'react-toastify';
import { getDecryptedSessionItem } from '../../utils/sessionUtils';

const Employees_DP = ({ deptIdRef, departmentHeads, departmentHeadId, setDepartmentHeads, setDepartmentHeadId}) => {
  const BASE_URL_HRMS = import.meta.env.VITE_HRMS_API_URL;
  const jwtToken = getDecryptedSessionItem('token');
  const bId = getDecryptedSessionItem('branch_id');

  const fetchEmployees = async () => {
    axios.get(`${BASE_URL_HRMS}masters/getEmployees/${bId}`, {
      headers: {
        Authorization: `Bearer ${jwtToken}`,
        'Content-Type': 'application/json',
      },
    })
    .then(response => {
      const empData = response.data.map((emps) => ({
        label: emps.userName + ' - ' + emps.empId,
        value: emps.empId,
      }))
      setDepartmentHeads(empData);
    })
    .catch(error => {
      console.error('Error fetching Employee details', error);
      toast.error('Failed to load Employee details');
    })
  }

  const handleEmployeeChange = (empId) => {
    setDepartmentHeadId(empId);
  }

  useEffect(() => {
    fetchEmployees();
  }, []);

  return (
    <div className="floating-label w-auto">
      <Select id='Dept-select' value={departmentHeads.find(eid => eid.value.toString() === departmentHeadId.toString())} onChange={handleEmployeeChange} onFocus={e => e.target.classList.add('focused')} onBlur={(e) => !departmentHeadId && e.target.classList.remove('focused')} options={departmentHeads} placeholder="" ref={deptIdRef} isMulti={false} isSearchable />
      <label htmlFor='Dept-select' className={departmentHeadId ? 'active' : 'floating'}>Department Head</label>
    </div>
  )
}

export default Employees_DP
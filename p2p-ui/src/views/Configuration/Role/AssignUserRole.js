import React, { useEffect, useRef, useState } from 'react';
import { getFormattedCreatedOn } from '../../../utils/DateUtils';
import { toast } from 'react-toastify';
import { CButton, CCol, CForm, CFormInput, CModal, CModalBody, CModalHeader, CRow, CListGroup, CListGroupItem, CModalFooter, CSpinner } from '@coreui/react';
import axios from 'axios';
import { FaAngleDoubleLeft, FaAngleDoubleRight, FaAngleLeft, FaAngleRight } from 'react-icons/fa';
import { draggableModal } from '../../SupportPages/draggableModal';
import { handleWholeNumberInput } from '../../SupportPages/handleInput';
import { getDecryptedSessionItem } from '../../../utils/sessionUtils';

const AssignUserRole = ({ addUserRoleVisible, setAddUserRoleVisible, refreshRolewiseUsers, editMode = false, userId = null }) => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const formattedRoleCreatedOn = getFormattedCreatedOn();

  const { modalRef, handleMouseDown, handleMouseMove, handleMouseUp } = draggableModal();

  const [loading, setLoading] = useState(false);

  const jwtToken = getDecryptedSessionItem('token');
  const userRoleCreatedBy = getDecryptedSessionItem('emp_id');
  const userRoleDeleteStatus = 0;
  const activeStatus = 0;

  const empIdRef = useRef('');

  const [empId, setEmpId] = useState('');
  const [empName, setEmpName] = useState('');
  const [empDesg, setEmpDesg] = useState('');
  const [empDept, setEmpDept] = useState('');
  const [empMail, setEmpMail] = useState('');
  const [empBranch, setEmpBranch] = useState('');
  const [selectedRoles, setSelectedRoles] = useState([]); // Store roles selected for movement
  const [availableRoles, setAvailableRoles] = useState([]); // Store available roles
  const [assignedRoles, setAssignedRoles] = useState([]); // Store assigned roles in the right list
  const [sourceFilter, setSourceFilter] = useState('');
  const [targetFilter, setTargetFilter] = useState('');

  // Load roles and set available and assigned roles for the user if in edit mode
  useEffect(() => {
    // Load all roles first
    axios.get(`${BASE_URL_P2P}role/allRoles`, {
      headers: {
        Authorization: `Bearer ${jwtToken}`,
        'Content-Type': 'application/json',
      },
    })
    .then(response => {
      const rolesData = response.data.map((role) => ({
        label: role.roleName,
        value: role.id,
      }))
      setAvailableRoles(rolesData); // Set available roles on the left

      // Proceed only if in edit mode and we have a userId
      if (editMode && userId) {
        // Fetch user details for the given userId to populate the form
        axios.get(`${BASE_URL_P2P}role/userDetails/${userId}`, {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
            'Content-Type': 'application/json',
          },
        })
        .then(response => {
          const userData = response.data;
          setEmpId(userData.empId);
          setEmpName(userData.empName);
          setEmpDesg(userData.empDesig);
          setEmpDept(userData.empDept);
          setEmpMail(userData.empMail);
          setEmpBranch(userData.empBranch);

          // Convert empAssignedRole string to array
          const assignedRoleIds = userData.empAssignedRole.split(',').map(id => id.trim());

          // Filter assigned roles from available roles
          const assigned = rolesData.filter(role => assignedRoleIds.includes(role.value.toString()));
          const available = rolesData.filter(role => !assignedRoleIds.includes(role.value.toString()));

          setAssignedRoles(assigned); // Populate assigned roles
          setAvailableRoles(available); // Update available roles
        })
        .catch(error => {
          console.error('Error fetching user details:', error);
          toast.error('Failed to load user details.');
        });
      }
    })
    .catch(error => {
      console.error('Error fetching user details', error);
      toast.error('Failed to load user details');
    })
  }, [editMode, userId, BASE_URL_P2P, jwtToken]);

  const toggleVisibility = (isVisible) => {
    if(!isVisible){
      setEmpId(''); setEmpName(''); setEmpDesg(''); setEmpDept(''); setEmpMail(''); setEmpBranch(''); setSelectedRoles(null); setAvailableRoles(null); setAssignedRoles(null);
    }
    setAddUserRoleVisible(isVisible);
  };

  const getEmployeeDetails = async (empId) => {
    setEmpName(''); setEmpDesg(''); setEmpDept(''); setEmpMail(''); setEmpBranch('');
    try{
      const response = await axios.get(`${BASE_URL_P2P}role/empDetails/${empId}`, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          'Content-Type': 'application/json',
        },
      });
      setEmpName(response.data.userName);
      setEmpDesg(response.data.roleCodeDTO);
      setEmpDept(response.data.departmentNameDTO);
      setEmpMail(response.data.email);
      setEmpBranch(response.data.branchId);
    }catch(error){
      console.error(error.message);
    }
  };

  // Move selected items from availableRoles to assignedRoles
  const moveSelectedToTarget = () => {
    setAssignedRoles((prev) => [...prev, ...selectedRoles.filter((role) => !prev.includes(role)),]);
    setAvailableRoles((prev) => prev.filter((role) => !selectedRoles.includes(role)));
    setSelectedRoles([]);
  };

  // Move all available items to assignedRoles
  const moveAllToTarget = () => {
    setAssignedRoles((prev) => [...prev, ...availableRoles.filter((role) => !prev.includes(role)),]);
    setAvailableRoles([]);
    setSelectedRoles([]);
  };

  // Move selected items from assignedRoles back to availableRoles
  const moveSelectedToSource = () => {
    setAvailableRoles((prev) => [...prev, ...selectedRoles.filter((role) => !prev.includes(role)),]);
    setAssignedRoles((prev) => prev.filter((role) => !selectedRoles.includes(role)));
    setSelectedRoles([]);
  };

  // Move all assigned roles back to availableRoles
  const moveAllToSource = () => {
    setAvailableRoles((prev) => [...prev, ...assignedRoles.filter((role) => !prev.includes(role)),]);
    setAssignedRoles([]);
    setSelectedRoles([]);
  };

  // Handle selection of items without moving them
  const handleSelect = (item, listType) => {
    if(listType === 'available'){
      setSelectedRoles((prev) =>
        prev.includes(item) ? prev.filter((i) => i !== item) : [...prev, item]
      );
    }else{
      setSelectedRoles((prev) =>
        prev.includes(item) ? prev.filter((i) => i !== item) : [...prev, item]
      );
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if(!empId){
      toast.error('Please enter Employee Id');
      empIdRef.current.focus();
      return false;
    }else if(!empName){
      toast.error('Please enter Correct Employee Id');
      empIdRef.current.focus();
      return false;
    }else if(assignedRoles.length === 0){
      toast.error('Please select Role');
      return false;
    }

    const userRoleDto = {
      empId,
      empName,
      empDesig: empDesg,
      empDept,
      empMail,
      empBranch,
      empAssignedRole: assignedRoles.map((role) => role.value).join(','),
      remarks: '',
      createdBy: userRoleCreatedBy,
      createdOn: formattedRoleCreatedOn,
      userRoleDeleteStatus: userRoleDeleteStatus,
      activeStatus: activeStatus
    };

    try{
      setLoading(true);
      if(editMode){
        await axios.put(`${BASE_URL_P2P}role/userRoles/${userId}`, userRoleDto, {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
            'Content-Type': 'application/json',
          },
        });
        toast.success('User Details Updated successfully.');
      }else{
        await axios.post(`${BASE_URL_P2P}role/userRoles`, userRoleDto, {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
            'Content-Type': 'application/json',
          },
        });
        toast.success('User Details added successfully.');
      }
      toggleVisibility(false);
      refreshRolewiseUsers();
    }catch(error){
      if(error.response && error.response.status === 409){
        toast.error('User details already exists.');
      }else{
        toast.error('Something went wrong.');
      }
      console.error('Error====>', error.message);
    }finally{
      setLoading(false);
    }
  };

  return (
    <>
      <CModal size="lg" backdrop className="pt-2" visible={addUserRoleVisible} onClose={() => toggleVisibility(false)} ref={modalRef}>
        <CModalHeader className='d-flex justify-content-center align-items-center' onClose={() => toggleVisibility(false)} style={{ cursor: 'move' }} onMouseMove={handleMouseMove} onMouseUp={handleMouseUp} onMouseLeave={handleMouseUp} onMouseDown={handleMouseDown}>
          <div className="font-semibold text-base">{editMode ? 'Update User Role' : 'Assign User Role'}</div>
        </CModalHeader>
        <CModalBody>
          <CForm onSubmit={handleSubmit}>
            {/* Employee Details Input Section */}
            <CRow>
              <CCol xs={12} md={6} className='p-0 pe-2'>
                <div className="floating-label">
                  <CFormInput type="numeric" size="sm" placeholder=" " onChange={(e) => handleWholeNumberInput(e.target.value, setEmpId)} onInput={(e) => getEmployeeDetails(e.target.value)} ref={empIdRef} value={empId} />
                  <label>Employee Id</label>
                </div>
              </CCol>
              <CCol xs={12} md={6} className='p-0 ps-2'>
                <div className="floating-label">
                  <CFormInput size="sm" placeholder=" " readOnly value={empName} />
                  <label>Employee Name</label>
                </div>
              </CCol>
            </CRow>

            {/* Role Assignment Section */}
            <CRow className="mb-2">
              <CCol xs={12} md={5} className='border border-slate-300'>
                <h5 className="text-center pt-2 pb-2 text-slate-800">Available Roles</h5>
                <div className="floating-label">
                  <CFormInput placeholder=" " value={sourceFilter} onChange={(e) => setSourceFilter(e.target.value)} className="border-slate-300 rounded-none" />
                  <label>Search Role</label>
                </div>
                <div style={{ maxHeight: '200px', overflowY: 'auto' }}>
                  <CListGroup>
                    {availableRoles.filter((item) => item.label.toLowerCase().includes(sourceFilter.toLowerCase())).map((item) => (
                      <CListGroupItem key={item.value} onClick={() => handleSelect(item, 'available')} className={`cursor-pointer border border-slate-300 rounded-none hover:bg-gray-200 ${selectedRoles.includes(item) ? 'bg-secondary-subtle text-black' : ''}`}>
                        {item.label}
                      </CListGroupItem>
                    ))}
                  </CListGroup>
                </div>
              </CCol>

              {/* Buttons */}
              <CCol xs={12} md={2} className="d-flex flex-column justify-content-center align-items-center align-middle">
                <FaAngleDoubleRight color='blue' onClick={moveAllToTarget} className='m-2 cursor-pointer text-2xl' />
                <FaAngleRight color='blue' onClick={moveSelectedToTarget} className='m-2 cursor-pointer text-2xl' />
                <FaAngleLeft color='red' onClick={moveSelectedToSource} className='m-2 cursor-pointer text-2xl' />
                <FaAngleDoubleLeft color='red' onClick={moveAllToSource} className='m-2 cursor-pointer text-2xl' />
              </CCol>

              {/* Assigned Roles Section */}
              <CCol xs={12} md={5} className='border border-slate-300'>
                <h5 className="text-center pt-2 pb-2 text-slate-800">Selected Roles</h5>
                <div className="floating-label">
                  <CFormInput placeholder=" " value={targetFilter} onChange={(e) => setTargetFilter(e.target.value)} className="border-slate-300 rounded-none" />
                  <label>Search Role</label>
                </div>
                <div style={{ maxHeight: '200px', overflowY: 'auto' }}>
                  <CListGroup>
                    {assignedRoles.filter((item) => item.label && item.label.toLowerCase().includes(targetFilter.toLowerCase())).map((item) => (
                      <CListGroupItem key={item.value} onClick={() => handleSelect(item, 'assigned')} className={`cursor-pointer border border-slate-300 rounded-none hover:bg-gray-200 ${selectedRoles.includes(item) ? 'bg-secondary-subtle text-black' : ''}`}>
                        {item.label}
                      </CListGroupItem>
                    ))}
                  </CListGroup>
                </div>
              </CCol>
            </CRow>
            <CModalFooter>
              <CButton color="primary" type="submit" disabled={loading}>{loading ? (<CSpinner size='sm' />) : (editMode ? 'Update' : 'Assign')}</CButton>
              <CButton color="warning" onClick={() => toggleVisibility(false)}>Cancel</CButton>
            </CModalFooter>
          </CForm>
        </CModalBody>
      </CModal>
    </>
  );
};

export default AssignUserRole;
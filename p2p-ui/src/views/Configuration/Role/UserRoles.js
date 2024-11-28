import { CButton, CCard, CCardBody, CCardTitle, CCol, CRow, CSpinner, CTooltip } from '@coreui/react';
import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { formatDate } from '../../../utils/DateFormatter';
import { toast, ToastContainer } from 'react-toastify';
import AssignUserRole from './AssignUserRole';
import { FaUserPlus } from 'react-icons/fa';
import Table from '../../SupportPages/Table';
import DisplayOnHover from '../../SupportPages/DisplayOnHover'
import BackButton from '../../SupportPages/BackButton';
import { getDecryptedSessionItem } from '../../../utils/sessionUtils';

const UserRoles = () => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const downloadName = "Assigned User Roles";
  const bId = getDecryptedSessionItem('branch_id');

  const jwtToken = getDecryptedSessionItem('token');

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [addUserRoleVisible, setAddUserRoleVisible] = useState(false);
  const [editMode, setEditMode] = useState(false);
  const [selectedUser, setSelectedUser] = useState(null);
  const [userRoles, setUserRoles] = useState([]);

  const handleUserRoleClick = () => {
    setEditMode(false);
    setSelectedUser(null);
    setAddUserRoleVisible(true);
  }

  const fetchRolewiseUsers = async () => {
    try{
      const response = await axios.get(`${BASE_URL_P2P}role/userRoles/${bId}`, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          'Content-Type': 'application/json',
        }
      });
      const formattedData = response.data.map(userRoles1 => ({
        ...userRoles1,
        createdOn: formatDate(userRoles1.createdOn),
      }));
      setUserRoles(formattedData); // This sets the user roles correctly
    }catch(error){
      setError(error);
    }finally{
      setLoading(false);
    }
  }

  const columns = [
    { 
      key: 'id',
      label: 'Sl No.',
      render: (_, __, index) => index + 1, //Generate serial number based on the index
    },
    { key: 'empId', label: 'Emp-Id' },
    { key: 'empName', label: 'Emp-Name'},
    { 
      key: 'empAssignedRole',
      label: 'Emp-Role',
      render: (item) => <DisplayOnHover values={item.empAssignedRole} />,
    },
    { key: 'createdBy', label: 'Created By'},
    { key: 'createdOn', label: 'Created On'},
  ]

  useEffect(() => {
    setLoading(true);
    fetchRolewiseUsers();
  }, []);

  const handleUpdate = (userId) => {
    setEditMode(true);
    setSelectedUser(userId.id);
    setAddUserRoleVisible(true);
  };

  const handleStatus = async (userId) => {
    try{
      await axios.put(`${BASE_URL_P2P}role/enableUser/${userId.id}`, {}, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          'Content-Type': 'application/json',
        }
      });
      setUserRoles(prevRoles => 
        prevRoles.map(usr => 
          usr.id === userId.id ? { ...usr, activeStatus: usr.activeStatus === 1 ? 0 : 1 } : usr
        )
      )
    }catch(error){
      console.error('Error: '+error.response);
      toast.warning('Something went wrong.')
    }
  };

  const handleDelete = async (userId) => {
    if(window.confirm(`Are you sure you want to delete the User: ${userId.empName}?`)){
      try{
        await axios.put(`${BASE_URL_P2P}role/deleteUser/${userId.id}`, {}, {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
            'Content-Type': 'application/json',
          }
        });
        toast.warning('User Details deleted successfully.');
        fetchRolewiseUsers();
      }catch(error){
        console.error('Error: '+error.response);
        toast.error('Something went wrong.')
        fetchRolewiseUsers();
      }
    }
  };

  const actions = [
    { label: 'Update', onClick: handleUpdate },
    { label: 'Delete', onClick: handleDelete },
    { label: 'Enable', onClick: handleStatus },
  ];

  if (loading) return <div className="pt-3 text-center"><CSpinner color="primary" variant="grow" /></div>;
  if (error) return <div className="pt-3 text-center">Error loading Users: {error.message}</div>;

  return (
    <>
      <ToastContainer position='top-right' autoClose={3000} />

      <CCard>
        <CCardTitle>
          <CRow className='d-flex justify-content-between align-items-center'>
            <CCol>Users</CCol>
            <CCol className='d-flex gap-2 justify-content-end'>
              <BackButton routePath={'/masters'} />
              <CTooltip content="Assign Role" placement="bottom">
                <CButton color='success' shape="rounded-pill" onClick={handleUserRoleClick}><FaUserPlus /></CButton>
              </CTooltip>
            </CCol>
          </CRow>
        </CCardTitle>

        <CCardBody>
          <Table data={userRoles} columns={columns} actions={actions} downloadName={downloadName} />
        </CCardBody>
      </CCard>

      { addUserRoleVisible && <AssignUserRole addUserRoleVisible={addUserRoleVisible} setAddUserRoleVisible={setAddUserRoleVisible} refreshRolewiseUsers={fetchRolewiseUsers} editMode={editMode} userId={selectedUser} />}
    </>
  )
}

export default UserRoles;
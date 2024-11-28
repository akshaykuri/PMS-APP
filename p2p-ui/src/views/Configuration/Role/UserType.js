import React, { useState, useEffect } from 'react';
import { CButton, CCard, CCardBody, CCardTitle, CCol, CRow, CSpinner, CTooltip } from '@coreui/react';
import { useNavigate } from 'react-router-dom';
import { FaPlus, FaUserCheck, FaUserPlus } from 'react-icons/fa';
import Table from '../../SupportPages/Table';
import BackButton from '../../SupportPages/BackButton'
import AddRole from './AddRole';
import axios from 'axios';
import { formatDate } from '../../../utils/DateFormatter';
import { toast, ToastContainer } from 'react-toastify';
import AssignUserRole from './AssignUserRole';
import { getDecryptedSessionItem } from '../../../utils/sessionUtils';

const UserType = () => {

  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const downloadName = "Role Master";

  const navigate = useNavigate();

  const [addVisible, setAddVisible] = useState(false);
  const [addUserRoleVisible, setAddUserRoleVisible] = useState(false);
  const [loading, setLoading] = useState(false);//for loading
  const [error, setError] = useState(null);//for storing errors
  const [userTypes, setUserTypes] = useState([]);//for storing usertypes/roles

  const jwtToken = getDecryptedSessionItem('token');

  const handleViewDetailsClick = () => {
    navigate("/masters/userRole");
  }

  const handleAddClick = () => {
    setAddVisible(true);
  }

  const handleUserRoleClick = () => {
    setAddUserRoleVisible(true);
  }

  const fetchUserTypes = async () => {
    try{
      const response = await axios.get(`${BASE_URL_P2P}role/roles`, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          'Content-Type': 'application/json',
        }
      });

      // Assuming response.data contains an array of roles and each role has a roleCreatedOn field
      const formattedData = response.data.map(role => ({
        ...role,
        roleCreatedOn: formatDate(role.roleCreatedOn),
      }));

      setUserTypes(formattedData);//feed the state with fetched data
    }catch(error){
      setError(error);
    }finally{
      setLoading(false);
    }
  };

  useEffect(() => {
    setLoading(true);
    fetchUserTypes();//call function
  }, []);

  const columns = [
    { 
      key: 'id',
      label: 'Sl No.',
      render: (_, __, index) => index + 1, //Generate serial number based on the index
    },
    { key: 'roleName', label: 'Role' },
    { key: 'roleDescription', label: 'Description' },
    { key: 'roleCreatedByName', label: 'Created By' },
    { key: 'roleCreatedOn', label: 'Created On' },
  ];

  const handleDelete = async (userType) => {
    if(window.confirm(`Are you sure you want to delete the role: ${userType.roleName}?`)){
      try{
        await axios.put(`${BASE_URL_P2P}role/delete/${userType.id}`, {}, {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
            'Content-Type': 'application/json',
          }
        });
        toast.warning('Role deleted Successfully.');
        fetchUserTypes();
      }catch(error){
        console.error('Error:', error.response); // Log the error response
        toast.error('Something went Wrong.');
        fetchUserTypes();
      }
    }
  };

  const actions = [
    { label: 'Delete', onClick: handleDelete },
  ];

  if(loading) return <div className="pt-3 text-center"><CSpinner color="primary" variant="grow" /></div>;//loader
  if(error) return <div className="pt-3 text-center">Error loading Roles: {error.message}</div>;//error message

  return (
    <>
      <ToastContainer position='top-right' autoClose={3000} />

      <CCard>
        <CCardTitle>
          <CRow className='d-flex justify-content-between align-items-center'>
            <CCol>Roles</CCol>
            <CCol className='d-flex gap-2 justify-content-end'>
              <BackButton routePath={'/masters'} />
              <CTooltip content="Add Role" placement="bottom">
                <CButton color='success' shape='rounded-pill' onClick={handleAddClick}><FaPlus /></CButton>
              </CTooltip>
              <CTooltip content="View Users" placement="bottom">
                <CButton color='info' shape='rounded-pill' onClick={handleViewDetailsClick}><FaUserCheck /></CButton>
              </CTooltip>
              <CTooltip content="Assign User Role" placement="bottom">
                <CButton color='secondary' shape='rounded-pill' onClick={handleUserRoleClick}><FaUserPlus /></CButton>
              </CTooltip>
            </CCol>
          </CRow>
        </CCardTitle>

        <CCardBody>
            <Table data={userTypes} columns={columns} actions={actions} downloadName={downloadName} />
        </CCardBody>
      </CCard>

      {addVisible && <AddRole addVisible={addVisible} setAddVisible={setAddVisible} refreshUserTypes={fetchUserTypes} />}
      {addUserRoleVisible && <AssignUserRole addUserRoleVisible={addUserRoleVisible} setAddUserRoleVisible={setAddUserRoleVisible} refreshUserTypes={fetchUserTypes} />}
    </>
  );
};

export default UserType;
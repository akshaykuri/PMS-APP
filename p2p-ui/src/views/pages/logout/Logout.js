import { CButton, CModal, CModalBody, CModalHeader } from '@coreui/react';
import React from 'react'
import { useNavigate } from 'react-router-dom';

const Logout = ({ logout, setLogout }) => {
  const toggleVisibility = (logout) => {
    setLogout(logout);
  };

  const navigate = useNavigate();
  const handleLogout = () => {
    setLogout(false);
    sessionStorage.clear(); //remove all session values
    navigate('/login'); // Redirect to login
  }

  return (
    // backdrop='static'
    <CModal visible={logout} onClose={() => toggleVisibility(false)}>
      <CModalHeader onClose={() => toggleVisibility(false)}>
          <div className="font-semibold text-base">Are you sure to Logout?</div>
      </CModalHeader>
      <CModalBody>
          <div className="flex justify-end gap-2 w-full">
              <CButton color="primary" onClick={() => toggleVisibility(false)}>No</CButton>
              <CButton color="danger text-white" onClick={handleLogout}>Yes</CButton>
          </div>
      </CModalBody>
    </CModal>
  )
}

export default Logout
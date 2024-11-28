import React from 'react';
import { useNavigate } from 'react-router-dom';
import { CButton, CTooltip } from '@coreui/react';
import { FaBackspace } from 'react-icons/fa';

const BackButton = ({routePath}) => {
  const navigate = useNavigate();

  const handleBackClick = () => {
    navigate(routePath); // Navigate to previous path/route
  };

  return (
    <CTooltip content="Back" placement="bottom">
      <CButton color='primary' shape="rounded-pill" onClick={handleBackClick}>
        <FaBackspace />
      </CButton>
    </CTooltip>
  );
};

export default BackButton;
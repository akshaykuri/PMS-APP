import React, { useEffect, useState } from 'react'
import { validateToken } from './validateToken';
import { Navigate, useLocation } from 'react-router-dom';
import { CSpinner } from '@coreui/react';

const ProtectedRoute = ({ element: Component }) => {
  const [isValid, setIsValid] = useState(null);
  const location = useLocation(); // Access the current location

  useEffect(() => {
    const checkToken = async () => {
      const result = await validateToken();
      setIsValid(result);
      if(!result){
        alert("Session Expired, Please Login Again!!");
        sessionStorage.clear(); // remove all session values
      }
    };
    checkToken();
  }, [location]); // Trigger validation on location change

  if(isValid === null){
    // Show a loading spinner while checking token validity
    return (
      <div className='pt-3 text-center'>
        <CSpinner color='primary' />
      </div>
    )
  }

  return isValid ? <Component /> : <Navigate to='/login' replace />;
};

export default ProtectedRoute
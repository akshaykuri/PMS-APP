import React, { useEffect } from 'react'
import { useLocation, useNavigate } from 'react-router-dom';

const DisableBackButton  = () => {
  const location = useLocation();
  const navigate  = useNavigate();

  useEffect(() => {
    // Push the current path into history to create a new entry
    navigate(location.pathname);
    const handlePopState = () => {
      // When the back button is pressed, push the current path again
      navigate(location.pathname)
    };
    // Add event listener for popstate
    window.addEventListener('popstate', handlePopState);
    // Clean up the event listener on unmount
    return () => {
      window.removeEventListener('popstate', handlePopState);
    };
  }, [location.pathname, navigate])

  return null;
}

export default DisableBackButton
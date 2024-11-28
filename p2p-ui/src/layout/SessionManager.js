import { useEffect } from 'react'
import { useLocation } from 'react-router-dom';
import { setEncryptedSessionItem } from '../utils/sessionUtils';

const SessionManager = () => {
  const location = useLocation(); // Access the current location

  const SESSION_TIMEOUT = 15 * 60 * 1000;//for 15 minutes
  let timer;

  const resetSessionTimer = () => {
    if(location.pathname !== '/login' && location.pathname !== '/'){
      clearTimeout(timer);
      timer = setTimeout(() => {
        sessionStorage.clear();// Clear sessionStorage
      }, SESSION_TIMEOUT);
      // Update session expiry time in session storage
      setEncryptedSessionItem("app_session_expiry", Date.now() + SESSION_TIMEOUT);
    }
  };

  useEffect(() => {
    if(location.pathname !== '/login' && location.pathname !== '/'){
      const events = ['mousemove', 'keydown', 'click', 'scroll'];
      // Add event listeners
      events.forEach(event => window.addEventListener(event, resetSessionTimer));
      // Initialize session timer
      resetSessionTimer();
      //Cleanup on unmount
      return () => {
        events.forEach(event => window.removeEventListener(event, resetSessionTimer));
        clearTimeout(timer);
      };
    }
  }, [location]);// Rerun effect when the location changes

  return null;// This component does not render anything
}

export default SessionManager
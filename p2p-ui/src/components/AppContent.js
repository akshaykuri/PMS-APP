import React, { Suspense, useEffect, useState } from 'react'
import { Navigate, Route, Routes } from 'react-router-dom'
import { CButton, CContainer, CSpinner } from '@coreui/react'
// routes config
import routes from '../routes'
import ProtectedRoute from './../layout/ProtectedRoute'
import { FaAngleDoubleUp } from 'react-icons/fa'

const AppContent = () => {

  const [isVisible, setIsVisible] = useState(false);

  const toggleVisibility = () => {
    if(window.scrollY > 50){
      setIsVisible(true);
    }else{
      setIsVisible(false);
    }
  };

  const scrollToTop = () => {
    window.scrollTo({
      top: 0,
      behavior: 'smooth'
    });
  };

  useEffect(() => {
    window.addEventListener('scroll', toggleVisibility);
    return () => {
      window.removeEventListener('scroll', toggleVisibility);
    };
  }, []);

  return (
    <CContainer className="text-slate-950 px-2.5" fluid>
      <Suspense fallback={<CSpinner color="primary" />}>
        <Routes>
          {routes.map((route, idx) => {
            const Element = route.element;
            return (
              route.element && ( <Route key={idx} path={route.path} exact={route.exact} name={route.name} element={ route.protected ? ( <ProtectedRoute element={Element} /> ) : ( <Element /> ) } /> )
            )
          })}
          <Route path="/" element={<Navigate to="login" replace />} />
        </Routes>
      </Suspense>

      {isVisible && (
        <CButton onClick={scrollToTop} className='position-fixed bottom-24 end-3 opacity-30 hover:opacity-100' style={{zIndex: 1000}} color='primary'><FaAngleDoubleUp /></CButton>
      )}
    </CContainer>
  )
}

export default React.memo(AppContent)
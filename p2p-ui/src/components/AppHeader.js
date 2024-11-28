import React, { useEffect, useRef, useState } from 'react'
import { useSelector, useDispatch } from 'react-redux'
import { CCardTitle, CContainer, CDropdown, CDropdownItem, CDropdownMenu, CDropdownToggle, CHeader, CHeaderNav, CHeaderToggler, useColorModes } from '@coreui/react'
import CIcon from '@coreui/icons-react'
import { cilMenu } from '@coreui/icons'
import { AppHeaderDropdown } from './header/index'
import { getDecryptedSessionItem } from '../utils/sessionUtils'

const AppHeader = () => {
  const headerRef = useRef();
  const { colorMode, setColorMode } = useColorModes('coreui-free-react-admin-template-theme');

  const [isScrolled, setIsScrolled] = useState(false);

  const user_name       = getDecryptedSessionItem('user_name');
  const user_details    = `${getDecryptedSessionItem('user_name')} - ${getDecryptedSessionItem('emp_id')}`;
  const branch_details  = `${getDecryptedSessionItem('branch_name')} (${getDecryptedSessionItem('branch_type_code')})`;
  const department      = getDecryptedSessionItem('department_name');
  const emailID         = getDecryptedSessionItem('email');
  const designation     = getDecryptedSessionItem('role_code');

  const dispatch = useDispatch();
  const sidebarShow = useSelector((state) => state.sidebarShow);

  useEffect(() => {
    const handleScroll = () => {
      setIsScrolled(document.documentElement.scrollTop > 0);
    };

    document.addEventListener('scroll', handleScroll);
    return () => {
      document.removeEventListener('scroll', handleScroll);
    };
  }, []);

  return (
    <CHeader position="sticky" className={`p-0 ${isScrolled ? 'shadow-sm' : ''}`} ref={headerRef} style={{borderTop: isScrolled ? '2px solid #062799' : 'none', transition: 'border-top 0.3s ease-in-out'}}>
      <CContainer className="h-11" style={{padding: '0px 10px'}} fluid>
        <CHeaderToggler onClick={() => dispatch({ type: 'set', sidebarShow: !sidebarShow })} style={{ marginInlineStart: '-14px' }} >
          <CIcon icon={cilMenu} size="lg" />
        </CHeaderToggler>
        {/* <CCardTitle className='cursor-none' style={{ marginInlineStart: '-14px' }}>NEIN-PMS</CCardTitle> */}
        <CHeaderNav>
          <CDropdown variant="nav-item" placement="bottom-end">
            <CDropdownToggle caret={true} className="d-flex align-items-center">
              <span className='font-semibold text-1xl me-1'>
                <svg fill="#1e3a8a" viewBox="0 0 24 24" width="24" height="24">
                  <path d="M12 12c2.2 0 4 1.8 4 4s-1.8 4-4 4-4-1.8-4-4 1.8-4 4-4zm0-10c-3.3 0-6 2.7-6 6 0 2.3 1.3 4.3 3.2 5.3C6.6 16.4 4 19.5 4 23h16c0-3.5-2.6-6.6-5.2-7.7C16.7 12.3 18 10.3 18 8c0-3.3-2.7-6-6-6z"/>
                </svg>
              </span>
              <span className='font-semibold text-1xl'>{user_name}</span>
            </CDropdownToggle>
            <CDropdownMenu>
              <CDropdownItem as='button' type='button'><span className='font-semibold'>Name</span>: {user_details}</CDropdownItem>
              <CDropdownItem as='button' type='button'><span className='font-semibold'>Branch</span>: {branch_details}</CDropdownItem>
              <CDropdownItem as='button' type='button'><span className='font-semibold'>Department</span>: {department}</CDropdownItem>
              <CDropdownItem as='button' type='button'><span className='font-semibold'>Email</span>: {emailID}</CDropdownItem>
              <CDropdownItem as='button' type='button'><span className='font-semibold'>Designation</span>: {designation}</CDropdownItem>
            </CDropdownMenu>
          </CDropdown>
          <li className="nav-item py-1">
            <div className="vr h-100 mx-2 text-body text-opacity-75"></div>
          </li>
          <AppHeaderDropdown />
        </CHeaderNav>
      </CContainer>
    </CHeader>
  )
}

export default AppHeader
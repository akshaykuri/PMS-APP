import React, { useEffect, useState } from 'react'
import { useSelector, useDispatch } from 'react-redux'
import { CSidebar, CSidebarBrand, CSidebarFooter, CSidebarHeader, CSidebarToggler } from '@coreui/react'
import { AppSidebarNav } from './AppSidebarNav'
import RectangularLogo from 'src/assets/images/avatars/RectangularLogo.png'
import SphericalLogo from 'src/assets/images/avatars/SphericalLogo.png'
// sidebar nav config
import navigation from '../_nav'

const AppSidebar = () => {
  const dispatch = useDispatch()
  const unfoldable = useSelector((state) => state.sidebarUnfoldable)
  const sidebarShow = useSelector((state) => state.sidebarShow)

  const [isScrolled, setIsScrolled] = useState(false);

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
    <CSidebar colorScheme='light' position="fixed" unfoldable={!unfoldable} visible={sidebarShow} onVisibleChange={(visible) => { dispatch({ type: 'set', sidebarShow: visible }) }}>
      <CSidebarHeader className="bg-white h-11" style={{borderTop: isScrolled ? '2px solid #062799' : 'none', transition: 'border-top 0.3s ease-in-out'}}>
        <CSidebarBrand className='flex justify-content-center align-items-center flex-col'>
          <img className='sidebar-brand-full w-full h-10 max-h-10 object-contain' src={RectangularLogo} />
          <img className='sidebar-brand-narrow w-full h-auto max-h-10 object-contain' src={SphericalLogo} />
        </CSidebarBrand>
      </CSidebarHeader>
      <AppSidebarNav items={navigation} />
      <CSidebarFooter className="d-none h-11 d-lg-flex">
        <CSidebarToggler onClick={() => dispatch({ type: 'set', sidebarUnfoldable: !unfoldable })} />
      </CSidebarFooter>
    </CSidebar>
  )
}

export default React.memo(AppSidebar)
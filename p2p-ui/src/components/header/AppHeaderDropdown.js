import React, { useState } from 'react'
import { CAvatar, CDropdown, CDropdownToggle } from '@coreui/react'
import Logout from '../../views/pages/logout/Logout'
import { FaPowerOff } from 'react-icons/fa6'

const AppHeaderDropdown = () => {
  const [logout, setLogout] = useState(false)

  const handleLogoutClick = () => {
    setLogout(true);
  };

  return (
    <>
      <CDropdown variant="nav-item">
        <CDropdownToggle placement="bottom-end" className="py-0 ps-0 pe-0" caret={false}>
          <CAvatar onClick={handleLogoutClick} size="md"><FaPowerOff /></CAvatar>
        </CDropdownToggle>
      </CDropdown>

      <Logout logout={logout} setLogout={setLogout} />
    </>
  )
}

export default AppHeaderDropdown
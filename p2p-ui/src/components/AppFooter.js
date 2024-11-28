import React from 'react'
import { CFooter } from '@coreui/react'

const AppFooter = () => {
  return (
    <CFooter className="px-2">
      <div className="ms-auto">
        <span className="me-1 text-slate-500">Copyright &copy; NIPPON EXPRESS (INDIA) PRIVATE. LIMITED, All rights reserved.</span>
      </div>
    </CFooter>
  )
}

export default React.memo(AppFooter)
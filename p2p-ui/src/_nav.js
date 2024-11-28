import React from 'react'
import CIcon from '@coreui/icons-react'
import { cilCalculator, cilCart, cilCloudDownload, cilDollar, cilNotes, cilSettings, cilSpeedometer } from '@coreui/icons'
import { CNavGroup, CNavItem } from '@coreui/react'

const _nav = [
  {
    component: CNavItem,
    name: 'Dashboard',
    to: '/dashboard',
    icon: <CIcon icon={cilSpeedometer} customClassName="nav-icon" />,
  },
  {
    component: CNavGroup,
    name: 'MR Plan',
    to: '/mrPlan',
    icon: <CIcon icon={cilNotes} customClassName="nav-icon" />,
    items: [
      {
        component: CNavItem,
        name: 'MR Plan Summary',
        to: '/mrPlan',
      },
    ],
  },
  {
    component: CNavGroup,
    name: 'Material Requisition',
    to: '/mrRequest',
    icon: <CIcon icon={cilCart} customClassName="nav-icon" />,
    items: [
      {
        component: CNavItem,
        name: 'PR Summary',
        to: '/mrRequest',
      },
    ],
  },
  {
    component: CNavGroup,
    name: 'Purchase Order',
    to: '/poRequest',
    icon: <CIcon icon={cilDollar} customClassName="nav-icon" />,
    items: [
      {
        component: CNavItem,
        name: 'PO Summary',
        to: '/poRequest',
      },
    ],
  },
  {
    component: CNavGroup,
    name: 'Invoice Submission',
    to: '/invSubmission',
    icon: <CIcon icon={cilCalculator} customClassName="nav-icon" />,
    items: [
      {
        component: CNavItem,
        name: 'Invoice Summary',
        to: '/invSubmission',
      },
    ],
  },
  {    
    component: CNavGroup,
    name: 'Inwarding',
    to: '/inwarding',
    icon: <CIcon icon={cilCloudDownload} customClassName="nav-icon" />,
    items: [
      {
        component: CNavItem,
        name: 'Inwarding Summary',
        to: '/inwarding',
      },
    ],
  },
  {
    component: CNavItem,
    name: 'Masters',
    to: '/masters',
    icon: <CIcon icon={cilSettings} customClassName="nav-icon" />,
  }
]

export default _nav
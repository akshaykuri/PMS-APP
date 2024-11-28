import React from 'react'

// Dashboard screen route import
const Dashboard                   = React.lazy(() => import('./views/Dashboard/Dashboard'));

// MR Plan screen route import
const MRPRequest                  = React.lazy(() => import('./views/MRP_Request/MrpRequest'));
const MRPNewRequest               = React.lazy(() => import('./views/MRP_Request/AddMRPlan/AddMRPlanReq'));
const MRPlanDetails               = React.lazy(() => import('./views/MRP_Request/AddMRPlan/MRPlanDetails'));
const SavedMRPRequest             = React.lazy(() => import('./views/MRP_Request/SavedMRPlan/SavedMRPlanReq'));

// PR screen route import
const MRRequest                   = React.lazy(() => import('./views/MR_Request/MrRequest'));

// PO screen route import
const PORequest                   = React.lazy(() => import('./views/PO_Request/PoRequest'));

// GRN screen route import
const InvoiceSubmission           = React.lazy(() => import('./views/Inv_Submission/InvoiceSubmission'));

// Inwarding screen route import
const InwardItems                 = React.lazy(() => import('./views/Inwarding/InwardItems'));

// Masters screen route imports
const Masters                     = React.lazy(() => import('./views/Configuration/Masters'))
const UserType                    = React.lazy(() => import('./views/Configuration/Role/UserType'));
const UserRoles                   = React.lazy(() => import('./views/Configuration/Role/UserRoles'));
const Role                        = React.lazy(() => import('./views/Configuration/Role/RoleManagement'));
const Division                    = React.lazy(() => import('./views/Configuration/Product/Division/Division'));
const Category                    = React.lazy(() => import('./views/Configuration/Product/Category/Category'));
const SubCategory                 = React.lazy(() => import('./views/Configuration/Product/SubCategory/SubCategory'));
const UOM                         = React.lazy(() => import('./views/Configuration/Product/UOM/UOM'));
const HSN                         = React.lazy(() => import('./views/Configuration/Product/HSN_SAC/HSN_SAC'));
const Product                     = React.lazy(() => import('./views/Configuration/Product/Product/Product'));
const DeptHead                    = React.lazy(() => import('./views/Configuration/Approval/DeptHead/DeptHead'));
const Approval                    = React.lazy(() => import('./views/Configuration/Approval/Approval/Approval'));

const routes = [

// Dashboard screen route import
  { path: '/dashboard', element: Dashboard, protected: true },

// MR Plan screen route import
  { path: '/mrPlan',name: 'MR Plan', element: MRPRequest, protected: true },
  { path: '/mrPlan/newRequest',name: 'MR Plan Request', element: MRPNewRequest, protected: true },
  { path: '/mrPlan/mrPlanDetails',name: 'MR Plan Details', element: MRPlanDetails, protected: true },
  { path: '/mrPlan/savedRequest/:mrpId',name: 'MR Plan Request', element: SavedMRPRequest, protected: true },

// PR screen route import
  { path: '/mrRequest',name: 'Material Requisition', element: MRRequest, exact: true, protected: true },

// PO screen route import
  { path: '/poRequest',name: 'Purchase Order', element: PORequest, exact: true, protected: true },

// GRN screen route import
  { path: '/invSubmission',name: 'Invoice Submission', element: InvoiceSubmission, exact: true, protected: true },

// Inwarding screen route import
  { path: '/inwarding',name: 'Inwarding', element: InwardItems, exact: true, protected: true },

// master screen route imports
  { path: '/masters',name: 'Masters', element: Masters, exact: true, protected: true },
  { path: '/masters/userType',name: 'Role Master', element: UserType, protected: true },
  { path: '/masters/userRole/',name: 'User Roles', element: UserRoles, protected: true },
  { path: '/masters/role',name: 'Role Management', element: Role, protected: true },
  { path: '/masters/division',name: 'Division Master', element: Division, protected: true },
  { path: '/masters/category',name: 'Category Master', element: Category, protected: true },
  { path: '/masters/subCategory',name: 'Sub-Category Master', element: SubCategory, protected: true },
  { path: '/masters/uom',name: 'UOM Master', element: UOM, protected: true },
  { path: '/masters/hsn',name: 'HSN_SAC Master', element: HSN, protected: true },
  { path: '/masters/product',name: 'Product Master', element: Product, protected: true },
  { path: '/masters/deptHead',name: 'Dept. Head Master', element: DeptHead, protected: true },
  { path: '/masters/approval',name: 'Approval Master', element: Approval, protected: true },
];

export default routes;
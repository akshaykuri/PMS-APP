import React, { useState } from "react";
import { CCard, CCardBody, CCardHeader, CCol, CDropdown, CDropdownItem, CDropdownMenu, CDropdownToggle, CRow, CTooltip } from "@coreui/react"
import { FaCheck, FaEllipsisV, FaEye, FaInfoCircle, FaPaperPlane, FaTimes, FaTrashAlt } from "react-icons/fa"
import ViewMRPlanDetails from "./ViewMRPlanDetails";

export const MrpRequestCard = ({ data, handlePlusClick, openRemarksModal, emp_id }) => {
  const [viewVisible, setViewVisible]       = useState(false);
  const [tooltipVisible, setTooltipVisible] = useState(false);

  const handleView = () => {
    setViewVisible(true);
  }

  const toggleTooltip = () => {
    setTooltipVisible((prevState) => !prevState);
  };

  const getStatusClass = (status) => {
    switch (status) {
      case "Initiator":
        return "bg-success";
      case "Pending":
        return "bg-warning";
      case "Approved":
        return "bg-success"; // Use the appropriate status class here
      case "Rejected":
        return "bg-danger";
      case "No Action Required":
        return "bg-info";
      case "Deleted":
        return "bg-danger";
      default:
        return "bg-secondary";
    }
  };

  return (
    <>
      <CCard className='mb-2' style={{position: 'static'}}>
        <CCardHeader className='d-flex flex-wrap align-items-center justify-content-between cheadbg'>
          <div className='d-flex'>
            <b>Reference Number:&nbsp;</b>{data.reqRefNumber} &nbsp;
            <CTooltip content='View MRP Detais' placement='top'>
              <span>
                <FaInfoCircle className='text-white cursor-pointer' onClick={handleView} />
              </span>
            </CTooltip>
          </div>
          <div className='d-flex'>
            <b>Requested By:&nbsp;</b>{data.reqByName}
          </div>
          <div className='d-flex'>
            <b>Requested On:&nbsp;</b>{data.reqOn}
          </div>
          <div className='d-flex'>
            <b>Draft Status:&nbsp;</b><span className={`d-flex ${data.draftStatus === 'Deleted' ? 'text-black fw-bold' : ''}`}>{data.draftStatus}</span>
          </div>
          <div className='d-flex'>
            <b>Req Status:&nbsp;</b><span className={`d-flex ${data.reqStatus === 'Deleted' ? 'text-black fw-bold' : ''}`}>{data.reqStatus}</span>
          </div>
          <div className='d-flex'>
            <CDropdown variant='dropdown' className="cursor-pointer">
              <CDropdownToggle caret={false}>
                <FaEllipsisV className='text-sm text-white' />
              </CDropdownToggle>
              <CDropdownMenu>
                { data.draftStatus === 'Saved' && <CDropdownItem onClick={() => handlePlusClick('Saved', data.id)}><span className="d-flex gap-2"><FaPaperPlane />Submit</span></CDropdownItem>}
                { data.routingDTOs.some(dto => String(dto.recEmpId) === String(emp_id) && dto.status === 'Pending') && data.draftStatus !== 'Saved' && data.reqStatus !== 'Deleted' && (<>
                    <CDropdownItem onClick={() => openRemarksModal('Approve', data.id)}><span className="d-flex gap-2"><FaCheck />Approve</span></CDropdownItem>
                    <CDropdownItem onClick={() => openRemarksModal('Reject', data.id)}><span className="d-flex gap-2"><FaTimes />Reject</span></CDropdownItem>
                  </>)}
                { data.reqStatus !== 'Deleted' && data.reqStatus === 'Pending' && <CDropdownItem onClick={() => openRemarksModal('Delete',data.id)}><span className="d-flex gap-2"><FaTrashAlt />Delete</span></CDropdownItem>}
                <CDropdownItem onClick={() => handleView()}><span className="d-flex gap-2"><FaEye />View / Print</span></CDropdownItem>
              </CDropdownMenu>
            </CDropdown>
          </div>
        </CCardHeader>
        <CCardBody style={{paddingLeft: '0.5rem', paddingTop: '0.125rem', paddingBottom: '0.125rem'}}>
          <CRow>
            <CCol xs={12} md={3}>
              <div className='d-flex'>
                <span className='text-gray-500 text-sm'>MR Plan Number</span>
              </div>
            </CCol>
            <CCol xs={12} md={3}>
              <div className='d-flex'>
                <span className='text-gray-500 text-sm'>Office Location</span>
              </div>
            </CCol>
            <CCol xs={12} md={2}>
              <div className='d-flex'>
                <span className='text-gray-500 text-sm'>Division</span>
              </div>
            </CCol>
            <CCol xs={12} md={2}>
              <div className='d-flex'>
                <span className='d-flex text-gray-500 text-sm'>No. of Items&nbsp;&nbsp;
                  <FaInfoCircle className='text-black cursor-pointer' onClick={toggleTooltip} />
                </span>
              </div>
            </CCol>
            <CCol xs={12} md={2}>

            </CCol>
          </CRow>
          <CRow className='mb-1'>
            <CCol xs={12} md={3}>
              <div className='d-flex'>
                <span className={`text-small ${data.reqNumber ? 'text-black' : 'text-red-500'}`}>{data.reqNumber || 'Not Generated'}</span>
              </div>
            </CCol>
            <CCol xs={12} md={3}>
              <div className='d-flex'>
                <span className='text-black text-small'>{data.offLocName}</span>
              </div>
            </CCol>
            <CCol xs={12} md={2}>
              <div className='d-flex'>
                <span className='text-black text-small'>{data.divName}</span>
              </div>
            </CCol>
            <CCol xs={12} md={2}>
              <div className='d-flex'>
                <span className='text-black text-small'>{data.noOfItems} - Items</span>
              </div>
            </CCol>
            <CCol xs={12} md={2}>
              <div className='d-flex justify-content-center'>
                <span className='oval-shape'>Routing Stats</span>
              </div>
            </CCol>
          </CRow>
          <CRow>
            <CCol xs={12} md={3}>
              <div className='d-flex'>
                <span className='text-gray-500 text-sm'>MR Plan Ref Number</span>
              </div>
            </CCol>
            <CCol xs={12} md={3}>
              <div className='d-flex'>
                <span className='text-gray-500 text-sm'>Department</span>
              </div>
            </CCol>
            <CCol xs={12} md={2}>
              <div className='d-flex'>
                <span className='text-gray-500 text-sm'>Purchase Type</span>
              </div>
            </CCol>
            <CCol xs={12} md={2}>
              <div className='d-flex'>
                <span className='text-gray-500 text-sm'>Total MRP Value</span>
              </div>
            </CCol>
            <CCol xs={12} md={2}>
              <div className='d-flex justify-content-center'>
                {data.routingDTOs.map((routing, index) => {
                  let tooltipContent = routing.status;
                  if (routing.status === "Initiator") {
                    if (data.draftStatus === "Saved") {
                      tooltipContent = "Saved";
                    } else if (data.draftStatus === "Submit") {
                      tooltipContent = "Submitted";
                    }
                  }
                  return(
                    <CTooltip key={index} content={tooltipContent} placement="top">
                      <span key={index} className={`dot ${getStatusClass(routing.status)}`}></span>
                    </CTooltip>
                  );
                })}
              </div>
            </CCol>
          </CRow>
          <CRow className='mb-1'>
            <CCol xs={12} md={3}>
              <div className='d-flex'>
                <span className='text-black text-small'>{data.reqRefNumber}</span>
              </div>
            </CCol>
            <CCol xs={12} md={3}>
              <div className='d-flex'>
                <span className='text-black text-small'>{data.deptName}</span>
              </div>
            </CCol>
            <CCol xs={12} md={2}>
              <div className='d-flex'>
                <span className='text-black text-small'>{data.purType}</span>
              </div>
            </CCol>
            <CCol xs={12} md={2}>
              <div className='d-flex'>
                <span className='text-black text-small'>&#8377; {data.totalMRPValue}</span>
              </div>
            </CCol>
            <CCol xs={12} md={2}>

            </CCol>
          </CRow>
        </CCardBody>
      </CCard>
      {viewVisible && <ViewMRPlanDetails viewVisible={viewVisible} setViewVisible={setViewVisible} mrpId={data.id} /> }
    </>
  )
}
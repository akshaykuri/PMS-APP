import { CButton, CCard, CCardHeader, CCardTitle, CCol, CFormTextarea, CModal, CModalBody, CModalFooter, CModalHeader, CModalTitle, CPagination, CPaginationItem, CRow, CSpinner, CTooltip } from '@coreui/react'
import React, { useEffect, useState } from 'react'
import BackButton from '../SupportPages/BackButton'
import { FaPlus } from 'react-icons/fa';
import { getDecryptedSessionItem } from '../../utils/sessionUtils';
import { toast, ToastContainer } from 'react-toastify';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { formatDate } from '../../utils/DateFormatter';
import { MrpRequestCard } from './MrpRequestCard';

const MrpRequest = () => {
  sessionStorage.removeItem('reqNumber');
  sessionStorage.removeItem('reqRefNumber');

  const navigate = useNavigate();
  const jwtToken = getDecryptedSessionItem('token');
  const emp_id   = getDecryptedSessionItem('emp_id');
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;

  const [remarksModalVisible, setRemarksModalVisible] = useState(false);
  const [remarks, setRemarks] = useState('');
  const [currentAction, setCurrentAction] = useState(null); // To store the action type
  const [currentMrpId, setCurrentMrpId] = useState(null); // To store the MR Plan ID

  const [loading, setLoading] = useState(false);
  const [error, setError]     = useState(false);
  const [mrpReqs, setMrpReqs] = useState([]);
  const itemsPerPage = 20;
  const [currentPage, setCurrentPage] = useState(1);

  const handlePlusClick = (dStatus, mrpId) => {
    if(dStatus === 'New'){
      navigate("/mrPlan/newRequest");
    }else if(dStatus === 'Saved'){
      navigate(`/mrPlan/savedRequest/${mrpId}`);
    }
  };

  const openRemarksModal = (status, mrpId) => {
    setCurrentAction(status);
    setCurrentMrpId(mrpId);
    setRemarksModalVisible(true);
  };

  const submitAction = async () => {
    if (!remarks.trim()) {
      toast.error("Remarks are required.");
      return;
    }

    if(currentAction === 'Delete'){
      if(!confirm('Are you sure deleting the MR Plan Request?')) return;
      setLoading(true);
      await axios.put(`${BASE_URL_P2P}mrPlan/deleteMRP/${currentMrpId}/${emp_id}`, {remarks}, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          "Content-Type": 'application/json',
        }
      })
      .then(response => {
        toast.warning('MR Plan Request deleted successfully.');
        fetchMrpReqs();
      })
      .catch(error => {
        console.error('Error deleting the MR Plan request: ', error);
        toast.error('Failed to delete MR Plan request.');
      })
      .finally(() => {
        setLoading(false);
        setRemarksModalVisible(false);
        setRemarks('');
      })
    }else{
      if(!confirm(`Are you sure to ${currentAction} the MR Plan Request?`)) return;
      setLoading(true);
      await axios.put(`${BASE_URL_P2P}mrPlan/statusMRP/${currentMrpId}/${emp_id}/${currentAction}`, {remarks}, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          "Content-Type": 'application/json',
        }
      })
      .then(response => {
        if(currentAction === 'Approve'){
          toast.success(`MR Plan Request Approved successfully.`);
        }else{
          toast.error(`MR Plan Request Rejected successfully.`);
        }
        fetchMrpReqs();
      })
      .catch(error => {
        console.error(`Error while ${currentAction} the MR Plan request: `, error);
        toast.error(`Failed to ${currentAction} MR Plan request.`);
      })
      .finally(() => {
        setLoading(false);
        setRemarksModalVisible(false);
        setRemarks('');
      })
    }
  }

  const fetchMrpReqs = async () => {
    setLoading(true);
    try{
      const response = await axios.get(`${BASE_URL_P2P}mrPlan/getMrpRequests/${emp_id}`, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          "Content-Type": 'application/json'
        }
      });
      const mrpData = response.data.map(mrps => ({
        ...mrps,
        reqOn: formatDate(mrps.reqOn),
        totalMRPValue: mrps.totalMRPValue.toFixed(2),
      }));
      setMrpReqs(mrpData);
    }catch(error){
      setError(error);
      console.error('Error fetching the MR Plan request details: ', error);
      toast.error('Failed to fetch MR Plan request details.');
    }finally{
      setLoading(false);
    }
  };

  useEffect(() => {
    setLoading(true);
    fetchMrpReqs();
  }, [currentPage]);

  // Calculate pagination
  const totalPages = Math.ceil(mrpReqs.length / itemsPerPage);
  const startIndex = (currentPage - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const currentData = mrpReqs.slice(startIndex, endIndex);

  // Handle page change
  const handlePageChange = (page) => {
    setCurrentPage(page);
  };

  if(loading) return <div className="pt-3 text-center"><CSpinner color="primary" variant="grow" /></div>;//loader
  if(error) return <div className="pt-3 text-center">Error loading Roles: {error.message}</div>;//error message

  return (
    <>
      <ToastContainer position='top-right' autoClose={3000} />

      <CCard>
        <CCardTitle>
          <CRow className='d-flex justify-content-between align-items-center'>
            <CCol>MR Plan</CCol>
            <CCol className='d-flex gap-2 justify-content-end'>
              <BackButton routePath={'/dashboard'} />
              <CTooltip content="New Request" placement="bottom">
                <CButton color='success' shape="rounded-pill" onClick={() => handlePlusClick('New')}><FaPlus /></CButton>
              </CTooltip>
            </CCol>
          </CRow>
        </CCardTitle>
      </CCard>

      {currentData.length > 0 ? (
        <CCard>
          <CCardHeader>
            <CRow>
              <CCol>
                <div className="d-flex justify-content-center align-items-center">
                  <CPagination className='mb-0'>
                    {/* Dynamically render page numbers */}
                    {[...Array(totalPages)].map((_, index) => (
                      <CPaginationItem key={index} active={currentPage === index + 1} onClick={() => handlePageChange(index + 1)}>{index + 1}</CPaginationItem>
                    ))}
                  </CPagination>
                </div>
              </CCol>
            </CRow>
          </CCardHeader>
        </CCard>
      ) : (
        null
      )}

      {currentData.length > 0 ?
      (currentData.map((data, index) => (
        <MrpRequestCard key={index} data={data} handlePlusClick={handlePlusClick} openRemarksModal={openRemarksModal} emp_id={emp_id} />
      ))) : (
        <div className="flex justify-center items-center h-64 bg-gray-100 text-gray-600 text-lg font-semibold rounded-lg shadow-md">
          <p>No MR Plan Requests Submitted</p>
        </div>
      )}

      {remarksModalVisible && (
        <CModal visible={remarksModalVisible} onClose={() => setRemarksModalVisible(false)}>
          <CModalHeader>
            <CModalTitle>Enter Remarks</CModalTitle>
          </CModalHeader>
          <CModalBody>
            <div className="floating-label">
              <CFormTextarea value={remarks} onChange={(e) => setRemarks(e.target.value)} rows={4} placeholder=" " />
              <label>Remarks</label>
            </div>
          </CModalBody>
          <CModalFooter className='d-flex justify-content-end'>
            <CButton color="warning" onClick={() => setRemarksModalVisible(false)}>Cancel</CButton>
            <CButton color="primary" onClick={submitAction}>Submit</CButton>
          </CModalFooter>
        </CModal>
      )}
    </>
  )
}

export default MrpRequest
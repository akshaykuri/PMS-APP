import React, { useEffect, useRef, useState } from 'react';
import { CButton, CCard, CCardBody, CCol, CForm, CFormInput, CFormLabel, CInputGroup, CInputGroupText, CRow, CSpinner } from '@coreui/react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { FaEye, FaEyeSlash, FaUser } from 'react-icons/fa';
import { toast, ToastContainer } from 'react-toastify';
import ProcurementImage from 'src/assets/images/avatars/PMS.png';
import { handleWholeNumberInput } from '../../SupportPages/handleInput';
import { setEncryptedSessionItem } from '../../../utils/sessionUtils';

const Login = () => {
  const BASE_URL_AUTH = import.meta.env.VITE_AUTH_API_URL;

  const [showPassword, setShowPassword] = useState(false);
  const [loading, setLoading] = useState(false);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const usernameRef = useRef('');
  const passwordRef = useRef('');
  const navigate = useNavigate();

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  const validateForm = () => {
    if (!username) {
      toast.error('Please enter Emp Id');
      usernameRef.current.focus();
      return false;
    } else if (!password) {
      toast.error('Please enter Password');
      passwordRef.current.focus();
      return false;
    } else {
      return true;
    }
  };

  useEffect(() => {
    sessionStorage.clear();
  }, []);

  const handleLogin = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;
    setLoading(true);
    try {
      const response = await axios.post(`${BASE_URL_AUTH}login`, {
        empId: username,
        password,
      }, {
        headers: {
          'Content-Type': 'application/json',
        },
      });
      if (response.status !== 200) {
        throw new Error(`Login failed: ${response.status} ${response.statusText}`);
      }
      // Extract token and employeeDetails from the response
      const { token, roles, actStat, empDetails } = response.data;
      // Remove the token and employee details in sessionStorage
      sessionStorage.clear();
      // store encrypted session data
      setEncryptedSessionItem('token', token);
      setEncryptedSessionItem('user_id', empDetails.userId);
      setEncryptedSessionItem('emp_id', empDetails.empId);
      setEncryptedSessionItem('user_name', empDetails.userName);
      setEncryptedSessionItem('email', empDetails.email);
      setEncryptedSessionItem('branch_id', empDetails.branchIdDTO);
      setEncryptedSessionItem('branch_type_code', empDetails.branchTypeCodeDTO);
      setEncryptedSessionItem('branch_name', empDetails.branchNameDTO);
      setEncryptedSessionItem('role_id', empDetails.roleIdDTO);
      setEncryptedSessionItem('role_code', empDetails.roleCodeDTO);
      setEncryptedSessionItem('department_id', empDetails.departmentIdDTO);
      setEncryptedSessionItem('department_name', empDetails.departmentNameDTO);
      setEncryptedSessionItem('user_ro1', empDetails.empManagerIdDTO);
      setEncryptedSessionItem('user_ro2', empDetails.empManager2IdDTO);
      // Redirect to dashboard
      navigate('/dashboard');
    } catch (err) {
      console.log('error during logging====>' + err);
      // Check if the error has a response and display the response body if available
      if (err.response && err.response.data) {
        alert(err.response.data); // Assuming the server sends the error message in the response body
      } else {
        alert('An unexpected error occurred, Please try again later or Contact IT Dept.'); // Fallback alert for unexpected errors
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <ToastContainer position='top-right' autoClose={3000} />
      <div className='p-0 m-0 d-flex justify-content-center align-items-center bg-white vh-100'>
        <CCard className='md-2'>
          <CCardBody>
            <CForm onSubmit={handleLogin}>
              <CRow>
                <CCol xs={12} md={8} className="d-flex justify-content-center align-items-center">
                  <img src={ProcurementImage} alt="Procurement System" style={{ maxWidth: '95%', height: '550px' }} />
                </CCol>
                <CCol xs={12} md={4} className="d-flex justify-content-center align-items-center">
                  <CRow>
                    <CCol xs={9} className='d-grid gap-2'>
                      <CInputGroup className="mb-2 relative">
                        <CFormInput placeholder=" " className='block pb-1.5 pt-3 w-full text-lg text-gray-600 bg-transparent rounded-lg border-1 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 font-bold focus:outline-none focus:ring-0 focus:border-blue-600 peer' id="empId" autoComplete="emp-id" value={username} onChange={(e) => handleWholeNumberInput(e.target.value, setUsername)} ref={usernameRef} />
                        <CFormLabel htmlFor="empId" className="absolute text-sm text-gray-400 dark:text-gray-600 duration-300 transform -translate-y-3 scale-75 top-1 z-10 origin-[0] bg-white dark:bg-gray-900 px-1.5 peer-focus:px-1.5 peer-focus:text-lime-600 peer-focus:dark:text-lime-500 peer-focus:font-bold peer-focus:dark:font-bold font-bold peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-1 peer-focus:scale-75 peer-focus:-translate-y-3 start-1 rtl:peer-focus:translate-x-1/4 rtl:peer-focus:left-auto">Emp Id</CFormLabel>
                        <CInputGroupText>
                          <FaUser />
                        </CInputGroupText>
                      </CInputGroup>
                    </CCol>
                    <CCol xs={9} className='d-grid gap-2'>
                      <CInputGroup className="mb-2 relative">
                        <CFormInput type={showPassword ? "text" : "password"} placeholder=" " className='block pb-1.5 pt-3 w-full text-lg text-gray-600 bg-transparent rounded-lg border-1 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 font-bold focus:outline-none focus:ring-0 focus:border-blue-600 peer' id="password" autoComplete="current-password" value={password} onChange={(e) => setPassword(e.target.value)} ref={passwordRef} />
                        <CFormLabel htmlFor="password" className="absolute text-sm text-gray-400 dark:text-gray-600 duration-300 transform -translate-y-3 scale-75 top-1 z-10 origin-[0] bg-white dark:bg-gray-900 px-1.5 peer-focus:px-1.5 peer-focus:text-lime-600 peer-focus:dark:text-lime-500 peer-focus:font-bold peer-focus:dark:font-bold font-bold peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-1 peer-focus:scale-75 peer-focus:-translate-y-3 start-1 rtl:peer-focus:translate-x-1/4 rtl:peer-focus:left-auto">Password</CFormLabel>
                        <CInputGroupText onClick={togglePasswordVisibility} style={{ cursor: 'pointer' }}>
                          {showPassword ? <FaEye /> : <FaEyeSlash />}
                        </CInputGroupText>
                      </CInputGroup>
                    </CCol>
                    <CCol xs={9} className='d-grid justify-content-center align-items-center'>
                      <CButton type="submit" color="primary fw-bolder" style={{width: '150px'}} shape='rounded-pill' size='lg' disabled={loading}>
                        {loading ? (<CSpinner size='sm' />) : ('Login')}
                      </CButton>
                    </CCol>
                  </CRow>
                </CCol>
              </CRow>
            </CForm>
          </CCardBody>
        </CCard>
      </div>
    </>
  );
}

export default Login;
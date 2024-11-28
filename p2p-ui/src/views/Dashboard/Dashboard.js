import React from 'react';
import { CCard, CCardBody, CCardHeader, CRow, CCol, CWidgetStatsF, CCardTitle } from '@coreui/react';
import { CChartLine, CChartPie } from '@coreui/react-chartjs';
import { FaUser, FaChartLine, FaShoppingCart, FaDollarSign } from 'react-icons/fa';

const Dashboard = () => {

  const handleClick = () => {
    
  };

  return (
    <>
      <CCard>
        <CCardTitle>Dashboard</CCardTitle>
      </CCard>
      <CCard>
        <CCardBody className='bg-gray-100'>
          <CRow>
            <CCol xs={12} sm={6} md={3}>
              <CWidgetStatsF onClick={handleClick} className="cursor-pointer mb-2" color="primary" value="1,024" title="Users" icon={<FaUser />} />
            </CCol>

            <CCol xs={12} sm={6} md={3}>
              <CWidgetStatsF onClick={handleClick} className="cursor-pointer mb-2" color="info" value="5,430" title="Sessions" icon={<FaChartLine />} />
            </CCol>

            <CCol xs={12} sm={6} md={3}>
              <CWidgetStatsF onClick={handleClick} className="cursor-pointer mb-2" color="warning" value="763" title="Orders" icon={<FaShoppingCart />} />
            </CCol>
            
            <CCol xs={12} sm={6} md={3}>
              <CWidgetStatsF onClick={handleClick} className="cursor-pointer mb-2" color="success" value="$25,430" title="Revenue" icon={<FaDollarSign />} />
            </CCol>
          </CRow>

          {/* Chart Section */}
          <CRow>
            <CCol xs={12} lg={6} className="mb-2">
              <CCard className="h-100">
                <CCardHeader>Purchased Over Time</CCardHeader>
                <CCardBody>
                  <CChartLine
                    data={{
                      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
                      datasets: [
                        {
                          label: 'Sales',
                          backgroundColor: 'rgba(0,123,255,0.1)',
                          borderColor: 'rgba(0,123,255,1)',
                          data: [30, 50, 40, 61, 42, 35, 40],
                        },
                      ],
                    }}
                    options={{
                      responsive: true,
                      maintainAspectRatio: false,
                    }}
                  />
                </CCardBody>
              </CCard>
            </CCol>

            <CCol xs={12} lg={6} className="mb-2">
              <CCard className="h-100">
                <CCardHeader>User Growth</CCardHeader>
                <CCardBody>
                  <CChartLine
                    data={{
                      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
                      datasets: [
                        {
                          label: 'Users',
                          backgroundColor: 'rgba(40,167,69,0.1)',
                          borderColor: 'rgba(40,167,69,1)',
                          data: [20, 30, 50, 60, 40, 80, 100],
                        },
                      ],
                    }}
                    options={{
                      responsive: true,
                      maintainAspectRatio: false,
                    }}
                  />
                </CCardBody>
              </CCard>
            </CCol>
          </CRow>

          {/* Pie Chart Section */}
          <CRow>
            <CCol xs={12} lg={6} className="mb-2">
              <CCard className="h-100">
                <CCardHeader>Revenue Breakdown</CCardHeader>
                <CCardBody>
                  <CChartPie
                    data={{
                      labels: ['Product A', 'Product B', 'Product C'],
                      datasets: [
                        {
                          data: [40, 35, 25],
                          backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
                          hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
                        },
                      ],
                    }}
                    options={{
                      responsive: true,
                      maintainAspectRatio: false,
                    }}
                  />
                </CCardBody>
              </CCard>
            </CCol>

            <CCol xs={12} lg={6} className="mb-2">
              <CCard className="h-100">
                <CCardHeader>User Distribution</CCardHeader>
                <CCardBody>
                  <CChartPie
                    data={{
                      labels: ['New Users', 'Returning Users', 'Guest Users'],
                      datasets: [
                        {
                          data: [60, 30, 10],
                          backgroundColor: ['#4BC0C0', '#FF9F40', '#FF6384'],
                          hoverBackgroundColor: ['#4BC0C0', '#FF9F40', '#FF6384'],
                        },
                      ],
                    }}
                    options={{
                      responsive: true,
                      maintainAspectRatio: false,
                    }}
                  />
                </CCardBody>
              </CCard>
            </CCol>
          </CRow>
        </CCardBody>
      </CCard>
    </>
  );
};

export default Dashboard;
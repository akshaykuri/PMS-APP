import React from 'react';
import { CTable, CTableHead, CTableRow, CTableHeaderCell, CTableBody, CTableDataCell, CPagination, CPaginationItem, CFormInput, CFormSelect, CCol, CRow, CTooltip } from '@coreui/react';
import { FaSortUp, FaSortDown, FaFileExcel, FaFileCsv, FaFilePdf, FaClipboardList, FaPencilAlt, FaTrashAlt, FaEye } from 'react-icons/fa'; // Import icons
import useTable from './useTable';
import * as XLSX from 'xlsx';
import { saveAs } from 'file-saver';
import jsPDF from 'jspdf';
import 'jspdf-autotable';
import { toast } from 'react-toastify';

const FilterableTable = ({ data, columns, actions, downloadName }) => {
  const { currentItems, searchTerm, setSearchTerm, itemsPerPage, setItemsPerPage, currentPage, setCurrentPage, totalPages, requestSort, sortConfig } = useTable(data);

  const exportToExcel = () => {
    const ws = XLSX.utils.json_to_sheet(data);
    const wb = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, `${downloadName}`);
    XLSX.writeFile(wb, `${downloadName}.xlsx`);
  };

  const exportToCSV = () => {
    const csvContent = "data:text/csv;charset=utf-8" + data.map(e => Object.values(e).join(",")).join("\n");
    const encodedUri = encodeURI(csvContent);
    saveAs(encodedUri, `${downloadName}.csv`);
  };

  const exportToPDF = () => {
    const doc = new jsPDF();
    doc.autoTable({
      head: [columns.map(col => col.label)],
      body: data.map(item => columns.map(col => item[col.key])),
    });
    doc.save(`${downloadName}.pdf`);
  }

  const copyToClipboard = () => {
    const clipboardData = data.map(item => columns.map(col => item[col.key]).join("\t")).join("\n");
    navigator.clipboard.writeText(clipboardData).then(() => {
      toast.success('Data copied to clipboard!');
    }).catch(err => {
      console.error('Failed to copy data to clipboard: ', err);
    });
  };

  return (
    <div>
      <CRow className="mb-1">
        <CCol className='d-flex align-items-center justify-content-between gap-1'>
          <div className="d-flex align-items-center gap-1">
            <CFormSelect className='max-w-32 text-sm' value={itemsPerPage} size="sm" onChange={(e) => { setItemsPerPage(Number(e.target.value)); setCurrentPage(1); }}>
              <option value={10}>Show 10 Rows</option>
              <option value={25}>Show 25 Rows</option>
              <option value={50}>Show 50 Rows</option>
              <option value={100}>Show 100 Rows</option>
              <option value={data.length}>Show All</option>
            </CFormSelect>

            <CTooltip content="Download Excel" placement="top">
              <span><FaFileExcel onClick={exportToExcel} className='text-xl h-full inline-flex align-items-center cursor-pointer' color='green' /></span>
            </CTooltip>
            <CTooltip content="Download CSV" placement="top">
              <span><FaFileCsv onClick={exportToCSV} className='text-xl h-full inline-flex align-items-center cursor-pointer' color='blue' /></span>
            </CTooltip>
            <CTooltip content="Download PDF" placement="top">
              <span><FaFilePdf onClick={exportToPDF} className='text-xl h-full inline-flex align-items-center cursor-pointer' color='red' /></span>
            </CTooltip>
            <CTooltip content="Copy to ClipBoard" placement="top">
              <span><FaClipboardList onClick={copyToClipboard} className='text-xl h-full inline-flex align-items-center cursor-pointer' color='black' /></span>
            </CTooltip>
          </div>

          <CFormInput className='max-w-52 text-sm float-right' type="text" placeholder="Search" size="sm" value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)} />
        </CCol>
      </CRow>
      <div className='mb-1 position-sticky table-container'>
        <CTable hover borderless responsive align='middle'>
          <CTableHead color='primary'>
            <CTableRow>
              {columns.map(({ key, label }) => (
                <CTableHeaderCell className="text-center cursor-pointer whitespace-nowrap" key={key} onClick={() => requestSort(key)}>
                  <span className='flex items-center gap-2'>{label}{sortConfig?.key === key && (sortConfig.direction === 'ascending' ? <FaSortUp /> : <FaSortDown />)}</span>
                </CTableHeaderCell>
              ))}
              {/* Render actions column only if actions are provided */}
              {actions && actions.length > 0 && <CTableHeaderCell className='whitespace-nowrap'>Actions</CTableHeaderCell>}
            </CTableRow>
          </CTableHead>
          <CTableBody>
            {currentItems.length > 0 ? (
              currentItems.map((item, index) => (
                <CTableRow key={item.id}>
                  {columns.map(({ key, render }) => (
                    <CTableDataCell key={key} className='whitespace-break-spaces'>
                      {render ? render(item, currentItems, index) : item[key]} {/* Pass index to render */}
                    </CTableDataCell>
                  ))}
                  {/* Render actions only if actions are provided */}
                  {actions && actions.length > 0 && (
                    <CTableDataCell className='text-center align-middle'>
                      <div className="d-flex justify-content-center gap-2">
                        {actions.map(({ label, onClick }) => (
                          <CTooltip key={label} content={label}>
                            <span className="me-2 cursor-pointer">
                              {label === 'Update' && <FaPencilAlt onClick={() => onClick(item)} className='text-primary' />}
                              {label === 'Delete' && <FaTrashAlt onClick={() => onClick(item)} className='text-danger' />}
                              {label === 'View' && <FaEye onClick={() => onClick(item)} className='text-black' />}
                            </span>
                          </CTooltip>
                        ))}
                      </div>
                    </CTableDataCell>
                  )}
                </CTableRow>
                ))
            ) : (
              <CTableRow>
                <CTableDataCell colSpan={columns.length + 1} className="text-center">
                  No data available.
                </CTableDataCell>
              </CTableRow>
            )}
          </CTableBody>
        </CTable>
      </div>
      <CRow className="d-flex justify-content-between align-items-center">
        <CCol md={6}>
          {/* Calculate the starting and ending index for the current page */}
          {(() => {
            const start = (currentPage - 1) * itemsPerPage + 1; // Starting index
            const end = Math.min(start + itemsPerPage - 1, data.length); // Ending index
            return(
              <span>
                Showing {start}-{end} of {data.length} entries
              </span>
            );
          })()}
        </CCol>
        <CCol md={6}>
          <CPagination className="float-right cursor-pointer">
            <CPaginationItem color="white" onClick={() => setCurrentPage(1)} disabled={currentPage === 1 || totalPages === 0}>&laquo;</CPaginationItem>
            <CPaginationItem color="white" onClick={() => setCurrentPage(currentPage > 1 ? currentPage - 1 : 1)} disabled={currentPage === 1 || totalPages === 0}>&lsaquo;</CPaginationItem>
            {/* Show all pages if totalPages is less than or equal to 5 */}
            {totalPages <= 5 && (
              [...Array(totalPages)].map((_, index) => (
                <CPaginationItem key={index + 1} active={currentPage === index + 1} onClick={() => setCurrentPage(index + 1)}>{index + 1}</CPaginationItem>
              ))
            )}
            {/* Show only first 2 pages, '...', and last 2 pages if totalPages > 5 */}
            {totalPages > 5 && (
              <>
                {[...Array(2)].map((_, index) => (
                  <CPaginationItem key={index + 1} active={currentPage === index + 1} onClick={() => setCurrentPage(index + 1)}>{index + 1}</CPaginationItem>
                ))}
              {currentPage >= 0 && <CPaginationItem disabled>...</CPaginationItem>}
              {[...Array(2)].map((_, index) => (
                <CPaginationItem key={totalPages - 1 + index} active={currentPage === totalPages - 1 + index} onClick={() => setCurrentPage(totalPages - 1 + index)}>{totalPages - 1 + index}</CPaginationItem>
              ))}
              </>
            )}
            <CPaginationItem color="white" onClick={() => setCurrentPage(currentPage < totalPages ? currentPage + 1 : totalPages)} disabled={currentPage === totalPages || totalPages === 0}>&rsaquo;</CPaginationItem>
            <CPaginationItem color="white" onClick={() => setCurrentPage(totalPages)} disabled={currentPage === totalPages || totalPages === 0}>&raquo;</CPaginationItem>
          </CPagination>
        </CCol>
      </CRow>
    </div>
  );
};

export default FilterableTable;
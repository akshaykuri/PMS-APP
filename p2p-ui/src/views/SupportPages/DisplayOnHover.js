import { CTooltip } from '@coreui/react'
import React from 'react'

const DisplayOnHover = ({ values }) => {
  // Split the values by comma and trim any whitespace
  const valueArray = values.split(',').map(value => value.trim());
  const tooltipContent = valueArray.join(', \n'); // Join values with line breaks for tooltip
  return (
    <>
      {valueArray.length > 1 ? (
        <CTooltip className='justify-items-start align-items-start' color='blue' content={<div style={{ whiteSpace: 'pre-line', textAlign: 'left' }}>{tooltipContent}</div>} placement="right">
          <span style={{ cursor: 'pointer', color: 'blue', textDecoration: 'underline' }}>
            Multiple Datas
          </span>
        </CTooltip>
      ) : (
        <span>{values}</span>
      )}
    </>
  );
};

export default DisplayOnHover
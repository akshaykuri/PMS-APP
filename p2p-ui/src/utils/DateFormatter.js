export const formatDate = (dateString) => {
    const date = new Date(dateString);
    
    // Check for valid date
    if (isNaN(date)) {
      return 'Invalid date';
    }
  
    // Extracting date components
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are zero-indexed
    const year = date.getFullYear();
    
    // Extracting time components
    let hours = date.getHours();
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const ampm = hours >= 12 ? 'PM' : 'AM';
    hours = hours % 12;
    hours = hours ? String(hours).padStart(2, '0') : '12'; // the hour '0' should be '12'
  
    return `${day}-${month}-${year}  ${hours}:${minutes} ${ampm}`;
};

export const formatOnlyDate = (dateString) => {
  const date = new Date(dateString);
  
  // Check for valid date
  if (isNaN(date)) {
    return 'Invalid date';
  }

  // Extracting date components
  const day = String(date.getDate()).padStart(2, '0');
  const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are zero-indexed
  const year = date.getFullYear();

  return `${day}-${month}-${year}`;
};
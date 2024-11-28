import React, { useEffect, useState } from 'react'
import Items_DP from '../../DropDowns/Items_DP';
import { CButton, CCol, CFormInput, CFormTextarea, CHeader, CRow, CTable, CTableBody, CTableDataCell, CTableHead, CTableHeaderCell, CTableRow } from '@coreui/react';
import { handleCalculationTotal, handleDecimalInput, handleSpecialCharacters, handleWholeNumberInput } from '../../SupportPages/handleInput';
import UOMForPR_DP from '../../DropDowns/UOMForPR_DP';
import { toast } from 'react-toastify';
import { FaDivide, FaPencilAlt, FaTrashAlt } from 'react-icons/fa';

const ProductDetails = ({ divId = '', purchaseType = '', itemRef, itemId, setItemId, descRef, desc, setDesc, qtyRef, qty, setQty, uomRef, uomId, setUomId, priceRef, price, setPrice, totRef, total, setTotal, addedProducts, setAddedProducts }) => {
  const [items, setItems]                                 = useState([]);
  const [uoms, setUoms]                                   = useState([]);
  const [editingIndex, setEditingIndex]                   = useState(null);

  useEffect(() => {
    if(itemId !== ''){
      setDesc(itemId?.prodInfo?.prdDetails?.prdDesc);
      if(price === ''){
        setPrice(itemId?.prodInfo?.prdDetails?.prdPrice);
      }
      setUomId(itemId.prodInfo.prdUom);
    };
  }, [itemId, price, uomId]);

  const handleAddProduct = () => {
    if(itemId && desc && qty && price){
      const newProduct = { itemId, desc, qty, uomId, price, total };

      const isDuplicate = addedProducts.some(product => product?.itemId?.value === itemId?.value);
      if(isDuplicate){
        toast.error('This product has already been added.')
        itemRef.current.focus();
        return false;
      }

      if (editingIndex !== null) {
        // Update the product at the editing index
        const updatedProducts = [...addedProducts];
        updatedProducts[editingIndex] = newProduct;
        setAddedProducts(updatedProducts);
        setEditingIndex(null); // Clear editing state
      } else {
        setAddedProducts([...addedProducts, newProduct]);
      }
      setItemId('');
      setDesc('');
      setQty('');
      setUomId('');
      setPrice('');
      setTotal('');
    }else{
      if(!itemId){
        toast.error('Please select Product.');
        itemRef.current.focus();
        return false;
      }else if(!desc || desc === ''){
        toast.error('Please enter Description.'+ desc);
        descRef.current.focus();
        return false;
      }else if(!qty && qty !== 0){
        toast.error('Please enter Quantity.');
        qtyRef.current.focus();
        return false;
      }else if(!price && price !== 0){
        toast.error('Please enter Price.');
        priceRef.current.focus();
        return false;
      }else if(!total && total !== 0){
        toast.error('Please enter Total.');
        totRef.current.focus();
        return false;
      }
    }
  };

  const handleEditProduct = (index) => {
    const productToEdit = addedProducts[index];
    setItemId(productToEdit.itemId);
    setDesc(productToEdit.desc);
    setQty(productToEdit.qty);
    setPrice(productToEdit.price);
    setTotal(productToEdit.total);
    // const updatedProducts = addedProducts.filter((_, i) => i !== index);
    // setAddedProducts(updatedProducts);
    setEditingIndex(index);
  }

  const handleDeleteProduct = (index) => {
    const updatedProducts = addedProducts.filter((_, i) => i !== index);
    setAddedProducts(updatedProducts);
  };

  const grandTotal = addedProducts.reduce((sum, product) => sum + parseFloat(product.total || 0), 0);

  return (
    <>
      <CHeader className="mb-3 fs-4 text-center pb-2">Product Details</CHeader>
      <CRow>
        <CCol xs={12} md={6}>
          <Items_DP divId={divId?.value} purchaseType={purchaseType?.value} itemRef={itemRef} items={items} itemId={itemId} setItems={setItems} setItemId={setItemId} />
        </CCol>
        <CCol xs={12} md={6}>
          <div className="floating-label">
            <CFormTextarea size="sm" placeholder=" " value={desc} onPaste={(e) => handleSpecialCharacters(e.target.value, setDesc)} onChange={(e) => handleSpecialCharacters(e.target.value, setDesc)} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={descRef} />
            <label>Product Description</label>
          </div>
        </CCol>
      </CRow>
      <CRow>
        <CCol xs={12} md={6}>
          <div className="floating-label">
            <CFormInput size="sm" placeholder=" " value={qty} onChange={(e) => { handleWholeNumberInput(e.target.value, setQty); handleCalculationTotal(price, e.target.value, setTotal); }} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={qtyRef} />
            <label>Qty.</label>
          </div>
        </CCol>
        <CCol xs={12} md={6}>
          <UOMForPR_DP uomRef={uomRef} uoms={uoms} uomId={uomId} setUoms={setUoms} setUomId={setUomId} />
        </CCol>        
      </CRow>
      <CRow>
        <CCol xs={12} md={6}>
          <div className="floating-label">
            <CFormInput size="sm" placeholder=" " value={price} onChange={(e) => { handleDecimalInput(e.target.value, setPrice); handleCalculationTotal(e.target.value, qty, setTotal); }} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={priceRef} />
            <label>Price</label>
          </div>
        </CCol>
        <CCol xs={12} md={6}>
          <div className="floating-label">
            <CFormInput size="sm" placeholder=" " value={total} onChange={(e) => handleDecimalInput(e.target.value, setTotal)} onFocus={e => e.target.classList.add('focused')} onBlur={e => e.target.value === '' && e.target.classList.remove('focused')} ref={totRef} readOnly={true} />
            <label>Total</label>
          </div>
        </CCol>
      </CRow>
      <CButton color='primary' onClick={handleAddProduct}>Add Product</CButton>

      {/* Table to display added products */}
      <CRow className='mt-3'>
        <CCol xs={12}>
          <CTable hover borderless responsive align='middle'>
            <CTableHead>
              <CTableRow>
                <CTableHeaderCell className='border-r border-r-gray-300'>#</CTableHeaderCell>
                <CTableHeaderCell className='border-r border-r-gray-300'>Product Name</CTableHeaderCell>
                <CTableHeaderCell className='border-r border-r-gray-300'>Description</CTableHeaderCell>
                <CTableHeaderCell className='border-r border-r-gray-300'>Qty</CTableHeaderCell>
                <CTableHeaderCell className='border-r border-r-gray-300'>UOM</CTableHeaderCell>
                <CTableHeaderCell className='border-r border-r-gray-300'>Price</CTableHeaderCell>
                <CTableHeaderCell className='border-r border-r-gray-300'>Total</CTableHeaderCell>
                <CTableHeaderCell></CTableHeaderCell>
              </CTableRow>
            </CTableHead>
            <CTableBody>
              { addedProducts.length > 0 ? (
                addedProducts.map((product, index) => (
                  <CTableRow key={index}>
                    <CTableDataCell className='border-r border-r-gray-300'>{ index+1 }</CTableDataCell>
                    <CTableDataCell className='border-r border-r-gray-300'>{ product?.itemId?.prodInfo?.prdDetails?.prdName }</CTableDataCell>
                    <CTableDataCell className='border-r border-r-gray-300'>{ product.desc }</CTableDataCell>
                    <CTableDataCell className='border-r border-r-gray-300'>{ product.qty }</CTableDataCell>
                    <CTableDataCell className='border-r border-r-gray-300'>{ product.uomId.label }</CTableDataCell>
                    <CTableDataCell className='border-r border-r-gray-300 text-right'>{ (product.price) }</CTableDataCell>
                    <CTableDataCell className='border-r border-r-gray-300 text-right'>{ (product.total) }</CTableDataCell>
                    <CTableDataCell>
                      <span className='flex gap-1 align-items-center justify-content-center'>
                        <FaPencilAlt className='cursor-pointer text-primary' onClick={() => handleEditProduct(index)} />
                        <FaTrashAlt className='cursor-pointer text-danger' onClick={() => handleDeleteProduct(index)} />
                      </span>
                    </CTableDataCell>
                  </CTableRow>
                ))) : (
                  <CTableRow>
                    <CTableDataCell colSpan={8} className='text-center'>
                      No Products Added
                    </CTableDataCell>
                  </CTableRow>
                )
              }
              <CTableRow>
                <CTableDataCell colSpan={6} className='border-r border-r-gray-300 text-right font-semibold text-blue-900'>Grand Total</CTableDataCell>
                <CTableDataCell className='border-r border-r-gray-300 text-right font-semibold text-red-950'>{grandTotal.toFixed(2)}</CTableDataCell>
                <CTableDataCell className='border-b-0'></CTableDataCell>
              </CTableRow>
            </CTableBody>
          </CTable>
        </CCol>
      </CRow>
    </>
  )
}

export default ProductDetails
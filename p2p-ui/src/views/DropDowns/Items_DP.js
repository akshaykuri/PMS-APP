import axios from 'axios';
import React, { useEffect } from 'react'
import Select from 'react-select';
import { toast } from 'react-toastify';
import { getDecryptedSessionItem } from '../../utils/sessionUtils';

const Items_DP = ({ divId = 0, purchaseType = '', itemRef, items, itemId, setItems, setItemId }) => {
  const BASE_URL_P2P = import.meta.env.VITE_P2P_API_URL;
  const jwtToken = getDecryptedSessionItem('token');
  const dId = divId;
  const purType = purchaseType;

  const fetchProducts = async () => {
    if(dId !== '' && purType !== ''){
      axios.get(`${BASE_URL_P2P}company/getProducts/${dId}/${purType}`, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          'Content-Type': 'application/json',
        },
      })
      .then(response => {
        const productsData = response.data.map((prd) => ({
          label: `${prd.productCode}, ${prd.hsnSacName}, ${prd.divisionName}, ${prd.purchaseType}, ${prd.productName}, ${prd.productDesc}, ${prd.categoryName}, ${prd.subCategoryName},`,
          value: prd.id,
          prodInfo: {
            prdDetails: {
              prdCode   : prd.productCode,
              prdName   : prd.productName,
              prdPrice  : (prd.price).toFixed(2),
              prdPur    : prd.purchaseType,
              prdDesc   : prd.productDesc,
              prdManu   : prd.manufacturer,
              prdMod    : prd.model,
            },
            prdHsn    : {
              label: prd.hsnSacName,
              value: prd.hsnsacId,
            },
            prdDiv    : {
              label: prd.divisionName,
              value: prd.prodDivId,
            },
            prdCat    : {
              label: prd.categoryName,
              value: prd.categoryId,
            },
            prdSub    : {
              label: prd.subCategoryName,
              value: prd.subCategoryId
            },
            prdUom    : {
              label: prd.uomName,
              value: prd.uomId,
            },
          },
        }));
        setItems(productsData);
      })
      .catch(error => {
        console.error('Error fetching Products', error);
        toast.error('Failed to load Product details');
      })
    }
  }

  useEffect(() => {
    fetchProducts();
  },[dId, purType]);

  return (
    <div className="floating-label">
      <Select id='Product-select' value={items.find(item => item.value === itemId?.value) || null} onChange={(e) => setItemId(e)} onFocus={e => e.target.classList.add('focused')} onBlur={(e) => !itemId && e.target.classList.remove('focused')} options={items} ref={itemRef} placeholder="" isMulti={false} isSearchable />
      <label htmlFor='Product-select' className={itemId ? 'active' : 'floating'}>Select Product</label>
    </div>
  )
}

export default Items_DP
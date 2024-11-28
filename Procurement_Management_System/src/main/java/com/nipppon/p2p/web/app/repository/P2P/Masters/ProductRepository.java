package com.nipppon.p2p.web.app.repository.P2P.Masters;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nipppon.p2p.web.app.entity.P2P.Masters.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	// Method to find the top product with the specified prefix, ordered by productCode descending
	Product findTopByProdDivIdOrderByProductCodeDesc(Long divId);
	Optional<Product> findByProductNameAndProdDivIdAndPurchaseTypeAndCategoryIdAndSubCategoryIdAndProdDeleteStatus(String prodName, Long divId, String purType, Long catId, Long subCatId, int deleteStatus);
	List<Product> findByProdDivIdAndPurchaseType(Long id, String purType);
}
package com.nipppon.p2p.web.app.repository.P2P.Masters;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nipppon.p2p.web.app.entity.P2P.Masters.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>{
	Optional<SubCategory> findBySubCategoryNameAndCategoryIdAndSubCatDeleteStatus(String subCategoryName,Long categoryId, int deleteStatus);
	List<SubCategory> findByCategoryIdAndSubCatDeleteStatus(Long id, int deleteStatus);
}
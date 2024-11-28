package com.nipppon.p2p.web.app.repository.P2P.Masters;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nipppon.p2p.web.app.entity.P2P.Masters.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	Optional<Category> findByCategoryNameAndCatDeleteStatus(String categoryName, int deleteStatus);
	List<Category> findByPurchaseType(String purType);
}
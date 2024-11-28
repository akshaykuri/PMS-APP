package com.nipppon.p2p.web.app.repository.P2P.MRPlan;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nipppon.p2p.web.app.entity.P2P.MRPlan.MR_Plan_Item;

@Repository
public interface MR_Plan_ItemRepository extends JpaRepository<MR_Plan_Item, Long>{
	Optional<MR_Plan_Item> findByMrplIdAndProductId(Long mrplid, Long productId);
}
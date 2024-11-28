package com.nipppon.p2p.web.app.repository.P2P.MRPlan;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nipppon.p2p.web.app.entity.P2P.MRPlan.MR_Plan_Routing;

@Repository
public interface MR_Plan_RoutingRepository extends JpaRepository<MR_Plan_Routing, Long>{
	List<MR_Plan_Routing> findByMrrIdAndStatus(long id, String status);
	Optional<MR_Plan_Routing> findByMrrIdAndStatusAndRecEmpId(long id, String string, String appId);
	List<MR_Plan_Routing> findByMrrIdAndRecEmpIdAndStatus(long id, String appId, String string);
}
package com.nipppon.p2p.web.app.repository.P2P.Masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nipppon.p2p.web.app.entity.P2P.Masters.PurchaseType;

@Repository
public interface PurchaseTypeRepository extends JpaRepository<PurchaseType, Long>{
	
}
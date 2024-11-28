package com.nipppon.p2p.web.app.repository.P2P.Masters;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nipppon.p2p.web.app.entity.P2P.Masters.HSN_SAC;

@Repository
public interface HSN_SACRepository extends JpaRepository<HSN_SAC, Long>{
	Optional<HSN_SAC> findByHsnCodeAndHsnDeleteStatus(String hsnCode, int deleteStatus);
}
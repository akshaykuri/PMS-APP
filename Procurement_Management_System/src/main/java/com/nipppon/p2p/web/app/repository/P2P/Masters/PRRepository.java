package com.nipppon.p2p.web.app.repository.P2P.Masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nipppon.p2p.web.app.entity.P2P.Masters.PR;

@Repository
public interface PRRepository extends JpaRepository<PR, Long>{

}
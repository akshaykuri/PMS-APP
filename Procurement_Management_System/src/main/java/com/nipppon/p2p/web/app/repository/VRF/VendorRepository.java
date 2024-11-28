package com.nipppon.p2p.web.app.repository.VRF;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nipppon.p2p.web.app.entity.VRF.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer>{

}
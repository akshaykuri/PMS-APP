package com.nipppon.p2p.web.app.repository.CRF;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nipppon.p2p.web.app.entity.CRF.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
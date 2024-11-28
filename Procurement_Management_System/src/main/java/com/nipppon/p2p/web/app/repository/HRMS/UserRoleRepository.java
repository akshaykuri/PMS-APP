package com.nipppon.p2p.web.app.repository.HRMS;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nipppon.p2p.web.app.entity.HRMS.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{

}
package com.nipppon.p2p.web.app.repository.P2P.Masters;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nipppon.p2p.web.app.entity.P2P.Masters.AssignUserRole;

@Repository
public interface AssignUserRoleRepository extends JpaRepository<AssignUserRole, Long>{
	Optional<AssignUserRole> findByEmpIdAndUserRoleDeleteStatus(String empId, int deleteStatus);
}
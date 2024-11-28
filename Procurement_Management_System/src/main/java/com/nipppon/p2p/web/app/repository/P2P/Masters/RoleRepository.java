package com.nipppon.p2p.web.app.repository.P2P.Masters;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nipppon.p2p.web.app.entity.P2P.Masters.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByRoleNameAndRoleDeleteStatus(String roleName, int deleteStatus);
}
package com.nipppon.p2p.web.app.repository.HRMS;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nipppon.p2p.web.app.entity.HRMS.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByEmpId(String empId);
	default List<User> findAllOrderByEmpId(){
		return findAll(Sort.by(Sort.Direction.ASC, "empId"));
	}
}
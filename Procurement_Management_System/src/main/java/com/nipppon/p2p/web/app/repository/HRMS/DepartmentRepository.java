package com.nipppon.p2p.web.app.repository.HRMS;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nipppon.p2p.web.app.entity.HRMS.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	default List<Department> findAllOrderBydepartmentName(){
		return findAll(Sort.by(Sort.Direction.ASC, "departmentName"));
	}
	Optional<Department> findByDepartmentId(Long departId);
}
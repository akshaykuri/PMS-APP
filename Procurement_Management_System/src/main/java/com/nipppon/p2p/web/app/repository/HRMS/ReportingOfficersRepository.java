package com.nipppon.p2p.web.app.repository.HRMS;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nipppon.p2p.web.app.entity.HRMS.ReportingOfficers;

@Repository
public interface ReportingOfficersRepository extends JpaRepository<ReportingOfficers, Integer>{
	Optional<ReportingOfficers> findByUserId(int userId);
}
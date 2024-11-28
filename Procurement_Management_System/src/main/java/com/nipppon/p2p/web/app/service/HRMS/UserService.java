package com.nipppon.p2p.web.app.service.HRMS;

import java.util.Optional;
import com.nipppon.p2p.web.app.dto.HRMS.UserDTO;
import com.nipppon.p2p.web.app.entity.HRMS.User;

public interface UserService{
	Optional<User> findByEmpId(String empId);
	UserDTO getEmpDetails(String empId);
}
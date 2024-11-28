package com.nipppon.p2p.web.app.service.HRMS;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.nipppon.p2p.web.app.entity.HRMS.User;
import com.nipppon.p2p.web.app.repository.HRMS.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String empId) throws UsernameNotFoundException{
		Optional<User> user = userRepository.findByEmpId(empId);
		if(!user.isPresent()){
            throw new UsernameNotFoundException("User Not Found with Emp Id --> "+empId);
		}
        return new org.springframework.security.core.userdetails.User(user.get().getEmpId(), user.get().getPassword(), new ArrayList<>());
	}

}
package com.nipppon.p2p.web.app.controller;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nipppon.p2p.web.app.repository.CRF.CustomerRepository;
import com.nipppon.p2p.web.app.repository.HRMS.UserRepository;
import com.nipppon.p2p.web.app.repository.P2P.Masters.PRRepository;
import com.nipppon.p2p.web.app.repository.VRF.VendorRepository;

@RestController
@RequestMapping("/api/dbConfig")
public class MultipleDBConfiguration{
//	@Autowired
//	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PRRepository prRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private VendorRepository vendorRepository;

	@GetMapping("/hrms")
	public ResponseEntity<?> getAllUsers(){
		return ResponseEntity.ok(userRepository.findAll());
	}

	@GetMapping("/p2p")
	public ResponseEntity<?> getAllPrs(){
		return ResponseEntity.ok(prRepository.findAll());
	}

	@GetMapping("/crf")
	public ResponseEntity<?> getAllCustomers(){
		return ResponseEntity.ok(customerRepository.findAll());
	}

	@GetMapping("/vrf")
	public ResponseEntity<?> getAllVendors(){
		return ResponseEntity.ok(vendorRepository.findAll());
	}
}
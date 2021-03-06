package com.eprocurement.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/api/users")
	public Page<User> getUsers(Pageable pageable){
		return userRepository.findAll(pageable);
	}
	
}

package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.dto.User;
import com.example.login.repository.UserRepository;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class IndexController {
	
	@Autowired
	private BCryptPasswordEncoder encodePassword;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/join")
	public String join(@RequestBody User user) {
		System.out.println(user.getPassword());
		user.setPassword(encodePassword.encode(user.getPassword()));
		userRepository.saveUser(user);
		return "회원가입완료";
	}
}

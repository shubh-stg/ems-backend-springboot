package com.ems.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.dto.JwtReponse;
import com.ems.dto.UserDto;
import com.ems.entity.User;
import com.ems.repository.RoleRepository;
import com.ems.repository.UserRepository;
import com.ems.service.JwtService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired 
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PostMapping("/register")
	public ResponseEntity<String>Register(@Valid @RequestBody UserDto userDto){
		
		if(userRepository.existsByUsername(userDto.getUsername())) {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Username Already Exists");
		}
		User user =new User();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		user.setRoles(Set.of(roleRepository.findById(1L).get()));
		userRepository.save(user);
		
		return ResponseEntity.ok("User Registered Successfully");
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> Login(@RequestBody UserDto userDto){
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
		
		String token=jwtService.generateToken(userDto.getUsername());
		
		
		
		return ResponseEntity.ok(new JwtReponse(token));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
		}
		
	}
}

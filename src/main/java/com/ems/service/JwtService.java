package com.ems.service;


import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ems.entity.Role;
import com.ems.entity.User;
import com.ems.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Autowired
	UserRepository userRepository;
	
	@Value("${jwt.secret}")
	private String SECRET_KEY;
	
	private SecretKey getSignInKey() {
	    return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}
	
	public String generateToken(String username) {
		
		User user=userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
		
		Set<Role>roles=user.getRoles();
		
		Set<String>role_names=roles.stream().map((role)->role.getName()).collect(Collectors.toSet());
		
		return Jwts.builder()
	            .subject(username) 
	            .issuedAt(new Date()) 
	            .expiration(new Date(System.currentTimeMillis() + 1000*60*60*12))
	            .claim("roles", role_names) 
	            .signWith(getSignInKey())
	            .compact();

	}
	public Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getSignInKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		
	}
	
    public String extractUsername(String token) {
       return extractAllClaims(token).getSubject();
    		   
    }

    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }


}

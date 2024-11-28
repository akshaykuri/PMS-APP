package com.nipppon.p2p.web.app.config;

import java.util.Date;
import java.util.function.Function;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil{
	private final String SECRET_KEY = "4f1feeca525de4cdb064656007da3edac7895a87ff0ea865693300fb8b6e8f9c";

	public String extractUsername(String token){
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token){
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public Claims extractAllClaims(String token){
		return Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
	}

	public boolean isTokenExpired(String token){
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(String empId){
		return Jwts.builder()
				.setSubject(empId)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))//last number 1 is for expiring token for 1 minute and change it based on below lines as required
				//1000 - milliseconds --> 1 second
				//1000 * 60 --> 1 minute
				//1000 * 60 * 60 --> 1 hour
				//1000 * 60 * 60 * 10 --> 10 hours
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
	}

	public boolean validateToken(String token, String empId){
		final String userName = extractUsername(token);
		return (userName.equals(empId) && !isTokenExpired(token));
	}
}
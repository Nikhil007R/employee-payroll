package com.bridgelabz.employee_payroll.utility;

import com.bridgelabz.employee_payroll.model.User;
import com.bridgelabz.employee_payroll.repository.EmployeeRepository;
import com.bridgelabz.employee_payroll.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtility {

    @Autowired
    UserRepository userRepository;

    private static final String SECRET_KEY = "MySecretKeyIsBestabcdefghijklmnop";

    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60  * 1000))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token){
        try{
            System.out.println(token);
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            System.out.println("Getting email => " + claims);
            return claims.getSubject();
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    public boolean validateToken(String token, String userEmail){
        final String email = extractEmail(token);

        boolean isTokenPresent = true;
        User user = userRepository.findByEmail(userEmail).orElse(null);

        if(user != null && user.getToken() == null){
            isTokenPresent = false;
        }

        final boolean valid = Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes())
                .build()
                .isSigned(token);

        return valid;
    }
}

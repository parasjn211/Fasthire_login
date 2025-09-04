package com.fasthirelogin.Login.security;

import com.fasthirelogin.Login.entity.FastHireEmployer;
import com.fasthirelogin.Login.entity.FastHireSuperAdmin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    // ✅ Generate token for SuperAdmin
    public String generateToken(FastHireSuperAdmin admin) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("canCreate", admin.isCanCreate());
        claims.put("canUpdate", admin.isCanUpdate());
        claims.put("canDelete", admin.isCanDelete());
        claims.put("canRead", admin.isCanRead());
        claims.put("role", "SUPERADMIN");

        return buildToken(claims, admin.getEmail());
    }

    // ✅ Generate token for Employer
    public String generateToken(FastHireEmployer employer) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("canCreate", employer.isCanCreate());
        claims.put("canUpdate", employer.isCanUpdate());
        claims.put("canDelete", employer.isCanDelete());
        claims.put("canRead", employer.isCanRead());
        claims.put("role", "EMPLOYER");

        return buildToken(claims, employer.getEmail());
    }

    private String buildToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // ✅ Extract email (subject)
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // ✅ Generic extractor using Function
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // ✅ Generic extractor using claim name + type
    public <T> T extractClaim(String token, String claimName, Class<T> clazz) {
        final Claims claims = extractAllClaims(token);
        Object value = claims.get(claimName);
        return clazz.cast(value);
    }

    // ✅ Validate token
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

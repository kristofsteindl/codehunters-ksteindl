package com.ksteindl.mongodbdemo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtProvider {

    private static final String AUTHORITIES_KEY = "roles";

    private final String secret;
    private final int expirationTime;
    private final SecretKey key;

    public JwtProvider(@Value("${jwt.secret}") String secret, 
                       @Value("${jwt.expiration-time}") int expirationTime) {
        this.secret = secret;
        this.expirationTime = expirationTime;
        key = generateSecretKey();
    }

    protected SecretKey generateSecretKey() {
        String secret = Base64.getEncoder().encodeToString(this.secret.getBytes());
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(Authentication authentication) {
        String username = authentication.getName();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        Claims claims = Jwts.claims().setSubject(username);

        claims.put(AUTHORITIES_KEY, authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","))
        );

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(expirationTime))) // if no claim is set already, it creates one and sets its exp date
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(claims.get(AUTHORITIES_KEY).toString());

        // TODO consider roles
        Customer principal = new Customer(claims.getSubject());

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return !claims.getBody()
                    .getExpiration()
                    .before(new Date(Instant.now().getEpochSecond()));

        } catch (SignatureException e) {
            log.warn("Invalid JWT signature", e);
        } catch (MalformedJwtException e) {
            log.warn("Invalid JWT token", e);
        } catch (ExpiredJwtException e) {
            log.warn("Expired JWT token", e);
        } catch (UnsupportedJwtException e) {
            log.warn("Unsupported JWT token", e);
        } catch (IllegalArgumentException e) {
            log.warn("JWT claims string is empty", e);
        }

        return false;
    }
}

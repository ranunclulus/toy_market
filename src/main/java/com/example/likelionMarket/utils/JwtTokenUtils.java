package com.example.likelionMarket.utils;

import com.example.likelionMarket.dtos.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenUtils {
    private final Key signingKey;
    private final JwtParser jwtParser;
    public JwtTokenUtils(
            @Value("${jwt.secret}")
            String jwtSecret
    ) {
        this.signingKey
                = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        this.jwtParser = Jwts
                .parserBuilder()
                .setSigningKey(this.signingKey)
                .build();
    }

    public boolean validate(String token) {
        try {
            jwtParser.parseClaimsJws(token).getBody();
            return true;
        } catch (SecurityException | MalformedJwtException e){
            log.warn("malformed jwt");
        } catch (ExpiredJwtException e) {
            log.warn("expired jwt presented");
        } catch (UnsupportedJwtException e) {
            log.warn("unsupported jwt");
        } catch (IllegalArgumentException e) {
            log.warn("illegal argument");
        }
        return false;
    }

    public String generateToken(String username) {
        // JWT에 담고싶은 추가 정보를 여기에서 추가한다.
        Claims jwtClaims = Jwts.claims()
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(1000)));

        return Jwts.builder()
                .setClaims(jwtClaims)
                .signWith(signingKey)
                .compact();
    }

    // JWT를 인자로 받고, 그 JWT를 해석해서
// 사용자 정보를 회수하는 메소드
    public Claims parseClaims(String token) {
        return jwtParser
                .parseClaimsJws(token)
                .getBody();
    }
}

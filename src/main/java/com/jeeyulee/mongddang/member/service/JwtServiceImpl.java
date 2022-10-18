package com.jeeyulee.mongddang.member.service;

import com.jeeyulee.mongddang.member.dto.MemberLoginResponseDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService{

    @Value("${jwt-token.issue-key}")
    String issueKey;

    @Value("${jwt-token.valid-time}")
    Integer validTime;

    @Override
    public String createJwt(MemberLoginResponseDTO member) {
        Date current = new Date();
        Date expired = new Date(current.getTime() + validTime);
        Key key = Keys.hmacShaKeyFor(issueKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS512)
                .claim("userId", member.getUserId())
                .claim("admin", member.getAdmin())
                .setIssuedAt(current)
                .setExpiration(expired)
                .compact();
    }

    public Boolean validate(String token) {
        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(Base64.getEncoder().encodeToString(issueKey.getBytes()))
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (MissingClaimException | IncorrectClaimException | ExpiredJwtException e) {
            return false;
        }
    }
}

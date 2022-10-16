package com.jeeyulee.mongddang.member.service;

import com.jeeyulee.mongddang.member.vo.MemberVO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService{

    @Value("${jwt-token.issue-key}")
    String issueKey;

    @Value("${jwt-token.valid-time}")
    Integer validTime;

    @Override
    public String createJwt(MemberVO memberVO) {
        Date current = new Date();
        Date expired = new Date(current.getTime() + validTime);
        Key key = Keys.hmacShaKeyFor(issueKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS512)
                .claim("userId", memberVO.getUserId())
                .setIssuedAt(current)
                .setExpiration(expired)
                .compact();
    }

    public Boolean validate(String token) {
        try {
            String userId = Jwts.parserBuilder()
                    .setSigningKey(issueKey)
                    .requireSubject("userId")
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("userId").toString();

            return true;
        } catch (MissingClaimException | IncorrectClaimException | ExpiredJwtException e) {
            return false;
        }
    }
}

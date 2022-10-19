package com.jeeyulee.mongddang.member.service;

import com.jeeyulee.mongddang.member.dto.MemberLoginResponseDTO;
import com.jeeyulee.mongddang.member.repository.MemberRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService{

    @Autowired
    MemberRepository memberRepository;

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

            log.info("JwtServiceImpl validate userId ===> {}", jws.getBody().get("userId").toString());
            log.info("JwtServiceImpl validate admin ===> {}", jws.getBody().get("admin").toString());

            Boolean admin = (Boolean)jws.getBody().get("admin");
            String userId = jws.getBody().get("userId").toString();

            return token.equals(memberRepository.findLastTokenById(userId));
        } catch (MissingClaimException | IncorrectClaimException | ExpiredJwtException e) {
            return false;
        }
    }
}

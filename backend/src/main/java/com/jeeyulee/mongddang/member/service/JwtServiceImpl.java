package com.jeeyulee.mongddang.member.service;

import com.jeeyulee.mongddang.member.domain.MemberLoginResponseDTO;
import com.jeeyulee.mongddang.member.repository.MemberRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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

    @Override
    public Boolean validate(String token) {
        try {
            String userId = parseJwtClaims(token).get("userId").toString();
            return token.equals(memberRepository.findLastTokenById(userId));
        } catch (MissingClaimException | IncorrectClaimException | ExpiredJwtException e) {
            return false;
        }
    }

    @Override
    public Boolean isAdmin(String token){
        Claims claims = parseJwtClaims(token);
        Boolean isAdmin = (Boolean) claims.get("admin");
        log.info("isAdmin ===> {}", isAdmin);

        return isAdmin;
    }

    @Override
    public String retrieveUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Claims claims = parseJwtClaims(request.getHeader("Authorization"));

        return claims.get("userId").toString();
    }


    private Claims parseJwtClaims(String token) {
        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(Base64.getEncoder().encodeToString(issueKey.getBytes()))
                .build()
                .parseClaimsJws(token);

        return jws.getBody();
    }
}

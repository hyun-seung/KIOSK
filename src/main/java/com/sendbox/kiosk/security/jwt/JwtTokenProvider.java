package com.sendbox.kiosk.security.jwt;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final UserDetailServiceImpl userDetailService;

    @Value("${spring.jwt.token.expiration.access}")
    private long accessExpiration;

    @Value("${spring.jwt.token.expiration.refresh}")
    private long refreshExpiration;

    private String secretKey;
    private SecretKey key;
    private JwtParser jwtParser;

    @Value("${spring.jwt.secret}")
    private void getSecretKey(String secretKey) {
        this.secretKey = secretKey;
        this.key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        this.jwtParser = Jwts.parser()
                .verifyWith(key)
                .build();
    }

    public String createAccessToken(Authentication authentication) {
        Claims claims = Jwts.claims().setSubject(authentication.getName()).build();

        Date now = new Date();
        Date expireDate = new Date(now.getTime() + accessExpiration);

        return Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(expireDate)
                .signWith(key)
                .compact();
    }

    public String createRefreshToken(Authentication authentication) {
        Claims claims = Jwts.claims().setSubject(authentication.getName()).build();

        Date now = new Date();
        Date expireDate = new Date(now.getTime() + refreshExpiration);

        return Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(expireDate)
                .signWith(key)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        String userPrincipal = jwtParser
                .parseSignedClaims(token)
                .getPayload().getSubject();

        // 전화번호로 조회
        UserDetails userDetails = userDetailService.loadUserByUsername(userPrincipal);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token){
        try{
            jwtParser.parseClaimsJws(token);
            return true;
        } catch(ExpiredJwtException e) {
            log.error("토큰 만료");
        } catch(JwtException e) {
            log.error("잘못된 토큰 - JwtTokenProvider");
        }
        return false;
    }
}

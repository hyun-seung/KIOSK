package com.sendbox.kiosk.security.jwt.service;

import com.sendbox.kiosk.security.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReleaseTokenService {

    private final JwtTokenProvider jwtTokenProvider;

    public String extractNumber(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        String tell = "";

        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                tell = auth.getName();
            } else {
                log.info("토큰이 없거나 이상한 경우");
            }
        } catch (Exception e) {
            log.error("번호 추출 에러");
        }

        return tell;
    }
}

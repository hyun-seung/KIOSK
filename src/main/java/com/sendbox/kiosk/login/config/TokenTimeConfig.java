package com.sendbox.kiosk.login.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenTimeConfig {

    public static long accessTokenExpiration;
    public static long refreshTokenExpiration;

    @Value("${spring.jwt.token.expiration.access}")
    public void setAccessTokenExpiration(long accessTokenExpiration) {
        this.accessTokenExpiration = accessTokenExpiration;
        log.info("SETTING ACCESS_TOKEN EXPIRATION : {}s", accessTokenExpiration);
    }

    @Value("${spring.jwt.token.expiration.refresh}")
    public void setRefreshTokenExpiration(long refreshTokenExpiration) {
        this.refreshTokenExpiration = refreshTokenExpiration;
        log.info("SETTING REFRESH_TOKEN EXPIRATION : {}s", refreshTokenExpiration);
    }
}

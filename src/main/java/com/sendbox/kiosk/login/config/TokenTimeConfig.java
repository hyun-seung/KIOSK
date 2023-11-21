package com.sendbox.kiosk.login.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenTimeConfig {

    public static long accessTokenExpiration;
    public static long refreshTokenExpiration;

    @Value("${spring.jwt.token.expiration.access}")
    public void setAccessTokenExpiration(long accessTokenExpiration) {
        this.accessTokenExpiration = accessTokenExpiration;
    }

    @Value("${spring.jwt.token.expiration.refresh}")
    public void setRefreshTokenExpiration(long refreshTokenExpiration) {
        this.refreshTokenExpiration = refreshTokenExpiration;
    }
}

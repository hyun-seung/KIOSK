package com.sendbox.kiosk.login.domain;

import com.sendbox.kiosk.login.config.TokenTimeConfig;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
public class Token {

    @Id
    private String accessToken;

    private String refreshToken;

    @TimeToLive
    private long getTimeToLive() {
        return TokenTimeConfig.refreshTokenExpiration;
    }

    @Builder
    public Token(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}

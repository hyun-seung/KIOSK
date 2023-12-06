package com.sendbox.kiosk.login.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenDto {
    private String accessToken;
    private String refreshToken;
    private long expireIn;

    @Builder
    public TokenDto(String accessToken, String refreshToken, long expireIn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expireIn = expireIn;
    }
}

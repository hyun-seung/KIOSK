package com.sendbox.kiosk.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenDto {

    private String accessToekn;
    private String refreshToken;

    @Builder
    public TokenDto(String accessToekn, String refreshToken) {
        this.accessToekn = accessToekn;
        this.refreshToken = refreshToken;
    }
}

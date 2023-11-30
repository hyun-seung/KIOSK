package com.sendbox.kiosk.member.user.domain.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserPointDto {

    private long userId;
    private String tell;
    private int totalPoint;

    @Builder
    public UserPointDto(long userId, String tell, int totalPoint) {
        this.userId = userId;
        this.tell = tell;
        this.totalPoint = totalPoint;
    }
}

package com.sendbox.kiosk.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class UserDto {

    private String name;
    private String phoneNumber;

    @Builder
    public UserDto(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}

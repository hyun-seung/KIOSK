package com.sendbox.kiosk.login.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {

    private long userName;
    private String tell;
}

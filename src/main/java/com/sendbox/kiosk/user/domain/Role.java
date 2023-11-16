package com.sendbox.kiosk.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ROLE_ADMIN("ADMIN", "총 관리자"),
    ROLE_OWNER("OWNER", "매장 운영자"),
    ROLE_STORE("STORE", "매장 내 키오스크"),
    ROLE_USER("USER", "개인 기기 사용자");

    private final String name;
    private final String desc;
}

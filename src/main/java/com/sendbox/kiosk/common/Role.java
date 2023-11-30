package com.sendbox.kiosk.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ROLE_ADMIN("ADMIN", "ROLE_ADMIN", "총 관리자"),
    ROLE_OWNER("OWNER", "ROLE_OWNER", "매장 운영자"),
    ROLE_STORE("STORE", "ROLE_STORE", "매장 내 키오스크"),
    ROLE_MEMBER("MEMBER", "ROLE_MEMBER", "개인 기기 사용자");

    private final String name;
    private final String fullName;
    private final String desc;
}

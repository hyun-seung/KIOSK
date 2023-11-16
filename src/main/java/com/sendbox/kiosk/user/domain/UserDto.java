package com.sendbox.kiosk.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
public class UserDto {

    private String name;
    private String phoneNumber;
    private Set<Role> roles;

    @Builder
    public UserDto(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

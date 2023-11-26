package com.sendbox.kiosk.member.user.domain;

import com.sendbox.kiosk.common.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
public class UserDto {

    private String name;
    private String tell;
    private String password;
    private Integer pin;
    private Set<Role> roles;

    @Builder
    public UserDto(String name, String tell, String password, Integer pin) {
        this.name = name;
        this.tell = tell;
        this.password = password;
        this.pin = pin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

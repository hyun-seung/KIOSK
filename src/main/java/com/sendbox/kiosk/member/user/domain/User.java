package com.sendbox.kiosk.member.user.domain;

import com.sendbox.kiosk.common.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Entity(name = "USERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private int totalPoint;

    @Column
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Builder
    public User(String name, String phoneNumber, Set<Role> roles) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.totalPoint = 0;
        this.roles = roles;
    }

    public User(UserDto userDto) {
        this.name = userDto.getName();
        this.phoneNumber = userDto.getPhoneNumber();
        this.totalPoint = 0;
        this.roles = userDto.getRoles();
    }
}

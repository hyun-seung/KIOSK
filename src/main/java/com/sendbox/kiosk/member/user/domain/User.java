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
    private String tell;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer pin;

    @Column(nullable = false)
    private int totalPoint;

    @Column
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Builder
    public User(String name, String tell, String password, Integer pin, Set<Role> roles) {
        this.name = name;
        this.tell = tell;
        this.password = password;
        this.pin = pin;
        this.totalPoint = 0;
        this.roles = roles;
    }

    public User(UserDto userDto) {
        this.name = userDto.getName();
        this.tell = userDto.getTell();
        this.password = userDto.getPassword();
        this.pin = userDto.getPin();
        this.totalPoint = 0;
        this.roles = userDto.getRoles();
    }
}

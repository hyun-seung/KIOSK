package com.sendbox.kiosk.member.user.controller;

import com.sendbox.kiosk.member.user.domain.User;
import com.sendbox.kiosk.member.user.domain.UserDto;
import com.sendbox.kiosk.member.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup/user")
    public ResponseEntity<Long> signupUser(@RequestBody UserDto userDto) {
        User newUser = userService.createUser(userDto);
        return ResponseEntity.ok(newUser.getId());
    }
}

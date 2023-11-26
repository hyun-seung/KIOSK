package com.sendbox.kiosk.login.controller;

import com.sendbox.kiosk.login.domain.LoginRequestDto;
import com.sendbox.kiosk.login.domain.TokenDto;
import com.sendbox.kiosk.member.user.domain.UserDto;
import com.sendbox.kiosk.login.service.LoginService;
import com.sendbox.kiosk.member.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        TokenDto resultToken = loginService.login(loginRequestDto);
        return ResponseEntity.ok(resultToken);
    }
}



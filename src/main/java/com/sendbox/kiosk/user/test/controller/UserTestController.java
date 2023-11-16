package com.sendbox.kiosk.user.test.controller;

import com.sendbox.kiosk.user.domain.LoginRequestDto;
import com.sendbox.kiosk.user.domain.TokenDto;
import com.sendbox.kiosk.user.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class UserTestController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/testuser/login")
    public ResponseEntity<String> testUserLogin(@RequestBody LoginRequestDto loginRequestDto) {
        log.info("userName : {}, tell : {}", loginRequestDto.getUserName(), loginRequestDto.getTell());
        TokenDto token = loginService.login(loginRequestDto);
        return ResponseEntity.ok(token.getAccessToekn());
    }

    @GetMapping("/user/test")
    public ResponseEntity<String> UserLogin() {
        return ResponseEntity.ok("USER_LOGIN_SUCCESS");
    }
}

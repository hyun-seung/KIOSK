package com.sendbox.kiosk.member.user.controller;

import com.sendbox.kiosk.member.user.domain.User;
import com.sendbox.kiosk.member.user.domain.UserDto;
import com.sendbox.kiosk.member.user.domain.response.UserPointDto;
import com.sendbox.kiosk.member.user.service.UserService;
import com.sendbox.kiosk.security.jwt.service.ReleaseTokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final ReleaseTokenService releaseTokenService;

    @PostMapping("/signup/user")
    public ResponseEntity<Long> signupUser(@RequestBody UserDto userDto) {
        User newUser = userService.createUser(userDto);
        return ResponseEntity.ok(newUser.getId());
    }

    @GetMapping("/mem/point")
    public ResponseEntity<UserPointDto> checkPointByTell(HttpServletRequest request, @RequestParam String tell) {
        String extractedTell = releaseTokenService.extractNumber(request);

        log.info("CONTROLLER TELL : {}", extractedTell);

        UserPointDto userPointDto = userService.checkPointByTell(tell);
        return ResponseEntity.ok(userPointDto);
    }
}

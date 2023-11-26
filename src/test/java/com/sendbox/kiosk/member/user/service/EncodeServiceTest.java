package com.sendbox.kiosk.member.user.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class EncodeServiceTest {

    @Autowired
    private EncodeService encodeService;

    @DisplayName("비밀번호를 암호화하고, 암호화된 비밀번호가 일치하는지 확인이 가능하다.")
    @Test
    public void encodingPasswordTestTrueCase() {
        //given
        String rawPassword = "test12#$";
        String anotherPassword = "test12#$1@";

        //when
        String encodingPassword = encodeService.encodingPassword(rawPassword);

        //then
        assertTrue(encodeService.isMatch(rawPassword, encodingPassword));
        assertFalse(encodeService.isMatch(anotherPassword, encodingPassword));
    }
}

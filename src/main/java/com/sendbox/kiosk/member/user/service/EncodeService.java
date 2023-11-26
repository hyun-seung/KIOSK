package com.sendbox.kiosk.member.user.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncodeService {

    private PasswordEncoder passwordEncoder;

    public EncodeService() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public boolean isMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public String encodingPassword(String password) {
        return passwordEncoder.encode(password);
    }
}

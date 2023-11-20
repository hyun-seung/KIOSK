package com.sendbox.kiosk.member.user.service;

import com.sendbox.kiosk.common.Role;
import com.sendbox.kiosk.member.user.domain.User;
import com.sendbox.kiosk.member.user.domain.UserDto;
import com.sendbox.kiosk.member.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Transactional
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDto userDto) {
        userDto.setRole(Role.ROLE_USER);
        List<User> users = userRepository.findByPhoneNumber(userDto.getPhoneNumber());

        if (users.size() != 0) {
            log.info("유저 : {}", users.size());
            return null;
        }

        User newUser = new User(userDto);
        log.info("저장");
        return userRepository.save(newUser);
    }

    public User createAdmin(UserDto userDto) {
        userDto.setRole(Role.ROLE_ADMIN);
        List<User> users = userRepository.findByPhoneNumber(userDto.getPhoneNumber());

        if (users.size() != 0) {
            log.info("유저 : {}", users.size());
            return null;
        }

        User newUser = new User(userDto);
        log.info("저장");
        return userRepository.save(newUser);
    }

    public User getUserIdByPhoneNumber(String phoneNumber) {
        return null;
    }
}

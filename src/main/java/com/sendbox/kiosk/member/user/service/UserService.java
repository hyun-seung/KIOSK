package com.sendbox.kiosk.member.user.service;

import com.sendbox.kiosk.common.Role;
import com.sendbox.kiosk.member.user.domain.User;
import com.sendbox.kiosk.member.user.domain.UserDto;
import com.sendbox.kiosk.member.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final EncodeService encodeService;
    private final UserRepository userRepository;

    public User createUser(UserDto userDto) {
        userDto.setRoles(new HashSet<>(Arrays.asList(Role.ROLE_USER)));

        String encodedPassword = encodeService.encodingPassword(userDto.getPassword());
        userDto.setPassword(encodedPassword);

        User newUser = new User(userDto);
        return userRepository.save(newUser);
    }

    public boolean isNotExistedUser(String tell) {
        List<User> users = userRepository.findByTell(tell);
        return users.isEmpty();
    }


}
package com.sendbox.kiosk.user.service;

import com.sendbox.kiosk.user.domain.User;
import com.sendbox.kiosk.user.domain.UserDto;
import com.sendbox.kiosk.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDto userDto) {
        List<User> users = userRepository.findByPhoneNumber(userDto.getPhoneNumber());

        if (users.size() != 0) {
            return null;
        }

        User newUser = new User(userDto);
        return userRepository.save(newUser);
    }

    public User getUserIdByPhoneNumber(String phoneNumber) {
        return null;
    }
}

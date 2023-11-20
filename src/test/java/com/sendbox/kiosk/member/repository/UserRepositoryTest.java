package com.sendbox.kiosk.member.repository;

import com.sendbox.kiosk.member.user.domain.User;
import com.sendbox.kiosk.member.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAllInBatch();
    }

    @DisplayName("전화번호로 해당 유저를 찾는다.")
    @Test
    public void findByPhoneNumberTest() {
        //given
        String phoneNumber = "01012345678";
        String userName = "test_user";
        User user1 = User.builder()
                .name(userName)
                .phoneNumber(phoneNumber)
                .build();
        User user2 = User.builder()
                .name("another_name")
                .phoneNumber("01099999999")
                .build();

        userRepository.save(user2);
        userRepository.save(user1);

        //when
        List<User> users = userRepository.findByPhoneNumber(phoneNumber);

        //then
        assertEquals(userName, users.get(0).getName());
    }
}
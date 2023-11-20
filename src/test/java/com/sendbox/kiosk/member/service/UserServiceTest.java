package com.sendbox.kiosk.member.service;

import com.sendbox.kiosk.member.user.domain.User;
import com.sendbox.kiosk.member.user.domain.UserDto;
import com.sendbox.kiosk.member.user.repository.UserRepository;
import com.sendbox.kiosk.member.user.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @AfterEach
    void tearDown() {
        userRepository.deleteAllInBatch();
    }

    @DisplayName("유저를 생성한다.")
    @Test
    public void createUserTest() {
        //given
        String userName = "TEST_USER";
        String userPhoneNumber = "01011112222";

        UserDto userDto = UserDto.builder()
                .name(userName)
                .phoneNumber(userPhoneNumber)
                .build();

        //when
        User user = userService.createUser(userDto);

        //then
        assertEquals(userName, user.getName());
        assertEquals(userPhoneNumber, user.getPhoneNumber());
        assertEquals(0, user.getTotalPoint());
    }

    @DisplayName("이미 존재하는 핸드폰 번호가 존재할 때는 생성에 실패하여 NULL을 반환한다.")
    @Test
    public void Test() {
        //given
        String userPhoneNumber = "01011112222";

        UserDto userDto1 = UserDto.builder()
                .name("TEST_USER1")
                .phoneNumber(userPhoneNumber)
                .build();

        UserDto userDto2 = UserDto.builder()
                .name("TEST_USER1")
                .phoneNumber(userPhoneNumber)
                .build();

        userService.createUser(userDto1);

        //when
        User user = userService.createUser(userDto2);

        //then
        assertNull(user);
    }

}
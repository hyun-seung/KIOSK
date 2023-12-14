package com.sendbox.kiosk.redis.repository;

import com.sendbox.kiosk.login.domain.Token;
import com.sendbox.kiosk.login.domain.TokenDto;
import com.sendbox.kiosk.redis.domain.UserToken;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class UserTokenRepositoryTest {

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Autowired
    private RedisTemplate<String, UserToken> redisTemplate;

    @AfterEach
    void tearDown() {
        Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection().serverCommands().flushDb();
    }

    @DisplayName("유저 토큰 정보를 저장하고 핸드폰 번호를 통해 토큰 정보를 조회한다.")
    @Test
    public void SaveAndFindByTellTest() {
        //given
        String userTell = "01011112222";
        String accessToken = "asdfzxcv";
        String refreshToken = "qwerasdf";

        Token token = new Token(accessToken, refreshToken);

        UserToken userToken = UserToken.builder()
                .userTell(userTell)
                .token(token)
                .build();

        //when
        userTokenRepository.save(userToken, 300);

        //then
        TokenDto findToken = userTokenRepository.findByTell(userTell);
        assertEquals(refreshToken, findToken.getRefreshToken());
    }
}